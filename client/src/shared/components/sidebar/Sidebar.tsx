import type { SideBarProps } from './side-dar';
import './styles/sidebar.css';
import MenuIcon from './components/menu-icon/MenuIcon';
import SideBarContent from './components/sidebar-content/SidebarContent';

const SideBar = ({ itemsData, urlBase, onActive, isActive}: SideBarProps) => {

    return (
        <section className = {isActive ? 'home-sidebar' : 'home-sidebar_disable'}>
            <MenuIcon onSubmit = {onActive} isActive = {isActive} />
            {isActive && <SideBarContent itemsData = {itemsData} urlBase = {urlBase} />}
        </section>
    )
}

export default SideBar;