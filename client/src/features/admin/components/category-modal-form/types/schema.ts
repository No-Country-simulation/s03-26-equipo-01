import { object, string, type ObjectSchema } from "yup";
import type { CategoryCreated } from "../model/category-created";

const schema: ObjectSchema<CategoryCreated> = object().shape({
    name: 
        string()
        .required("El campo es requerido")
        .min(3)
})

export default schema;