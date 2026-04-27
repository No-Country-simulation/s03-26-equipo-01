import ModalContainer from "../../../../../../../../shared/components/modal-container/ModalContainer";
import useActive from "../../../../../../../../shared/hooks/use-active";
import { ButtonsCommitContainer } from "../../../../../../components/modal-buttons-container/ModalButtonsContainer";
import ModalCommitContainer from "../../../../../../components/modal-commit-container/ModalCommitContainer";
import ModalTitleContainer from "../../../../../../components/modal-title-form/ModalTitleContainer";
import type { RejectModalProps } from "./reject-modal";

const RejectModal = ({onChangeState, onClose}: RejectModalProps) => {

    const {isActive, handleActive} = useActive();

    const handleClose = () => {
        handleActive();
        onClose();
    }
    const handleAcept = () => onChangeState()
    
    return (
        <ModalContainer disable = {isActive}>
            <ModalCommitContainer>
                <ModalTitleContainer title = "¿Rechazar Testimonio?" />
                <ButtonsCommitContainer onClose = {handleClose} onAcept = {handleAcept} />
            </ModalCommitContainer>
        </ModalContainer>
    )
}

export default RejectModal;