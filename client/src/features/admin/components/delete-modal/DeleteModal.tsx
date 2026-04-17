import ModalContainer from "../../../../shared/components/modal-container/ModalContainer";
import useActive from "../../../../shared/hooks/use-active";
import { ButtonsDeleteContainer } from "../modal-buttons-container/ModalButtonsContainer";
import ModalTitleContainer from "../modal-title-form/ModalTitleContainer";
import type { DeleteModalProps } from "./delete-modal";
import './styles/delete-modal.css';

const DeleteModal = ({onDelete, id, onClose}: DeleteModalProps) => {

    const {isActive, handleActive} = useActive();
    const handleCommit = () => onDelete(id, handleActive);
    const handleClose = () => onClose && onClose();

    return (
        <ModalContainer disable = {isActive}>
            <div className = 'delete-modal_container'>
                <div>
                <ModalTitleContainer title = "¿Estas seguro que quieres eliminar de forma permanente?" />
                <ButtonsDeleteContainer 
                    onActive = {onClose ? handleClose : handleActive} 
                    onDelete = {handleCommit}
                />
                </div>
            </div>
        </ModalContainer>
    )
}

export default DeleteModal;