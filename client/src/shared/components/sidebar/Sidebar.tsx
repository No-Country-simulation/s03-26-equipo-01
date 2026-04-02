import type { SideBarProps } from './side-dar';
import './styles/sidebar.css';
import SideBarContent from './components/sidebar-content/SidebarContent';

const SideBar = ({ itemsData, urlBase}: SideBarProps) => {

    return (
        <section className = 'home-sidebar'>
            <SideBarContent itemsData = {itemsData} urlBase = {urlBase} />
        </section>
    )
}

export default SideBar;