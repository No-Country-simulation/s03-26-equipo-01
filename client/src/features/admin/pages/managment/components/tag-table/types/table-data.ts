import type { TableDataContent } from "../../../../../../../shared/types/table/table";
import type { CreatedTag } from "../../../../../adapters/tag/dtos/create-tag";

const headers = ['ID', 'NAME'];

const tableData = (tags: CreatedTag[]): TableDataContent => {

    return {
        headers: headers,
        rows: tags.map(tag => Object.values(tag))
    }
}

export default tableData;