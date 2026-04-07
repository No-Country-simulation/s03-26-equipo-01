import { object, string, type ObjectSchema } from "yup";
import type { CreatedTag } from "../../../adapters/tag/dtos/create-tag";

const schema: ObjectSchema<CreatedTag> = object().shape({
    name: 
        string()
        .required("El campo es requerido")
        .min(3)
})

export default schema;