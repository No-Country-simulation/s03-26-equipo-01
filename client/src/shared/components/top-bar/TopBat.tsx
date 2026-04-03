import useUser from '../../user/hooks/use-user';
import UserConfigurationData from './components/user-configuration-data/UserConfigurationData';
import './styles/top-bar.css';

const TopBar = () => {

    const {user} = useUser();

    return (
        <header className = 'top-bar'>
            <UserConfigurationData user = {user} />
        </header>
    )
}

export default TopBar;