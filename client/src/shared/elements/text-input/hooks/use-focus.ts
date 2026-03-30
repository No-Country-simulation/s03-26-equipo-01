import { useState } from "react";

const useFocus = () => {

    const [onFocus, setOnfocus] = useState<boolean>(false);
    const [contentCount, setContentCount] = useState<number>(0);

    const handleCounter = () => setContentCount(value => value + 1);
    const changeFocus = () => isEmpty() && setOnfocus(!onFocus);
    const isEmpty = () => contentCount === 0;

    return {onFocus, changeFocus, handleCounter}
}

export default useFocus;