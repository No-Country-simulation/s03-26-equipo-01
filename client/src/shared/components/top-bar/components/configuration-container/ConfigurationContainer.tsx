import type { ConfigurationContainerProps } from "./configuration-container";
import dropboxIcon from '../../../../../assets/dropbox-icon-grey.svg';
import './styles/configuration-container.css';

const ConfigurationContainer = ({user, onActive}: ConfigurationContainerProps) => {
    return (
        <div className = 'user-configuration-data_presentation' onClick = {onActive}>
            <p>{user?.firstName} {user?.lastName}</p>
            <figure>
                <img src = {dropboxIcon} alt = 'icono desplegable para ver datos del usuario' />
            </figure>
        </div>
    )
}

export default ConfigurationContainer;