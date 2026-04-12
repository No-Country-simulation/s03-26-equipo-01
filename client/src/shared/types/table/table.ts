export interface TableDataContent {
    headers: string[]
    rows: Row[]
}

interface Row {
    id: number 
    fields: string[]
}
