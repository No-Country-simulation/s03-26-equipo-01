import { Copy, Eye, EyeOff } from 'lucide-react';
import type { ApiKeyCardProps } from './api-key-card';

const ApiKeyCard = ({
  apiKey,
  displayApiKey,
  isVisible,
  onCopy,
  onToggleVisibility,
  title = 'Clave API',
  warningText = 'Manten tu clave API segura. No la compartas publicamente.',
}: ApiKeyCardProps) => {
  return (
    <article className='embed-card'>
      <div className='embed-card_header embed-card_header-simple'>
        <h3 className='embed-card_title'>{title}</h3>
      </div>
      <div className='embed-card_body'>
        <div className='embed-api-key'>
          <div className='embed-api-key_display'>{displayApiKey}</div>
          <button
            type='button'
            onClick={onToggleVisibility}
            className='embed-button embed-button-icon'
            aria-label={isVisible ? 'Ocultar clave API' : 'Mostrar clave API'}
          >
            {isVisible ? (
              <EyeOff size={20} className='embed-icon-muted' />
            ) : (
              <Eye size={20} className='embed-icon-muted' />
            )}
          </button>
          <button
            type='button'
            onClick={() => onCopy(apiKey, title)}
            className='embed-button embed-button-primary'
          >
            <Copy size={16} />
            Copiar
          </button>
        </div>
        <p className='embed-api-key_warning'>{warningText}</p>
      </div>
    </article>
  );
};

export default ApiKeyCard;
