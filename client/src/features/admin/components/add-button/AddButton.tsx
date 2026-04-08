import type { AddButtonProps } from "./add-button";
import './styles/add-button.css';

const AddButton = ({text, onSubmit}: AddButtonProps) => {
    return (
        <button onClick = {onSubmit} className = 'add-admin-button'>
            {'+ ' + text}
        </button>
    )
}

export default AddButton;