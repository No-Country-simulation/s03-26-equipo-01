export interface RejectModalProps {
    onChangeState: (id: number, closeModal: () => void) => void
    onClose: (close: () => void) => void
    id: number
}