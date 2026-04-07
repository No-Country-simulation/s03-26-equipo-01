import type { ReactNode } from 'react'
import './styles/modal-container.css'

interface ModalContainerProps {
    disable: boolean
    children: ReactNode
}

const ModalContainer = ({disable, children}: ModalContainerProps) => {
    return (
        <div className = {disable ? 'modal-form-container_disable' : 'modal-form-container falling-container'}>
            {children}
        </div>
    )
}

export default ModalContainer;