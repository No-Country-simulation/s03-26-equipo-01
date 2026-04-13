import type { InputTextData } from "../../../../../../../shared/types/input-text-data";

const inputTextData: InputTextData[] = [
    {
        type: 'text',
        required: true,
        label: 'primer nombre',
        placeholder: 'Primer nombre',
        id: '1',
        name: 'firstName',
    }, 
    {
        type: 'text',
        required: true,
        label: 'apellido',
        placeholder: 'Apellido',
        id: '2',
        name: 'lastName',
    }, 
    {
        type: 'email',
        required: true,
        label: 'email',
        placeholder: 'Email',
        id: '3',
        name: 'email',
    }, 
    {
        type: 'password',
        required: true,
        label: 'password',
        placeholder: 'Contraseña',
        id: '4',
        name: 'password',
    }, 
];

export default inputTextData;