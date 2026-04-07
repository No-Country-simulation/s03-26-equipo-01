import useAuthContext from "../../../../shared/auth/context/use-auth-context";
import TitleContainer from "../../components/title-container/TitleContainer";
import TabContainer from "../../components/tab-container/TabContainer";
import CategoryDashboard from "./components/category-dashboard/CategoryDashboard";
import TagDashboard from "./components/tag-dashboard/TagDashboard";
import useTab from "../../hooks/use-tab";
import './styles/dashboard-panel.css';
import type { TabsProps } from "./dashboard-panel";

const DashboardPanel = () => {    

    const {user} = useAuthContext()
    const {tabName, handleTab} = useTab();

    return (
        <section className = 'dashboard-admin-panel'>
            <div className = 'dashboard-admin-panel_container'>
                <div className = 'dashboard-admin-panel_container--info'>
                    <TitleContainer 
                        title = {`Bienvenido ${user?.firstName + ' ' + user?.lastName}`}
                    />
                    <Tabs 
                        handleTab = {handleTab} 
                        tabName = {tabName}
                    />
                </div>
            </div>
        </section>
    )
}

const Tabs = ({handleTab, tabName}: TabsProps) => {
    return (
        <TabContainer onTab = {handleTab}>
            <CategoryDashboard currentTab = {tabName} />
            <TagDashboard currentTab = {tabName} />
        </TabContainer>
    )
}

export default DashboardPanel;