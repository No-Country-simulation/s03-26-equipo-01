import type { ButtonsContainerProps, ButtonsDeleteContainerProps } from './modal-buttons-container';
import './styles/modal-buttons-container.css';

export const ButtonsCommitContainer = ({onClose, onAcept}: ButtonsContainerProps) => {
    return (
        <section className = 'commit-form_buttons'>
            <button 
                type = 'button'
                onClick = {onClose} 
                className = 'commit-form--cancel'>CANCELAR
            </button>
            <button 
                onClick = {onAcept}
                type = 'submit' 
                className = 'commit-form---commit'>CONFIRMAR
            </button>
        </section>
    )
}

export const ButtonsDeleteContainer = ({onActive, onDelete}: ButtonsDeleteContainerProps) => {
    return (
        <section className = 'commit-form_buttons'>
            <button 
                type = 'button'
                onClick = {onActive} 
                className = 'commit_delete--cancel'>CANCELAR
            </button>
            <button 
                type = 'submit' 
                onClick = {onDelete}
                className = 'commit_delete--commit'>CONFIRMAR
            </button>
        </section>
    )
}

