import { Copy } from "lucide-react";
import type { EmbedCodeCardProps } from "./embed-code-card";

const EmbedCodeCard = ({ snippet, onCopy }: EmbedCodeCardProps) => {
  return (
    <article className="embed-card">
      <div className="embed-card_header">
        <div>
          <h3 className="embed-card_title">{snippet.title}</h3>
          <p className="embed-card_description">{snippet.description}</p>
        </div>
        <button
          type="button"
          onClick={() => onCopy(snippet.code, snippet.copyLabel)}
          className="embed-button embed-button-primary"
        >
          <Copy size={16} />
          Copiar
        </button>
      </div>
      <div className="embed-card_body">
        <pre className="embed-code-block">
          <code className="embed-code-text">{snippet.code}</code>
        </pre>
      </div>
    </article>
  );
};

export default EmbedCodeCard;
