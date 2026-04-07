import useActive from "../../../../../../shared/hooks/use-active";
import AddButton from "../../../../components/add-button/AddButton";
import type { TabContent } from "../../../../components/tab-container/tab-container";
import { TabValues } from "../../../../components/tab-container/tab-values";
import './styles/tab-table.css';

const TagSubPanel = ({currentTab}: TabContent) => {
    
    const {handleActive} = useActive();

    return (
        <section hidden = {currentTab !== TabValues.TAG} className = 'tag-subpanel-container'>
            <AddButton text = "CREAR TAG" onSubmit = {handleActive} />
        </section>
    )
}

export default TagSubPanel;