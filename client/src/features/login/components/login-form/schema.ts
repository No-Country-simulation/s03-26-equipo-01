import { object, ObjectSchema, string } from 'yup';
import type { AuthCredentials } from '../../../../shared/auth/models/user-credentials';


const schema: ObjectSchema<AuthCredentials> = object().shape({
    email: 
        string(). 
        email('El correo electrónico no es válido').
        required('Campo obligatorio'),
    password:
        string(). 
        required('Campo obligatorio'),
});

export default schema;