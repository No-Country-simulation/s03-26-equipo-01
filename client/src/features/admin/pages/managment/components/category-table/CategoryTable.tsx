import useActive from "../../../../../../shared/hooks/use-active";
import AddButton from "../../../../components/add-button/AddButton";
import CategoryModalForm from "../../../../components/category-modal-form/CategoryModalForm";
import type { TabContent } from "../../../../components/tab-container/tab-container";
import { TabValues } from "../../../../components/tab-container/tab-values";
import useCategory from "../../../../hooks/use-category";
import './styles/category-table.css';

const CategorySubPanel = ({currentTab}: TabContent) => {
    
    const {isActive, handleActive} = useActive();
    const {addCategory} = useCategory();

    return (
        <section hidden = {currentTab !== TabValues.CATEGORIA} className = 'category-subpanel-container'>
            <AddButton text = "CREAR CATEGORIA" onSubmit = {handleActive} />
            {isActive && <CategoryModalForm onSubmit = {addCategory} />}
        </section>
    )
}

export default CategorySubPanel;