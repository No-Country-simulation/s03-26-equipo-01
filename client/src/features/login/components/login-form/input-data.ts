import type { InputTextData } from "../../../../shared/types/input-text-data";

const inputsData: InputTextData[] = [
    {
        required: true,
        label: 'Ingresa su nombre de usuario',
        id: "1",
        name: 'userName',
        type: 'text'
    },
    {
        required: true,
        label: 'Ingresa su contraseña',
        id: "2",
        name: 'password',
        type: 'password'
    }
];

export default inputsData;