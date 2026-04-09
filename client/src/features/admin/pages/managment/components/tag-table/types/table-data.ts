import type { TableDataContent } from "../../../../../../../shared/types/table/table";
import type { Tag } from "../../../../../models/tag";

const headers = ['ID', 'NAME'];

const tableData = (tags: Tag[]): TableDataContent => {

    return {
        headers: headers,
        rows: tags.map(tag => {
            return {
                fields: Object.values(tag),
                id: tag.id 
            }
        })
    }
}

export default tableData;