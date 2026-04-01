import LogoContainer from "../logo-container/LogoContainer";
import NavegationList from "../navegation-list/NavegationList";
import type { SideBarContentProps } from "./sidebar-content";
import './sidebar-content.css';

const SideBarContent = ({itemsData} : SideBarContentProps) => {
    return (
        <aside className = "home-sidebar-content">
            <LogoContainer />
            <NavegationList itemsData = {itemsData} />
        </aside>
    )
}

export default SideBarContent;