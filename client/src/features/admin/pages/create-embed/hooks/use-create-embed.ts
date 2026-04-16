import { useEffect, useState } from 'react';
import type { EmbedPageContent } from '../create-embed';
import { getEmbedBaseUrl } from '../create-embed';
import adminApiKeyService from '../../../services/api-key/admin-api-key.service';

const MASK_CHARACTER = '*';
const COPY_SUCCESS_MESSAGE = 'Texto copiado al portapapeles';
const COPY_ERROR_MESSAGE = 'No se pudo copiar el texto al portapapeles.';
const API_KEY_ERROR_MESSAGE = 'No se pudo obtener la API key del administrador.';

type UseCreateEmbedParams = {
  getPageContent: (baseUrl: string, apiKey?: string) => EmbedPageContent;
};

const useCreateEmbed = ({ getPageContent }: UseCreateEmbedParams) => {
  const [toastMessage, setToastMessage] = useState('');
  const [toastType, setToastType] = useState<'success' | 'error' | 'info'>(
    'success',
  );
  const [isApiKeyVisible, setIsApiKeyVisible] = useState(false);
  const [apiKey, setApiKey] = useState('');

  useEffect(() => {
    adminApiKeyService()
      .then((responseApiKey) => setApiKey(responseApiKey))
      .catch((error) => {
        console.error(error);
        setToastType('error');
        setToastMessage(API_KEY_ERROR_MESSAGE);
      });
  }, []);

  useEffect(() => {
    if (!toastMessage) return;

    const timeoutId = window.setTimeout(() => setToastMessage(''), 2500);

    return () => window.clearTimeout(timeoutId);
  }, [toastMessage]);

  const toggleApiKeyVisibility = () =>
    setIsApiKeyVisible((currentValue) => !currentValue);

  const handleCopy = async (value: string, _label: string) => {
    const copied = await copyToClipboard(value);

    if (!copied) {
      setToastType('error');
      setToastMessage(COPY_ERROR_MESSAGE);
      return;
    }

    setToastType('success');
    setToastMessage(COPY_SUCCESS_MESSAGE);
  };

  const handleCloseToast = () => setToastMessage('');

  const displayApiKey = isApiKeyVisible
    ? apiKey
    : MASK_CHARACTER.repeat(apiKey.length);

  const embedBaseUrl = getEmbedBaseUrl();
  const pageContent = getPageContent(embedBaseUrl, apiKey || undefined);

  return {
    apiKey,
    displayApiKey,
    handleCloseToast,
    handleCopy,
    isApiKeyVisible,
    pageContent,
    toastMessage,
    toastType,
    toggleApiKeyVisibility,
  };
};

const copyToClipboard = async (value: string) => {
  if (typeof navigator !== 'undefined' && navigator.clipboard?.writeText) {
    try {
      await navigator.clipboard.writeText(value);
      return true;
    } catch (error) {
      console.error(error);
    }
  }

  try {
    const textArea = document.createElement('textarea');
    textArea.value = value;
    textArea.style.position = 'fixed';
    textArea.style.left = '-999999px';
    textArea.style.top = '-999999px';
    document.body.appendChild(textArea);
    textArea.focus();
    textArea.select();

    const copied = document.execCommand('copy');
    document.body.removeChild(textArea);

    return copied;
  } catch (error) {
    console.error(error);
    return false;
  }
};

export default useCreateEmbed;
