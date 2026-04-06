import type { Editor } from "../../models/editor";
import type { EditorResponse } from "./dtos/response";

export function editorsAdapter(response: EditorResponse[]): Editor[] {
    return response.map(editor => editorAdapter(editor));
}

export function editorAdapter(response: EditorResponse): Editor {
    return {
        id: response.id,
        completeName: response.firstName + ' ' + response.lastName
    }
}