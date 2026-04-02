import { useState } from 'react';
import type { SideBarProps } from './side-dar';
import './styles/sidebar.css';
import MenuIcon from './components/menu-icon/MenuIcon';
import SideBarContent from './components/sidebar-content/SidebarContent';

const SideBar = ({ itemsData, urlBase }: SideBarProps) => {

    const [isActive, setIsActive] = useState(false);

    const handleActive = () => setIsActive(!isActive);

    return (
        <section className = 'home-sidebar'>
            <MenuIcon onSubmit = {handleActive} isActive = {isActive} />
            {isActive && <SideBarContent itemsData = {itemsData} urlBase = {urlBase} />}
        </section>
    )
}

export default SideBar;