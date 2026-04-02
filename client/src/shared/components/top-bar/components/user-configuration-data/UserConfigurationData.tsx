import type { User } from "../../../../types/user/user";
import './user-configuration-data.css'
import dropboxIcon from '../../../../../assets/dropbox-icon.svg';


interface UserConfigurationDataProps {
    user: User | null
}

const UserConfigurationData = ({user}: UserConfigurationDataProps) => {
    return (
        <div className = 'user-configuration-data'>
            <p>{user?.firstName} {user?.lastName}</p>
            <figure>
                <img src = {dropboxIcon} alt = 'icono desplegable para ver datos del usuario' />
            </figure>
        </div>
    )
}

export default UserConfigurationData;