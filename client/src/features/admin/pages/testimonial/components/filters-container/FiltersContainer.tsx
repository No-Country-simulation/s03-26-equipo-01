import SelectInput from "../../../../../../shared/elements/select-input/SelectInput";
import useFilter from "../../../../../../shared/hooks/use-filter-data";
import type { FilterData } from "../../../../../../shared/types/filter-data/filter-data";
import createSelectInputData from "./create-select-input-data";
import type { FiltersContainerProps } from "./filters-container";
import './styles/filters-container.css';

const FiltersContainer = ({adminResources, onFilter}: FiltersContainerProps) => {

    const selectInputData = createSelectInputData(adminResources);  
    const {addData} = useFilter();
    const handleFilter = (value: string, type: keyof FilterData) => onFilter(addData(value, type));
    
    return (
        <section className = 'admin-filters-container falling-container'>
            <h3>filtrar por</h3>
            <form>
                {selectInputData.map(selectInputData => 
                    <SelectInput
                        key = {selectInputData.id} 
                        selectInputData = {selectInputData} 
                        handleSubmit = {handleFilter} />
                )}
            </form>
        </section>
    )
}

export default FiltersContainer;