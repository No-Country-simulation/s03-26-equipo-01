import { useState } from "react";
import type { FilterData } from "../types/filter-data/filter-data";

const useFilter = () => {

    const [filterData, setFilterData] = useState<FilterData>({
        category: '',
        status: '',
        editorName: '',
        date: ''
    });

    function updateFilter(value: string, type: keyof FilterData) {      
        const newFilter = {
            ...filterData,
            [type]: value
        }  
        setFilterData(newFilter);
        return newFilter;
    }

    return {filterData, addData: updateFilter}
}

export default useFilter;