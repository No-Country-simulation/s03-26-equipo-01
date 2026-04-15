export type EmbedSnippet = {
  code: string;
  copyLabel: string;
  description: string;
  id: string;
  title: string;
};

export const EMBED_API_KEY = "vza_i2345678980abcdef123456789abcdef";

export const EMBED_INSTRUCTIONS = [
  "Copia el codigo del widget que quieras probar.",
  "Reemplaza YOUR_API_KEY por la clave asignada cuando se conecte la integracion real.",
  "Pega el fragmento en tu HTML donde quieras renderizar testimonios.",
  "Ajusta los parametros del snippet segun la experiencia visual que necesites.",
];

export const EMBED_SNIPPETS: EmbedSnippet[] = [
  {
    id: "carousel",
    title: "Carrusel",
    description:
      "Snippet de prueba para visualizar un carrusel con reproduccion automatica.",
    copyLabel: "Codigo del carrusel",
    code: `<!-- Carrusel de Testimonios de Voz Activa -->
<div id="vozactiva-carousel"></div>
<script src="https://cdn.vozactiva.com/widget.js"></script>
<script>
  VozActiva.init({
    apiKey: 'YOUR_API_KEY',
    container: '#vozactiva-carousel',
    style: 'carousel',
    autoSlide: true
  });
</script>`,
  },
];
