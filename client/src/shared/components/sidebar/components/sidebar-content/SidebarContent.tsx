import LogoContainer from "../logo-container/LogoContainer";
import NavegationList from "../navegation-list/NavegationList";
import type { SideBarContentProps } from "./sidebar-content";
import './styles/sidebar-content.css';

const SideBarContent = ({itemsData, urlBase} : SideBarContentProps) => {
    return (
        <aside className = 'home-sidebar-content show-sidebar'>
            <LogoContainer />
            <NavegationList itemsData = {itemsData} urlBase = {urlBase} />
        </aside>
    )
}

export default SideBarContent;