import EmbedPanel from './components/embed-panel/EmbedPanel';
import { getCarouselEmbedContent } from './create-embed';
import useCreateEmbed from './hooks/use-create-embed';
import './styles/create-embed.css';

const CreateCarouselEmbed = () => {
  const embedState = useCreateEmbed({
    getPageContent: getCarouselEmbedContent,
  });

  return <EmbedPanel {...embedState} />;
};

export default CreateCarouselEmbed;
