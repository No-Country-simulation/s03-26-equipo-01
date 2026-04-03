import { useState } from "react";

const useActive = () => {

    const [isActive, setIsActive] = useState<boolean>(false);
    const [id, setId] = useState<number | undefined>(undefined);
    
    const handleActive = (id?: number) => {
        setId(id);
        setIsActive(!isActive);
    }

    const isElementActive = (elementId: number) => id === elementId;

    return {id, isElementActive, isActive, handleActive}
}

export default useActive;