import type { ReactNode } from 'react'
import './styles/modal-container.css'

interface ModalContainerProps {
    disable: boolean
    children: ReactNode
}

const ModalContainer = ({disable, children}: ModalContainerProps) => {
    return (
        <section className = {disable ? 'modal-form-container_disable' : 'modal-form-container falling-container'}>
            {children}
        </section>
    )
}

export default ModalContainer;