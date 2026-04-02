import UserConfigurationData from './components/UserConfigurationData';
import './styles/top-bar.css';

const TopBar = () => {
    return (
        <header className = 'top-bar'>
            <UserConfigurationData />
        </header>
    )
}

export default TopBar;