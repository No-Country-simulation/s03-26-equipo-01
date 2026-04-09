import type { Tag } from "../../../../../models/tag";

const headers = ['id', 'name'];

const tableData = (tags: Tag[]): TableData => {
    return {
        headers: headers
            
    }
}

interface TableData {
    headers: string[]
}

export default tableData;