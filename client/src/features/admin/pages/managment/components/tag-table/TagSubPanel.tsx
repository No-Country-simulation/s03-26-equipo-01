import TableEditData from "../../../../../../shared/components/table-container/TableContainer";
import AddButton from "../../../../components/add-button/AddButton";
import DeleteModal from "../../../../components/delete-modal/DeleteModal";
import type { TabContent } from "../../../../components/tab-container/tab-container";
import { TabValues } from "../../../../components/tab-container/tab-values";
import TagModalForm from "../../../../components/tag-modal-form/TagModalForm";
import useTag from "../../hooks/use-tag";
import TableButtons from "../table-buttons/TableButtons";
import './styles/tab-table.css';
import tableData from "./types/table-data";
import useModalActive from "../../hooks/use-modal-active";

const TagSubPanel = ({currentTab}: TabContent) => {
    
    const {add, edit, deleted} = useModalActive();
    const {tags, addTag, editTag, deleteTag} = useTag();

    return (
        <section hidden = {currentTab !== TabValues.TAG} className = 'tag-subpanel-container'>
            <AddButton text = "CREAR TAG" onSubmit = {add.handle} />
            {add.isActive && <TagModalForm onSubmit = {addTag} onClose = {add.onClose} />}
            {edit.isActive && <TagModalForm onSubmit = {(tag) => editTag(tag, edit.id)} onClose = {edit.onClose} />}
            {deleted.isActive && <DeleteModal onDelete = {deleteTag} onClose = {deleted.onClose} id = {deleted.id as number} />}
            <TableEditData 
                tableData = {tableData(tags)}>
                {(id: number) => <TableButtons onEdit={edit.handle} onDelete={deleted.handle} id={id} />}
            </TableEditData>
        </section>
    )
}

export default TagSubPanel;