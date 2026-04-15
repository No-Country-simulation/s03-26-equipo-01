import TitleContainer from "../../components/title-container/TitleContainer";
import Toast from "../../../../shared/components/toast/Toast";
import ApiKeyCard from "./components/api-key-card/ApiKeyCard";
import EmbedCodeCard from "./components/embed-code-card/EmbedCodeCard";
import useCreateEmbed from "./hooks/use-create-embed";
import "./styles/create-embed.css";

const CreateEmbed = () => {
  const {
    apiKey,
    displayApiKey,
    embedBaseUrl,
    embedSnippets,
    handleCloseToast,
    instructions,
    isApiKeyVisible,
    toastMessage,
    toastType,
    toggleApiKeyVisibility,
    handleCopy,
  } = useCreateEmbed();

  return (
    <section className="embed-admin-panel">
      <div className="embed-admin-panel_container">
        <div className="embed-admin-panel_container--info">
          <TitleContainer
            title="Codigos de embed"
            text={`Usá estos snippets para insertar el formulario real de testimonios desde ${embedBaseUrl}.`}
          />
          <section className="embed-instructions">
            <h3 className="embed-instructions_title">Instrucciones de instalacion</h3>
            <ul className="embed-instructions_list">
              {instructions.map((instruction) => (
                <li key={instruction}>{instruction}</li>
              ))}
            </ul>
          </section>
          <div className="embed-cards">
            {embedSnippets.map((snippet) => (
              <EmbedCodeCard
                key={snippet.id}
                snippet={snippet}
                onCopy={handleCopy}
              />
            ))}
            <ApiKeyCard
              apiKey={apiKey}
              displayApiKey={displayApiKey}
              isVisible={isApiKeyVisible}
              onCopy={handleCopy}
              onToggleVisibility={toggleApiKeyVisibility}
            />
          </div>
        </div>
      </div>
      {toastMessage && (
        <Toast
          type={toastType}
          onClose={handleCloseToast}
          content={
            <>
              <strong>{toastType === "success" ? "Listo!" : "Error"}</strong>
              <p>{toastMessage}</p>
            </>
          }
        />
      )}
    </section>
  );
};

export default CreateEmbed;
