import itemsData from './editor-sidebar';
import EditorRoutes from '../../routes/editor-routes';
import { EDITOR_BASE_PATH } from '../../../../core/routes/routes';
import PageContainer from '../../../../shared/components/page-container/PageContainer';

const Editor = () => {
  return (
    <PageContainer itemsData={itemsData} basePath={EDITOR_BASE_PATH}>
      <EditorRoutes />
    </PageContainer>
  );
};

export default Editor;
