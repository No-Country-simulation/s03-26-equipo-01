import type { TabContent } from "../../../../components/tab-container/tab-container";
import { TabValues } from "../../../../components/tab-container/tab-values";

const CategoryDashboard = ({currentTab}: TabContent) => {
    return (
        <p hidden = {currentTab !== TabValues.CATEGORIA}>categorias</p>
    )
}

export default CategoryDashboard;