import LogoContainer from "../logo-container/LogoContainer";
import NavegationList from "../navegation-list/NavegationList";
import type { SideBarContentProps } from "./sidebar-content";
import './sidebar-content.css';

const SideBarContent = ({itemsData} : SideBarContentProps) => {
    return (
        <div className = "home-sidebar-content">
            <LogoContainer />
            <NavegationList itemsData = {itemsData} />
        </div>
    )
}

export default SideBarContent;