import type { UnsuscribeButtonProps } from './unsuscribe-button';
import './unsuscribe-button.css';

const UnsuscribeButton = ({onActive, id}: UnsuscribeButtonProps) => {
     return (
        <button onClick={() => onActive(id)} className = 'unsuscribe-button'>Dar de baja</button>
    )
}

export default UnsuscribeButton;