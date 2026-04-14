import { useState } from "react";
import type { StateTestimonial } from "../../../../../models/state";


const useChangeState = () => {
    const [id, setId] = useState<number>();
    const [state, setState] = useState<StateTestimonial>();
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

    const isState = (adminState: StateTestimonial) => state === adminState;

    return { id, isDiscart, isState, changeToDiscart, changeToPublished, changeToAproved, changeToDraft }
}

export default useChangeState;