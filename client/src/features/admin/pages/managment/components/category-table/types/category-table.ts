import type { TableDataContent } from "../../../../../../../shared/types/table/table";
import type { Category } from "../../../../../models/category";

const headers = ['ID', 'NAME', 'FECHA DE CREACIÓN'];

const tableData = (categories: Category[]): TableDataContent => {

    return {
        headers: headers,
        rows: categories.map(category => {
            return {
                fields: Object.values(category),
                id: category.id 
            }
        })
    }
}

export default tableData;