import ModalContainer from "../../../../../../shared/components/modal-container/ModalContainer";
import useActive from "../../../../../../shared/hooks/use-active";
import { ButtonsCommitContainer } from "../../../../components/modal-buttons-container/ModalButtonsContainer";
import ModalTitleContainer from "../../../../components/modal-title-form/ModalTitleContainer";
import type { AprobedModalProps } from "./aproved-modal";

const AprobedModal = ({onAcept, id}: AprobedModalProps) => {

    const {isActive, handleActive} = useActive();
    
    return (
        <ModalContainer disable = {isActive}>
            <div>
                <ModalTitleContainer title = "Aprobar Testimonio?" />
                <ButtonsCommitContainer onClose = {handleActive} onAcept = {() => onAcept(id)} />
            </div>
        </ModalContainer>
    )
}

export default AprobedModal;