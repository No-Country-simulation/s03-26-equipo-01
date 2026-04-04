import useAuth from '../../auth/hooks/use-auth';
import useUser from '../../user/hooks/use-user';
import UserConfigurationData from './components/user-configuration-data/UserConfigurationData';
import './styles/top-bar.css';

const TopBar = () => {

    const {user} = useUser();
    const {logout} = useAuth();

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