import useActive from "../../../../../../shared/hooks/use-active";
import AddButton from "../../../../components/add-button/AddButton";
import CategoryModalForm from "../../../../components/category-modal-form/CategoryModalForm";
import type { TabContent } from "../../../../components/tab-container/tab-container";
import { TabValues } from "../../../../components/tab-container/tab-values";

const CategoryDashboard = ({currentTab}: TabContent) => {
    
    const {isActive, handleActive} = useActive();

    return (
        <section hidden = {currentTab !== TabValues.CATEGORIA}>
            <AddButton text = "Crear categoría" onSubmit = {handleActive} />
            {isActive && <CategoryModalForm />}
        </section>
    )
}

export default CategoryDashboard;