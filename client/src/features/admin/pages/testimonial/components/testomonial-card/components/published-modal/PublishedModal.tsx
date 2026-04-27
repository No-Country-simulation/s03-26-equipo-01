import ModalContainer from "../../../../../../../../shared/components/modal-container/ModalContainer";
import useActive from "../../../../../../../../shared/hooks/use-active";
import { ButtonsCommitContainer } from "../../../../../../components/modal-buttons-container/ModalButtonsContainer";
import ModalCommitContainer from "../../../../../../components/modal-commit-container/ModalCommitContainer";
import ModalTitleContainer from "../../../../../../components/modal-title-form/ModalTitleContainer";
import type { PublishedModalProps } from "./published-modal";
import './published-modal.css';
const PublishedModal = ({onClose, onChangeState}: PublishedModalProps) => {

    const {isActive, handleActive} = useActive();

    const handleClose = () => {
        handleActive();
        onClose();
    }
    const handleAcept = () => onChangeState()
    
    return (
        <ModalContainer disable = {isActive}>
            <ModalCommitContainer>
                <ModalTitleContainer title = "¿Publicar Testimonio?" />
                <p className = 'published-modal-message'>Este testimonio será visible para todos los lectores del sitio.</p>
                <ButtonsCommitContainer onClose = {handleClose} onAcept = {handleAcept} />
            </ModalCommitContainer>
        </ModalContainer>
    )
}

export default PublishedModal;