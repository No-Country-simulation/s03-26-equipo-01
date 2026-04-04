

export interface SelectInputData {
    id: number 
    name: string 
    placeholder: string 
    content: string[]
    type: 'category' | 'tag' | 'editorName' | 'status' | 'date'
}