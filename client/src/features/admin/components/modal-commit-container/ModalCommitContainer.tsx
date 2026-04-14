import type React from "react";
import './styles/modal-commit-container.css';

interface ModalCommitContainerProps {
    children: React.ReactNode
}
const ModalCommitContainer = ({children}: ModalCommitContainerProps) => {
    return (
        <div className = 'modal-commit-container'>
            <div>
                {children}
            </div>
        </div>
    )
}

export default ModalCommitContainer;