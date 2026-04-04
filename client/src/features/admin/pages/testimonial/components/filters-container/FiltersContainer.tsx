import SelectInput from "../../../../../../shared/elements/select-input/SelectInput";
import createSelectInputData from "./create-select-input-data";
import type { FiltersContainerProps } from "./filters-container";
import './styles/filters-container.css';

const FiltersContainer = ({adminResources}: FiltersContainerProps) => {

    const selectInputData = createSelectInputData(adminResources);  
    const handleSubmit = (value: string, type: string) => console.log(value, type)

    return (
        <section className = 'admin-filters-container'>
            <h3>filtrar por</h3>
            <form>
                {selectInputData.map(selectInputData => 
                    <SelectInput
                        key = {selectInputData.id} 
                        selectInputData = {selectInputData} 
                        handleSubmit = {handleSubmit} />
                )}
            </form>
        </section>
    )
}

export default FiltersContainer;