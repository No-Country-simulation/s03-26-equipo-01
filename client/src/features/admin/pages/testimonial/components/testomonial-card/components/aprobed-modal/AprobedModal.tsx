import ModalContainer from "../../../../../../../../shared/components/modal-container/ModalContainer";
import useActive from "../../../../../../../../shared/hooks/use-active";
import { ButtonsCommitContainer } from "../../../../../../components/modal-buttons-container/ModalButtonsContainer";
import ModalCommitContainer from "../../../../../../components/modal-commit-container/ModalCommitContainer";
import ModalTitleContainer from "../../../../../../components/modal-title-form/ModalTitleContainer";
import type { AprobedModalProps } from "./aproved-modal";

const AprobedModal = ({onChangeState, onClose, id}: AprobedModalProps) => {

    const {isActive, handleActive} = useActive();

    const handleClose = () => onClose(handleActive)
    const handleAcept = () => onChangeState(id, handleActive)
    
    return (
        <ModalContainer disable = {isActive}>
            <ModalCommitContainer>
                <ModalTitleContainer title = "¿Aprobar Testimonio?" />
                <ButtonsCommitContainer onClose = {handleClose} onAcept = {handleAcept} />
            </ModalCommitContainer>
        </ModalContainer>
    )
}

export default AprobedModal;