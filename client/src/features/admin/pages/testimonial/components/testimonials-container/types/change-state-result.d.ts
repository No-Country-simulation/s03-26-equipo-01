import type { SelectState } from "./select-state";

export interface ChangeStateResult {
    isError: boolean; 
    state: SelectState
}