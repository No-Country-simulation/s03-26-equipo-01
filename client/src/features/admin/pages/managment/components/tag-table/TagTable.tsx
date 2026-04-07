import useActive from "../../../../../../shared/hooks/use-active";
import AddButton from "../../../../components/add-button/AddButton";
import type { TabContent } from "../../../../components/tab-container/tab-container";
import { TabValues } from "../../../../components/tab-container/tab-values";
import TagModalForm from "../../../../components/tag-modal-form/TagModalForm";
import useTag from "../../../../hooks/use-tag";
import './styles/tab-table.css';

const TagSubPanel = ({currentTab}: TabContent) => {
    
    const {isActive, handleActive} = useActive();
    const {addTag} = useTag();

    return (
        <section hidden = {currentTab !== TabValues.TAG} className = 'tag-subpanel-container'>
            <AddButton text = "CREAR TAG" onSubmit = {handleActive} />
            {isActive && <TagModalForm onSubmit = {addTag} />}
        </section>
    )
}

export default TagSubPanel;