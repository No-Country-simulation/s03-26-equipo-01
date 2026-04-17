import TableEditData from "../../../../../../shared/components/table-container/TableContainer";
import useActive from "../../../../../../shared/hooks/use-active";
import AddButton from "../../../../components/add-button/AddButton";
import DeleteModal from "../../../../components/delete-modal/DeleteModal";
import type { TabContent } from "../../../../components/tab-container/tab-container";
import { TabValues } from "../../../../components/tab-container/tab-values";
import TagModalForm from "../../../../components/tag-modal-form/TagModalForm";
import useTag from "../../hooks/use-tag";
import TableButtons from "../table-buttons/TableButtons";
import './styles/tab-table.css';
import tableData from "./types/table-data";

const TagSubPanel = ({currentTab}: TabContent) => {
    
    const addActive = useActive();
    const editActive = useActive();
    const deleteActive = useActive();
    const {tags, addTag, editTag, deleteTag} = useTag();
    const handleEdit = (id: number) => editActive.handleActive(id);
    const handleDelete = (id: number) => deleteActive.handleActive(id);

    return (
        <section hidden = {currentTab !== TabValues.TAG} className = 'tag-subpanel-container'>
            <AddButton text = "CREAR TAG" onSubmit = {addActive.handleActive} />
            {addActive.isActive && <TagModalForm onSubmit = {addTag} />}
            {editActive.isActive && <TagModalForm onSubmit = {(tag) => editTag(tag, editActive.id)} />}
            {deleteActive.isActive && <DeleteModal onDelete = {deleteTag} id = {deleteActive.id as number}/>}
            <TableEditData 
                tableData = {tableData(tags)}>
                {(id: number) => <TableButtons onEdit={handleEdit} onDelete={handleDelete} id={id} />}
            </TableEditData>
        </section>
    )
}

export default TagSubPanel;