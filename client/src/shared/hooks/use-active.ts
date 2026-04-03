import { useState } from "react";

const useActive = () => {

    const [isActive, setIsActive] = useState<boolean>(false);
    
    const handleActive = () => setIsActive(!isActive);

    return {isActive, handleActive}
}

export default useActive;