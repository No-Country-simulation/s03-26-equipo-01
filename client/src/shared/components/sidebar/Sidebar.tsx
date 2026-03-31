import LogoContainer from './components/logo-container/LogoContainer';
import NavegationList from './components/navegation-list/NavegationList';
import type { SideBarProps } from './side-dar';
import './sidebar.css';

const SideBar = ({ itemsData }: SideBarProps) => {
    return (
        <section className = 'home-sidebar'>
            <LogoContainer />
            <NavegationList itemsData = {itemsData} />
        </section>
    )
}

export default SideBar;