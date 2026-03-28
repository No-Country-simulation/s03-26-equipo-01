import type { FieldValues, UseFormRegister } from "react-hook-form"
import type { InputTextData } from "../../types/input-text-data"


export interface TextInputProps<T extends FieldValues> {
    inputTextData: InputTextData
    error: string | undefined
    register: UseFormRegister<T>;
}