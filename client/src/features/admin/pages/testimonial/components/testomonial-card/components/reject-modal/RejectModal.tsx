import ModalContainer from "../../../../../../../../shared/components/modal-container/ModalContainer";
import useActive from "../../../../../../../../shared/hooks/use-active";
import { ButtonsCommitContainer } from "../../../../../../components/modal-buttons-container/ModalButtonsContainer";
import ModalTitleContainer from "../../../../../../components/modal-title-form/ModalTitleContainer";
import type { RejectModalProps } from "./reject-modal";

const RejectModal = ({onAcept, id}: RejectModalProps) => {

    const {isActive, handleActive} = useActive();
    
    return (
        <ModalContainer disable = {isActive}>
            <div>
                <ModalTitleContainer title = "Testimonio Rechazado" />
                <ButtonsCommitContainer onClose = {handleActive} onAcept = {() => onAcept(id)} />
            </div>
        </ModalContainer>
    )
}

export default RejectModal;