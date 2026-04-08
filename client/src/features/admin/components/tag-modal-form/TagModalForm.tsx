import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import ModalContainer from "../../../../shared/components/modal-container/ModalContainer";
import useActive from "../../../../shared/hooks/use-active";
import ModalTitleContainer from "../modal-title-form/ModalTitleContainer";
import ModalInputsContainer from "../modal-input-container/ModalInputContainer";
import ModalButtonsContainer from "../modal-buttons-container/ModalButtonsContainer";
import type { TagModalFormProps } from "./tag-model-form";
import schema from "./types/schema";
import inputTextData from "./types/input-text-data";
import type { CreatedTag } from "../../adapters/tag/dtos/create-tag";
import './styles/tag-modal-form.css';

const TagModalForm = ({onSubmit}: TagModalFormProps) => {

    const {register, handleSubmit, formState: {errors}} = useForm<CreatedTag>({
        resolver: yupResolver(schema)
    });
    const handleClick = (categoryCreated: CreatedTag) => onSubmit(categoryCreated);
    const {isActive, handleActive} = useActive();

    return (
        <ModalContainer disable = {isActive}>
            <form onSubmit = {handleSubmit(handleClick)} className = 'tag-modal-form-container'>
                <div>
                    <ModalTitleContainer title = "Nuevo Tag" />
                    <ModalInputsContainer 
                        register = {register}
                        errorMessage = {errors.name?.message}
                        inputTextData = {inputTextData}
                        warningText = "Usa palabras que tu cliente usaría."
                    />
                    <ModalButtonsContainer onActive = {handleActive} />
                </div>
            </form>
        </ModalContainer>
    )
}



export default TagModalForm;