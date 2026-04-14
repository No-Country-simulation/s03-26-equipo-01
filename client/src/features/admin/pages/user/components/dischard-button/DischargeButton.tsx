import type { DischargeButtonProps } from './dischard-button';
import './dischard-button.css';

const DischargeButton = ({onActive, id}: DischargeButtonProps) => {
    return (
        <button onClick={() => onActive(id)} className = 'dischard-button'>Dar de alta</button>
    )
}

export default DischargeButton;