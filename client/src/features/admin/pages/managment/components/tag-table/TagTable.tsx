import type { TabContent } from "../../../../components/tab-container/tab-container";
import { TabValues } from "../../../../components/tab-container/tab-values";
import useTag from "../../../../hooks/use-tag";

const TagTable = ({currentTab}: TabContent) => {
    
    const {addTag, editTag, deleteTag} = useTag();

    return (
        <p hidden = {currentTab !== TabValues.TAG}>tags</p>
    )
}

export default TagTable;