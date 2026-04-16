import type { EmbedSnippet } from "../../create-embed";

export type EmbedCodeCardProps = {
  onCopy: (value: string, label: string) => Promise<void>;
  snippet: EmbedSnippet;
};
