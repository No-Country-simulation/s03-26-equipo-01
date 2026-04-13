import DeleteButton from "../../../../../../shared/elements/delete-button/DeleteButton"
import EditButton from "../../../../../../shared/elements/edit-button/EditButton"


interface TableButtonsProps {
    onEdit: (id: number) => void
    onDelete: (id: number) => void
    id: number
}
const TableButtons = ({onEdit, onDelete, id}: TableButtonsProps) => {
    return (
        <>
            <EditButton onSubmit = {onEdit} id={id} />
            <DeleteButton onSubmit = {onDelete} id={id} />
        </>
    )
}
export default TableButtons;