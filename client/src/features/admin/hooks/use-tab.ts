import { useState } from "react";
import { TabValues } from "../components/tab-container/tab-values";

const useTab = () => {

    const [tabName, setTabName] = useState<TabValues>(TabValues.CATEGORIA);
    const handleTab = (tabName: TabValues) => setTabName(tabName);

    return {tabName, handleTab}
}

export default useTab;