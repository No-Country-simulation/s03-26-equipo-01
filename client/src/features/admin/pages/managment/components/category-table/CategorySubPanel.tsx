import TableEditData from "../../../../../../shared/components/table-container/TableContainer";
import AddButton from "../../../../components/add-button/AddButton";
import CategoryModalForm from "../../../../components/category-modal-form/CategoryModalForm";
import DeleteModal from "../../../../components/delete-modal/DeleteModal";
import type { TabContent } from "../../../../components/tab-container/tab-container";
import { TabValues } from "../../../../components/tab-container/tab-values";
import useCategory from "../../hooks/use-category";
import useModalActive from "../../hooks/use-modal-active";
import TableButtons from "../table-buttons/TableButtons";
import './styles/category-table.css';
import tableData from "./types/category-table";

const CategorySubPanel = ({currentTab}: TabContent) => {
    
    const {add, edit, deleted} = useModalActive();
    const {categories, addCategory, editCategory, deleteCategory} = useCategory();

    return (
        <section hidden = {currentTab !== TabValues.CATEGORIA} className = 'category-subpanel-container'>
            <AddButton text = "CREAR CATEGORIA" onSubmit = {add.handle} />
            {add.isActive && <CategoryModalForm onSubmit = {addCategory} onClose = {add.onClose} />}
            {edit.isActive && <CategoryModalForm onSubmit = {(category) => editCategory(category, edit.id)} onClose = {edit.onClose} />}
            {deleted.isActive && <DeleteModal onDelete = {deleteCategory} onClose = {deleted.onClose} id = {deleted.id as number} />}
            <TableEditData 
                tableData = {tableData(categories)}>
                {(id: number) => <TableButtons onEdit={edit.handle} onDelete={deleted.handle} id={id} />}
            </TableEditData>
        </section>
    )
}

export default CategorySubPanel;