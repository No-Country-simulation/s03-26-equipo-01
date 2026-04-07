import type { FieldValues, UseFormRegister } from "react-hook-form";
import type { InputTextData } from "../../../../shared/types/input-text-data";
import TextInput from "../../../../shared/elements/text-input/TextInput";
import './styles/modal-input-container.css';

interface InputsContainerProps<T extends FieldValues> {
    warningText: string
    inputTextData: InputTextData
    register: UseFormRegister<T>
    errorMessage?: string
}

const ModalInputsContainer = <T extends FieldValues>({warningText, inputTextData, register, errorMessage}: InputsContainerProps<T>) => {
    return (
        <section className = 'modal-form_inputs'>
            <TextInput 
                inputTextData = {inputTextData}
                register = {register}
                error = {errorMessage}
            />
            <p>{warningText}</p>
        </section>
    )
}

export default ModalInputsContainer;