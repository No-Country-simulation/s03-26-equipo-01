import type { CardUserDataProps, UserDataSectionProps } from "./card-user";
import helpIcon from '../../../../../assets/config-icon/help-icon.svg';
import logoutIcon from '../../../../../assets/config-icon/logout-icon.svg';
import './styles/card-user.css';

const CardUserData = ({user}: CardUserDataProps) => {
    return (
        <div className = 'user-configuration-dropbox falling-container'>
            <UserDataSection user = {user} />
            <UserDropboxSection />
        </div>
    )
}

const UserDataSection = ({user}: UserDataSectionProps) => {
    return (
        <section className='user-configuration-dropbox_user'>
            <p className='user-configuration-dropbox_user--name'>{user?.firstName} {user?.lastName}</p>
            <p className='user-configuration-dropbox_user--email'>{user?.email}</p>
        </section>
    )
}

const UserDropboxSection = () => {
    return (
        <section className = 'user-configuration-dropbox_loguot'>
            <div className = 'help-dropbox-container'>
                <img src = {helpIcon} alt = "icono de ayuda para recuperar la cuenta" />
                <button>ayuda</button>
            </div>
            <div className = 'logout-dropbox-container'>
                <img src = {logoutIcon} alt = "icono para cerrera sesión" />
                <button className = 'user-configuration-dropbox_loguot--button'>Cerrar sesión</button>
            </div>
        </section>
    )
}

export default CardUserData;