import { Code2 } from 'lucide-react';
import TitleContainer from '../../../../components/title-container/TitleContainer';
import Toast from '../../../../../../shared/components/toast/Toast';
import type { EmbedPageContent } from '../../create-embed';
import ApiKeyCard from '../api-key-card/ApiKeyCard';
import EmbedCodeCard from '../embed-code-card/EmbedCodeCard';

type EmbedPanelProps = {
  apiKey: string;
  displayApiKey: string;
  handleCloseToast: () => void;
  handleCopy: (value: string, label: string) => Promise<void>;
  isApiKeyVisible: boolean;
  pageContent: EmbedPageContent;
  toastMessage: string;
  toastType: 'success' | 'error' | 'info';
  toggleApiKeyVisibility: () => void;
};

const EmbedPanel = ({
  apiKey,
  displayApiKey,
  handleCloseToast,
  handleCopy,
  isApiKeyVisible,
  pageContent,
  toastMessage,
  toastType,
  toggleApiKeyVisibility,
}: EmbedPanelProps) => {
  return (
    <section className='embed-admin-panel'>
      <div className='embed-admin-panel_container'>
        <div className='embed-admin-panel_container--info'>
          <TitleContainer title={pageContent.title} text={pageContent.text} />
          <section className='embed-instructions'>
            <h3 className='embed-instructions_title'>
              <Code2 size={18} aria-hidden='true' />
              <span>{pageContent.instructionsTitle}</span>
            </h3>
            <ul className='embed-instructions_list'>
              {pageContent.instructions.map((instruction) => (
                <li key={instruction}>{instruction}</li>
              ))}
            </ul>
          </section>
          <div className='embed-cards'>
            {pageContent.snippets.map((snippet) => (
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
              title={pageContent.apiKeyTitle}
              warningText={pageContent.apiKeyWarning}
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
              <strong>{toastType === 'success' ? 'Listo!' : 'Error'}</strong>
              <p>{toastMessage}</p>
            </>
          }
        />
      )}
    </section>
  );
};

export default EmbedPanel;
