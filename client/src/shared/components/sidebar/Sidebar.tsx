import type { SideBarProps } from './side-dar';
import './styles/sidebar.css';
import SideBarContent from './components/sidebar-content/SidebarContent';

const SideBar = ({itemsData, urlBase}: SideBarProps) => {

    return (
        <section className = 'home-sidebar'>
            <div className = 'home-sidebar_fixed'>
                <SideBarContent itemsData = {itemsData} urlBase = {urlBase} />
            </div>
        </section>
    )
}

export default SideBar;