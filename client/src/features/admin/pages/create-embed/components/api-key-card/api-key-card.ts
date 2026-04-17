export type ApiKeyCardProps = {
  apiKey: string;
  displayApiKey: string;
  isVisible: boolean;
  onCopy: (value: string, label: string) => Promise<void>;
  onToggleVisibility: () => void;
  title?: string;
  warningText?: string;
};
