import useAuthContext from '../../auth/context/use-auth';
import UserConfigurationData from './components/user-configuration-data/UserConfigurationData';
import './styles/top-bar.css';

const TopBar = () => {

    const {user} = useAuthContext();

    return (
        <header className = 'top-bar'>
            <UserConfigurationData user = {user} />
        </header>
    )
}

export default TopBar;