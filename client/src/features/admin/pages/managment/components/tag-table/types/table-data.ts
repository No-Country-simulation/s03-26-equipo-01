import type { Table } from "../../../../../../../shared/types/table/table";
import type { CreatedTag } from "../../../../../adapters/tag/dtos/create-tag";

const headers = ['id', 'name'];

const tableData = (tags: CreatedTag[]): Table => {

    return {
        headers: headers,
        rows: tags.map(tag => Object.values(tag))
    }
}

export default tableData;