import { yupResolver } from "@hookform/resolvers/yup";
import ModalContainer from "../../../../../../shared/components/modal-container/ModalContainer";
import { useForm } from "react-hook-form";
import ModalTitleContainer from "../../../../components/modal-title-form/ModalTitleContainer";
import { ButtonsCommitContainer } from "../../../../components/modal-buttons-container/ModalButtonsContainer";
import type { UserModalFormProps } from "./user-modal-form";
import type { CreatedUser } from "../../model/created-user";
import useActive from "../../../../../../shared/hooks/use-active";
import inputTextData from "./types/input-text-data";
import TextInput from "../../../../../../shared/elements/text-input/TextInput";
import schema from "./types/schema";
import './styles/user-modal-form.css';

const UserModalForm = ({onSubmit}: UserModalFormProps) => {

    const {register, handleSubmit, formState: {errors}} = useForm<CreatedUser>({
        resolver: yupResolver(schema)
    });
    const handleClick = (categoryCreated: CreatedUser) => onSubmit(categoryCreated);
    const {isActive, handleActive} = useActive();

    return (
        <ModalContainer disable = {isActive}>
            <form onSubmit = {handleSubmit(handleClick)} className = 'users-modal-form-container'>
                <div>
                    <ModalTitleContainer title = "Nuevo Usuario" />
                    {inputTextData.map(inputData => 
                        <TextInput
                            key = {inputData.id}
                            register = {register}
                            inputTextData = {inputData}
                            error = {errors[inputData.name as keyof CreatedUser]?.message}
                        />
                    )}
                    <ButtonsCommitContainer onClose = {handleActive} />
                </div>
            </form>
        </ModalContainer>
    )
}



export default UserModalForm;