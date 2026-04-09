import './edit-button.css';
import editButton from '../../../assets/edit-icon/edit-icon.svg'
import type { EditButtonProps } from './edit-button';

const EditButton = ({onSubmit, id}: EditButtonProps) => {
    return (
        <div className = 'edit-button_container'>
            <img src={editButton} alt="" />
            <button onClick = {() => onSubmit(id)}>EDITAR</button>
        </div>
    )
}

export default EditButton