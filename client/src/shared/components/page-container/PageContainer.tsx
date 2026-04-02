import { useState, type ReactNode } from "react";
import type { NavegationItemData } from "../../types/navegation-item-data/navegation-list-data";
import SideBar from "../sidebar/Sidebar";
import './styles/page-container.css'
import TopBar from "../top-bar/TopBat";
import MenuIcon from "../sidebar/components/menu-icon/MenuIcon";

interface PageContainerProps {
    itemsData: NavegationItemData[]
    basePath: string
    children: ReactNode
}

const PageContainer = ({children, itemsData, basePath}: PageContainerProps) => {

    const [isActive, setIsActive] = useState(false);
        
    const handleActive = () => setIsActive(!isActive);
    
    return (
        <main className = {isActive ? 'page-container_reduce' : 'page-container'}>
            <div className = 'page-header-container'>
                <MenuIcon onSubmit = {handleActive} isActive = {isActive} />
                <TopBar />
            </div>
            {isActive && <SideBar 
                    itemsData = {itemsData} 
                    urlBase = {basePath} 
                    onActive = {handleActive}
                />}
            {children}
        </main>
    )
}

export default PageContainer;