export interface DeleteModalProps {
    onDelete: (id: number, onClose: () => void) => void
    onClose?: () => void
    id: number
}