import ModalContainer from "../../../../../../shared/components/modal-container/ModalContainer";
import useActive from "../../../../../../shared/hooks/use-active";
import { ButtonsCommitContainer } from "../../../../components/modal-buttons-container/ModalButtonsContainer";
import ModalTitleContainer from "../../../../components/modal-title-form/ModalTitleContainer";


export interface PublishedModalProps {
    onAcept: (id: number) => void
    id: number
}

const PublishedModal = ({onAcept, id}: PublishedModalProps) => {

    const {isActive, handleActive} = useActive();
    
    return (
        <ModalContainer disable = {isActive}>
            <div>
                <ModalTitleContainer title = "¿Publicar Testimonio?" />
                <p>Este testimonio será visible para todos los lectores del sitio.</p>
                <ButtonsCommitContainer onClose = {handleActive} onAcept = {() => onAcept(id)} />
            </div>
        </ModalContainer>
    )
}

export default PublishedModal;