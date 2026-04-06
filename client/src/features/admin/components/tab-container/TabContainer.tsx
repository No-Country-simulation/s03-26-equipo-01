import { Tab, Tabs } from "@mui/material";
import type { TabContainerProps } from "./tab-container";
import { useState } from "react";
import { TabValues } from "./tab-values";

const TabContainer = ({children, onTab}: TabContainerProps) => {
    
    const [index, setIndex] = useState<number>(0);

    const handleChange = (_: React.SyntheticEvent, newValue: number) => {
        setIndex(newValue);
        onTab(newValue === 0 ? TabValues.CATEGORIA : TabValues.TAG);
    }

    return (
        <section>
            <Tabs onChange = {handleChange} value = {index}>
                <Tab label = {TabValues.CATEGORIA} />
                <Tab label = {TabValues.TAG} />
            </Tabs>
            {children}
        </section>
    )
}

export default TabContainer;