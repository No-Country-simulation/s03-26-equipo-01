import { useForm } from "react-hook-form";
import type { ButtonsContainerProps, CategoryModalFormProps, InputsContainerProps } from "./category-model-form";
import TextInput from "../../../../shared/elements/text-input/TextInput";
import schema from "./types/schema";
import type { CategoryCreated } from "../../adapters/category/dtos/category-created";
import { yupResolver } from "@hookform/resolvers/yup";
import inputTextData from "./types/input-text-data";
import ModalContainer from "../../../../shared/components/modal-container/ModalContainer";
import './styles/category-model-form.css';
import useActive from "../../../../shared/hooks/use-active";

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
                    <TitleContainer />
                    <InputsContainer 
                        register = {register}
                        errorMessage = {errors.name?.message}
                    />
                    <ButtonsContainer onActive = {handleActive} />
                </div>
            </form>
        </ModalContainer>
    )
}

const TitleContainer = () => {
    return (
        <section className = 'category-modal-form_title'>
            <h3>Nuevo Categoria</h3>
        </section>
    )
}

const InputsContainer = ({register, errorMessage}: InputsContainerProps) => {
    return (
        <section className = 'category-modal-form_inputs'>
            <TextInput 
                inputTextData = {inputTextData}
                register = {register}
                error = {errorMessage}
            />
            <p>Crea categorias amplias, no específicas.</p>
        </section>
    )
}

const ButtonsContainer = ({onActive}: ButtonsContainerProps) => {
    return (
        <section className = 'category-modal-form_buttons'>
            <button onClick = {onActive}>Cancelar</button>
            <button>Confirmar</button>
        </section>
    )
}

export default CategoryModalForm;