import TableEditData from "../../../../../../shared/components/table-container/TableContainer";
import useActive from "../../../../../../shared/hooks/use-active";
import AddButton from "../../../../components/add-button/AddButton";
import CategoryModalForm from "../../../../components/category-modal-form/CategoryModalForm";
import DeleteModal from "../../../../components/delete-modal/DeleteModal";
import type { TabContent } from "../../../../components/tab-container/tab-container";
import { TabValues } from "../../../../components/tab-container/tab-values";
import useCategory from "../../hooks/use-category";
import './styles/category-table.css';
import tableData from "./types/category-table";

const CategorySubPanel = ({currentTab}: TabContent) => {
    
    const addActive = useActive();
    const editActive = useActive();
    const deleteActive = useActive();
    const {categories, addCategory, editCategory, deleteCategory} = useCategory();
    const handleEdit = (id: number) => editActive.handleActive(id);
    const handleDelete = (id: number) => deleteActive.handleActive(id);

    return (
        <section hidden = {currentTab !== TabValues.CATEGORIA} className = 'category-subpanel-container'>
            <AddButton text = "CREAR CATEGORIA" onSubmit = {addActive.handleActive} />
            {addActive.isActive && <CategoryModalForm onSubmit = {addCategory} />}
            {editActive.isActive && <CategoryModalForm onSubmit = {(category) => editCategory(category, editActive.id)} />}
            {deleteActive.isActive && <DeleteModal onDelete = {deleteCategory} id = {deleteActive.id}/>}
            <TableEditData 
                tableData = {tableData(categories)}
                activeEdit = {handleEdit}
                activeDelete = {handleDelete}
            />
        </section>
    )
}

export default CategorySubPanel;