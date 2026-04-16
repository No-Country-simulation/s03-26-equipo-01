import EmbedPanel from './components/embed-panel/EmbedPanel';
import { getFormEmbedContent } from './create-embed';
import useCreateEmbed from './hooks/use-create-embed';
import './styles/create-embed.css';

const CreateEmbed = () => {
  const embedState = useCreateEmbed({
    getPageContent: getFormEmbedContent,
  });

  return <EmbedPanel {...embedState} />;
};

export default CreateEmbed;
