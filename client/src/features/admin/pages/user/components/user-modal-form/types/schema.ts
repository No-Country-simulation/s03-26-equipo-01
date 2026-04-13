import { object, string, type ObjectSchema } from "yup";
import type { CreatedUser } from "../../../model/created-user";


const schema: ObjectSchema<CreatedUser> = object().shape({
    firstName: 
        string().
        min(4).
        required('el campo es requerido'),
    lastName: 
        string().
        min(2).
        required('el campo es requerido'),
    email: 
        string().
        email('el campo debe ser un email').
        required('el campo es requerido'),
    password: 
        string().
        required('el campo es requerido')
});

export default schema;