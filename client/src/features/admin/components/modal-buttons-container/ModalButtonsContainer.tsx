import './styles/modal-buttons-container.css';

export interface ButtonsContainerProps {
    onActive: () => void
}

const ModalButtonsContainer = ({onActive}: ButtonsContainerProps) => {
    return (
        <section className = 'modal-form_buttons'>
            <button 
                onClick = {onActive} 
                className = 'modal-form--cancel'>CANCELAR
            </button>
            <button 
                type = 'submit' 
                className = 'modal-form--add'>CONFIRMAR
            </button>
        </section>
    )
}

export default ModalButtonsContainer;