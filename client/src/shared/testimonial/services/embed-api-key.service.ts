const EMBED_API_KEY_QUERY_PARAM = 'apiKey';

declare global {
  interface Window {
    VOZACTIVA_EMBED_API_KEY?: string;
  }
}

function getEmbedApiKey() {
  const queryApiKey = getApiKeyFromQuery();

  if (queryApiKey) return queryApiKey;

  if (typeof window !== 'undefined' && window.VOZACTIVA_EMBED_API_KEY) {
    return window.VOZACTIVA_EMBED_API_KEY;
  }

  return import.meta.env.VITE_TESTIMONIAL_EMBED_API_KEY || '';
}

function getApiKeyFromQuery() {
  if (typeof window === 'undefined') return '';

  try {
    const searchParams = new URLSearchParams(window.location.search);
    return searchParams.get(EMBED_API_KEY_QUERY_PARAM)?.trim() || '';
  } catch {
    return '';
  }
}

export default getEmbedApiKey;
