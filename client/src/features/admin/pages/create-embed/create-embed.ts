export type EmbedSnippet = {
  code: string;
  copyLabel: string;
  description: string;
  id: string;
  title: string;
};

const DEFAULT_SITE_URL = 'http://localhost:5173';
const EMBED_API_KEY_PLACEHOLDER = 'YOUR_API_KEY';

export const EMBED_INSTRUCTIONS = [
  'Copia el código del widget que deseas usar',
  'Reemplazá YOUR_API_KEY con tu clave API',
  'Pega el código en tu página HTML donde quieras mostrar los testimonios',
  'Personaliza los parámetros según tus necesidades',
];

function sanitizeBaseUrl(baseUrl: string) {
  return baseUrl.replace(/\/+$/, '');
}

function buildEmbedUrl(baseUrl: string, apiKey = EMBED_API_KEY_PLACEHOLDER) {
  const testimonialUrl = `${sanitizeBaseUrl(baseUrl)}/testimonial`;
  const query = new URLSearchParams({ apiKey });
  return `${testimonialUrl}?${query.toString()}`;
}

export function getEmbedSnippets(
  baseUrl: string,
  apiKey = EMBED_API_KEY_PLACEHOLDER,
): EmbedSnippet[] {
  const iframeUrl = buildEmbedUrl(baseUrl, apiKey);

  return [
    {
      id: 'html',
      title: 'Formulario',
      description:
        'Testimonios en formato carrusel con reproducción automática.',
      copyLabel: 'Código HTML',
      code: `<iframe
  src="${iframeUrl}"
  title="Formulario de testimonios"
  width="100%"
  height="940"
  frameBorder="0"
  loading="lazy"
></iframe>`,
    },
  ];
}

export function getEmbedBaseUrl() {
  if (typeof window === 'undefined') return DEFAULT_SITE_URL;

  return window.location.origin || DEFAULT_SITE_URL;
}
