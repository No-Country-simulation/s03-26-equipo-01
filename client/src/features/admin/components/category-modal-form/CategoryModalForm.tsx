import { useForm } from "react-hook-form";
import type { CategoryModalFormProps } from "./category-model-form";
import schema from "./types/schema";
import type { CategoryCreated } from "../../adapters/category/dtos/category-created";
import { yupResolver } from "@hookform/resolvers/yup";
import inputTextData from "./types/input-text-data";
import ModalContainer from "../../../../shared/components/modal-container/ModalContainer";
import './styles/category-model-form.css';
import useActive from "../../../../shared/hooks/use-active";
import ModalTitleContainer from "../modal-title-form/ModalTitleContainer";
import ModalInputsContainer from "../modal-input-container/ModalInputContainer";
import {ButtonsCommitContainer} from "../modal-buttons-container/ModalButtonsContainer";

const CategoryModalForm = ({onSubmit}: CategoryModalFormProps) => {

    const {register, handleSubmit, formState: {errors}} = useForm<CategoryCreated>({
        resolver: yupResolver(schema)
    });
    const handleClick = (categoryCreated: CategoryCreated) => onSubmit(categoryCreated);
    const {isActive, handleActive} = useActive();

    return (
        <ModalContainer disable = {isActive}>
            <form onSubmit = {handleSubmit(handleClick)} className = 'category-modal-form-container'>
                <div>
                    <ModalTitleContainer title = "Nueva Categoria" />
                    <ModalInputsContainer 
                        register = {register}
                        errorMessage = {errors.name?.message}
                        inputTextData = {inputTextData}
                        warningText = "Crea categorias amplias, no específicas."
                    />
                    <ButtonsCommitContainer onActive = {handleActive} />
                </div>
            </form>
        </ModalContainer>
    )
}



export default CategoryModalForm;