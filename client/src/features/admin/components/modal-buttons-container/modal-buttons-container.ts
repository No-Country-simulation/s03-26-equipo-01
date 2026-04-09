export interface ButtonsContainerProps {
    onActive: () => void
}

export interface ButtonsDeleteContainerProps {
    onActive: () => void
    onDelete: () => Promise<void>
}