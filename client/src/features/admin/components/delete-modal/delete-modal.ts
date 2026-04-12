export interface DeleteModalProps {
    onDelete: (id?: number) => Promise<void>
    id?: number
}