import useAuthContext from '../../auth/context/use-auth-context';
import UserConfigurationData from './components/user-configuration-data/UserConfigurationData';
import './styles/top-bar.css';

const TopBar = () => {

    const {logout, user} = useAuthContext();

    return (
        <header className = 'top-bar'>
            <UserConfigurationData 
                user = {user} 
                logout = {logout}
            />
        </header>
    )
}

export default TopBar;