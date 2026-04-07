export interface AddButtonProps {
    text: string
    onSubmit: () => void
}

const AddButton = ({text, onSubmit}: AddButtonProps) => {
    return (
        <button onClick = {onSubmit}>
            {text}
        </button>
    )
}

export default AddButton;