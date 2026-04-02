import './user-configuration-data.css'
import type { CardUserDataProps, ConfigurationContainerProps, UserConfigurationDataProps } from './user-configuration-data';
import dropboxIcon from '../../../../../assets/dropbox-icon-grey.svg';
import { useState } from 'react';

const UserConfigurationData = ({user}: UserConfigurationDataProps) => {

    const [isActive, setIsActive] = useState<boolean>(false);

    const handleActive = () => setIsActive(!isActive);

    return (
        <section className = 'user-configuration-data'>
            <ConfigurationContainer user = {user} onActive = {handleActive} />
            {isActive && <CardUserData user = {user} />}
        </section>
    )
}

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

const CardUserData = ({user}: CardUserDataProps) => {
    return (
        <div className = 'user-configuration-dropbox'>
            <section className = 'user-configuration-dropbox_user'>
                <p className = 'user-configuration-dropbox_user--name'>{user?.firstName} {user?.lastName}</p>
                <p className = 'user-configuration-dropbox_user--email'>{user?.email}</p>
            </section>
            <section className = 'user-configuration-dropbox_loguot'>
                <button>ayuda</button>
                <button className = 'user-configuration-dropbox_loguot--button'>Cerrar sesión</button>
            </section>
        </div>
    )
}



export default UserConfigurationData;