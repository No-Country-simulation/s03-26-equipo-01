import './styles/modal-title-form.css';

interface ModalTitleContainerProps {
    title: string
}

const ModalTitleContainer = ({title}: ModalTitleContainerProps) => {
    return (
        <section className = 'modal-form_title'>
            <h3>{title}</h3>
        </section>
    )
}

export default ModalTitleContainer