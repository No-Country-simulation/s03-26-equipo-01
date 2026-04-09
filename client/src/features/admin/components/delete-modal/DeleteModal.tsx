import ModalContainer from "../../../../shared/components/modal-container/ModalContainer";
import useActive from "../../../../shared/hooks/use-active";
import { ButtonsDeleteContainer } from "../modal-buttons-container/ModalButtonsContainer";
import ModalTitleContainer from "../modal-title-form/ModalTitleContainer";
import type { DeleteModalProps } from "./delete-modal";
import './styles/delete-modal.css';

const DeleteModal = ({onDelete, id}: DeleteModalProps) => {

    const handleCommit = () => onDelete(id);
    const {isActive, handleActive} = useActive();

    return (
        <ModalContainer disable = {isActive}>
            <div className = 'delete-modal_container'>
                <div>
                <ModalTitleContainer title = "¿Estas seguro que quieres eliminar de forma permanente?" />
                <ButtonsDeleteContainer 
                    onActive = {handleActive} 
                    onDelete = {handleCommit}
                />
                </div>
            </div>
        </ModalContainer>
    )
}

export default DeleteModal;