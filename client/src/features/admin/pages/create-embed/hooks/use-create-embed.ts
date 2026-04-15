import { useEffect, useState } from "react";
import {
  EMBED_INSTRUCTIONS,
  getEmbedBaseUrl,
  getEmbedSnippets,
} from "../create-embed";
import adminApiKeyService from "../../../services/api-key/admin-api-key.service";

const MASK_CHARACTER = "*";
const COPY_SUCCESS_MESSAGE = "Texto copiado al cortapapeles";
const COPY_ERROR_MESSAGE = "No se pudo copiar el texto al cortapapeles.";
const API_KEY_ERROR_MESSAGE = "No se pudo obtener la API key del administrador.";

const useCreateEmbed = () => {
  const [toastMessage, setToastMessage] = useState("");
  const [toastType, setToastType] = useState<"success" | "error" | "info">(
    "success"
  );
  const [isApiKeyVisible, setIsApiKeyVisible] = useState(false);
  const [apiKey, setApiKey] = useState("");

  useEffect(() => {
    adminApiKeyService()
      .then((responseApiKey) => setApiKey(responseApiKey))
      .catch((error) => {
        console.error(error);
        setToastType("error");
        setToastMessage(API_KEY_ERROR_MESSAGE);
      });
  }, []);

  useEffect(() => {
    if (!toastMessage) return;

    const timeoutId = window.setTimeout(() => setToastMessage(""), 2500);

    return () => window.clearTimeout(timeoutId);
  }, [toastMessage]);

  const toggleApiKeyVisibility = () =>
    setIsApiKeyVisible((currentValue) => !currentValue);

  const handleCopy = async (value: string, _label: string) => {
    const copied = await copyToClipboard(value);

    if (!copied) {
      setToastType("error");
      setToastMessage(COPY_ERROR_MESSAGE);
      return;
    }

    setToastType("success");
    setToastMessage(COPY_SUCCESS_MESSAGE);
  };

  const handleCloseToast = () => setToastMessage("");

  const displayApiKey = isApiKeyVisible
    ? apiKey
    : MASK_CHARACTER.repeat(apiKey.length);

  const embedBaseUrl = getEmbedBaseUrl();

  return {
    apiKey,
    displayApiKey,
    embedBaseUrl,
    embedSnippets: getEmbedSnippets(embedBaseUrl, apiKey || undefined),
    handleCloseToast,
    instructions: EMBED_INSTRUCTIONS,
    isApiKeyVisible,
    toastMessage,
    toastType,
    toggleApiKeyVisibility,
    handleCopy,
  };
};

const copyToClipboard = async (value: string) => {
  if (typeof navigator !== "undefined" && navigator.clipboard?.writeText) {
    try {
      await navigator.clipboard.writeText(value);
      return true;
    } catch (error) {
      console.error(error);
    }
  }

  try {
    const textArea = document.createElement("textarea");
    textArea.value = value;
    textArea.style.position = "fixed";
    textArea.style.left = "-999999px";
    textArea.style.top = "-999999px";
    document.body.appendChild(textArea);
    textArea.focus();
    textArea.select();

    const copied = document.execCommand("copy");
    document.body.removeChild(textArea);

    return copied;
  } catch (error) {
    console.error(error);
    return false;
  }
};

export default useCreateEmbed;
