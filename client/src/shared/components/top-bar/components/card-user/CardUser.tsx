import type { CardUserDataProps, UserDataSectionProps, UserDropboxSectionProps } from "./card-user";
import helpIcon from '../../../../../assets/config-icon/help-icon.svg';
import logoutIcon from '../../../../../assets/config-icon/logout-icon.svg';
import './styles/card-user.css';
import type { User } from "../../../../user/models/user";

const CardUserData = ({user, logout}: CardUserDataProps) => {
    return (
        <div className = 'user-configuration-dropbox falling-container'>
            <UserDataSection 
                user = {user} 
            />
            <UserDropboxSection 
                user = {user}
                logout = {logout}    
            />
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

const UserDropboxSection = ({user, logout}: UserDropboxSectionProps) => {
    return (
        <section className = 'user-configuration-dropbox_loguot'>
            <div className = 'help-dropbox-container'>
                <img src = {helpIcon} alt = "icono de ayuda para recuperar la cuenta" />
                <button>ayuda</button>
            </div>
            <div className = 'logout-dropbox-container'>
                <img src = {logoutIcon} alt = "icono para cerrera sesión" />
                <button onClick = {() => logout(user as User)} className = 'user-configuration-dropbox_loguot--button'>Cerrar sesión</button>
            </div>
        </section>
    )
}

export default CardUserData;