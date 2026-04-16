export type EmbedSnippet = {
  code: string;
  copyLabel: string;
  description: string;
  id: string;
  title: string;
};

export type EmbedPageContent = {
  apiKeyTitle: string;
  apiKeyWarning: string;
  instructions: string[];
  instructionsTitle: string;
  snippets: EmbedSnippet[];
  text: string;
  title: string;
};

const DEFAULT_SITE_URL = 'http://localhost:5173';
const EMBED_API_KEY_PLACEHOLDER = 'YOUR_API_KEY';
const DEFAULT_FORM_HEIGHT = '58.75rem';
const DEFAULT_CAROUSEL_HEIGHT = '40rem';

export const EMBED_INSTRUCTIONS = [
  'Copia el codigo del widget que deseas usar',
  'Reemplaza YOUR_API_KEY con tu clave API',
  'Pega el codigo en tu pagina HTML donde quieras mostrar los testimonios',
  'Personaliza los parametros segun tus necesidades',
];

function sanitizeBaseUrl(baseUrl: string) {
  return baseUrl.replace(/\/+$/, '');
}

function buildEmbedUrl(
  baseUrl: string,
  path: string,
  apiKey = EMBED_API_KEY_PLACEHOLDER,
) {
  const testimonialUrl = `${sanitizeBaseUrl(baseUrl)}${path}`;
  const query = new URLSearchParams({ apiKey });
  return `${testimonialUrl}?${query.toString()}`;
}

function buildIframeSnippet(url: string, title: string, height: string) {
  return `<iframe
  src="${url}"
  title="${title}"
  frameBorder="0"
  loading="lazy"
  style="width: 100%; min-height: ${height}; border: 0;"
></iframe>`;
}

export function getFormEmbedSnippets(
  baseUrl: string,
  apiKey = EMBED_API_KEY_PLACEHOLDER,
): EmbedSnippet[] {
  const iframeUrl = buildEmbedUrl(baseUrl, '/testimonial', apiKey);

  return [
    {
      id: 'html',
      title: 'Formulario',
      description:
        'Formulario embebido para recolectar testimonios desde tu sitio.',
      copyLabel: 'Codigo HTML',
      code: buildIframeSnippet(
        iframeUrl,
        'Formulario de testimonios',
        DEFAULT_FORM_HEIGHT,
      ),
    },
  ];
}

export function getCarouselEmbedSnippets(
  baseUrl: string,
  apiKey = EMBED_API_KEY_PLACEHOLDER,
): EmbedSnippet[] {
  const carouselUrl = buildEmbedUrl(baseUrl, '/testimonial/published', apiKey);

  return [
    {
      id: 'carousel',
      title: 'Carrusel',
      description:
        'Testimonios en formato carrusel con reproduccion automatica.',
      copyLabel: 'Codigo HTML',
      code: buildIframeSnippet(
        carouselUrl,
        'Carrusel de testimonios',
        DEFAULT_CAROUSEL_HEIGHT,
      ),
    },
  ];
}

export function getFormEmbedContent(
  baseUrl: string,
  apiKey = EMBED_API_KEY_PLACEHOLDER,
): EmbedPageContent {
  return {
    title: 'Embed para el formulario',
    text:
      'Integra en tu sitio externo el formulario para poder recolectar los testimonios de tus usuarios.',
    instructionsTitle: 'Instrucciones de instalacion',
    instructions: EMBED_INSTRUCTIONS,
    apiKeyTitle: 'Clave API',
    apiKeyWarning: 'Manten tu clave API segura. No la compartas publicamente.',
    snippets: getFormEmbedSnippets(baseUrl, apiKey),
  };
}

export function getCarouselEmbedContent(
  baseUrl: string,
  apiKey = EMBED_API_KEY_PLACEHOLDER,
): EmbedPageContent {
  return {
    title: 'Embed para publicar testimonios',
    text: 'Integra tus testimonios en tu sitio web con estos codigos.',
    instructionsTitle: 'Instrucciones de instalacion',
    instructions: EMBED_INSTRUCTIONS,
    apiKeyTitle: 'Tu Clave API',
    apiKeyWarning: 'Manten tu clave API segura. No la compartas publicamente.',
    snippets: getCarouselEmbedSnippets(baseUrl, apiKey),
  };
}

export function getEmbedSnippets(
  baseUrl: string,
  apiKey = EMBED_API_KEY_PLACEHOLDER,
): EmbedSnippet[] {
  return getFormEmbedSnippets(baseUrl, apiKey);
}

export function getEmbedBaseUrl() {
  if (typeof window === 'undefined') return DEFAULT_SITE_URL;

  return window.location.origin || DEFAULT_SITE_URL;
}
