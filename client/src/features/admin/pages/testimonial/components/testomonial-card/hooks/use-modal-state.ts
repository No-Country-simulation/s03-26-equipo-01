import { useState } from "react";
import type { StateTestimonial } from "../../../../../models/state";

const useChangeState = () => {
    
    const [id, setId] = useState<number | null>(null);
    const [state, setState] = useState<StateTestimonial | null>(null);
    const [isDiscart, setIsDiscart] = useState<boolean>(false);

    const changeToAproved = (id: number) => {
        setId(id);
        setState('Aprobado');
    }

    const changeToDraft = (id: number) => {
        setId(id);
        setState('Borrador');
    }

    const changeToPublished = (id: number) => {
        setId(id);
        setState('Publicado');
    }

    const changeToDiscart = (id: number) => {
        setId(id);
        setIsDiscart(true);
    }

    const refresh = () => {
        setId(null);
        setState(null)
        setIsDiscart(false)
    }

    const isState = (adminState: StateTestimonial) => state === adminState;

    return { id, isDiscart, isState, changeToDiscart, changeToPublished, changeToAproved, changeToDraft, refresh }
}

export default useChangeState;