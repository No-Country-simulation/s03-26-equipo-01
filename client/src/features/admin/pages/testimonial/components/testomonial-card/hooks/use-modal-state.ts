import { useState } from "react";
import type { StateTestimonial } from "../../../../../models/state";


const useModalState = () => {
    const [id, setId] = useState<number>();
    const [state, setState] = useState<StateTestimonial>();
    const [isDiscart, setIsDiscart] = useState<boolean>(false);

    const openAprovedModal = (id: number) => {
        setId(id);
        setState('Aprobado');
    }

    const openDraftModal = (id: number) => {
        setId(id);
        setState('Borrador');
    }

    const openPublishModal = (id: number) => {
        setId(id);
        setState('Publicado');
    }

    const openDiscartModal = (id: number) => {
        setId(id);
        setIsDiscart(true);
    }

    const isState = (adminState: StateTestimonial) => state === adminState;

    return { id, isDiscart, isState, openDiscartModal, openPublishModal, openAprovedModal, openDraftModal }
}

export default useModalState;