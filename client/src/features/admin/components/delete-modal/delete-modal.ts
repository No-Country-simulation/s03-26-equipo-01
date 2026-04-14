export interface DeleteModalProps {
    onDelete: (id: number, onClose: () => void) => void
    id: number
}