import { useState } from "react";
import type { SelectState } from "../../testimonials-container/types/select-state";

const useSelectState = () => {
    
    const [id, setId] = useState<number | null>(null);
    const [selectedState, setState] = useState<SelectState | null>(null);

    const selectTo = (id: number, state: SelectState) => {
        setId(id);
        setState(state);
    }

    const refresh = () => {
        setId(null);
        setState(null)
    }

    const isState = (adminState: SelectState) => selectedState === adminState;

    return { id, selectedState, isState, selectTo, refresh }
}

export default useSelectState;