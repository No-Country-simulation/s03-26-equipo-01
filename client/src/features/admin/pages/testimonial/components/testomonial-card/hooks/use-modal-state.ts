import { useState } from "react";
import type { SelectState } from "../types/select-state";

const useSelectState = () => {
    
    const [id, setId] = useState<number | null>(null);
    const [state, setState] = useState<SelectState | null>(null);

    const selectTo = (id: number, state: SelectState) => {
        setId(id);
        setState(state);
    }

    const refresh = () => {
        setId(null);
        setState(null)
    }

    const isState = (adminState: SelectState) => state === adminState;

    return { id, isState, selectTo, refresh }
}

export default useSelectState;