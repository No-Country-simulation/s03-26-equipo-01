export type EmbedSnippet = {
  code: string;
  copyLabel: string;
  description: string;
  id: string;
  title: string;
};

const DEFAULT_SITE_URL = 'http://localhost:5173';

export const EMBED_API_KEY =
  import.meta.env.VITE_TESTIMONIAL_EMBED_API_KEY ||
  'vza_i2345678980abcdef123456789abcdef';

export const EMBED_INSTRUCTIONS = [
  'Copiá el HTML del formulario.',
  'Reemplazá YOUR_API_KEY por la clave API del cliente que va a enviar testimonios.',
  'Pegalo directo en la página donde querés mostrar el formulario.',
  'Publicá el frontend y actualizá la URL base si el formulario vive en otro dominio.',
  'Si hace falta, ajustá el alto, el borde y el ancho del iframe según el diseño del sitio.',
];

function sanitizeBaseUrl(baseUrl: string) {
  return baseUrl.replace(/\/+$/, '');
}

function buildEmbedUrl(baseUrl: string, apiKeyPlaceholder = 'YOUR_API_KEY') {
  const testimonialUrl = `${sanitizeBaseUrl(baseUrl)}/testimonial`;
  const query = new URLSearchParams({ apiKey: apiKeyPlaceholder });
  return `${testimonialUrl}?${query.toString()}`;
}

export function getEmbedSnippets(baseUrl: string): EmbedSnippet[] {
  const iframeUrl = buildEmbedUrl(baseUrl);

  return [
    {
      id: 'html',
      title: 'HTML del formulario',
      description:
        'Fragmento listo para pegar en una página y mostrar el formulario real de testimonios.',
      copyLabel: 'Código HTML',
      code: `<!-- Formulario de testimonios Voz Activa -->
<iframe
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
