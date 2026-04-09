import type { DeleteButtonProps } from "./delete-button"
import deleteIcon from '../../../assets/delete-icon/delete-icon.svg';
import './delete-button.css';

const DeleteButton = ({onSubmit, id}: DeleteButtonProps) => {
    return (
        <div className = 'deleted-button_container'>
            <img src = {deleteIcon} alt="" />
            <button onClick = {() => onSubmit(id)}>ELIMINAR</button>
        </div>
    )
}

export default DeleteButton