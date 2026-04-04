import type { SelectInputData } from "../../../../../../shared/types/select-input-data/select-input-data";
import type TestimonialResources from "../../../../models/testimonial-resources";

const createSelectInputData = (adminResources: TestimonialResources): SelectInputData[] => {
    return [
        {
            id: 1,
            name: 'categoria',
            placeholder: 'Categoría',
            content: adminResources.category.map(category => category.name),
            type: 'category'
        },
        {
            id: 2,
            name: 'editor',
            placeholder: 'Editor',
            content: adminResources.users.map(user => user.completeName), 
            type: 'editorId'
        },
        {
            id: 3,
            name: 'status',
            placeholder: 'Estado',
            content: adminResources.states,
            type: 'status'
        }
    ]
}

export default createSelectInputData;