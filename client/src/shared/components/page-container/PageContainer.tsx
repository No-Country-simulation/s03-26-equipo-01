import { useState } from "react";
import SideBar from "../sidebar/Sidebar";
import './styles/page-container.css'
import TopBar from "../top-bar/TopBat";
import MenuIcon from "../sidebar/components/menu-icon/MenuIcon";
import type { HeaderContainerProps, PageContainerProps, PageContentProps } from "./page-container";

const PageContainer = ({children, itemsData, basePath}: PageContainerProps) => {

    const [isActive, setIsActive] = useState(true);
        
    const handleActive = () => setIsActive(!isActive);
    
    return (
        <main className = {isActive ? 'page-container_reduce' : 'page-container'}>
            <HeaderContainer 
                onActive = {handleActive}
                isActive = {isActive}
            />
            <PageContent 
                onActive = {handleActive}
                isActive = {isActive}
                children = {children}
                itemsData = {itemsData}
                basePath = {basePath}
            />
        </main>
    )
}

const HeaderContainer = ({onActive, isActive}: HeaderContainerProps) => {
    return (
        <section className = 'page-header-container'>
            <MenuIcon onSubmit = {onActive} isActive = {isActive} />
            <TopBar />
        </section>
    )
}

const PageContent = ({onActive, isActive, itemsData, basePath, children}: PageContentProps) => {
    return (
        <section className = {isActive ? 'page-content-container' : 'page-content-container_disable'}>
            {isActive && <SideBar 
                itemsData = {itemsData} 
                urlBase = {basePath} 
                onActive = {onActive}
        />}
            {children}
        </section>
    )
}


export default PageContainer;
