import './styles/user-configuration-data.css';
import type { UserConfigurationDataProps } from './user-configuration-data';
import useActive from '../../../../hooks/use-active';
import CardUserData from '../card-user/CardUser';
import ConfigurationContainer from '../configuration-container/ConfigurationContainer';

const UserConfigurationData = ({user}: UserConfigurationDataProps) => {

    const {isActive, handleActive} = useActive();

    return (
        <section className = 'user-configuration-data'>
            <ConfigurationContainer user = {user} onActive = {handleActive} />
            {isActive && <CardUserData user = {user} />}
        </section>
    )
}

export default UserConfigurationData;