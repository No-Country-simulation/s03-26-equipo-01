import type { TabContent } from "../../../../components/tab-container/tab-container";
import { TabValues } from "../../../../components/tab-container/tab-values";

const TagDashboard = ({currentTab}: TabContent) => {
    return (
        <p hidden = {currentTab !== TabValues.TAG}>tags</p>
    )
}

export default TagDashboard;