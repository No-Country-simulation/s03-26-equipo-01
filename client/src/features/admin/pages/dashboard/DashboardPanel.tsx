import useAuthContext from "../../../../shared/auth/context/use-auth-context";
import TitleContainer from "../../components/title-container/TitleContainer";
import CategoryDashboard from "./components/category-dashboard/CategoryDashboard";
import TagDashboard from "./components/tag-dashboard/TagDashboard";
import useTab from "../../hooks/use-tab";
import './styles/dashboard-panel.css';
import TabContainer from "../../components/tab-container/TabContainer";
import useMetrics from "./hooks/use-metrics";

const DashboardPanel = () => {    

    const {user} = useAuthContext()
    const {tabName, handleTab} = useTab();
    const {categoriesMetrics, tagsMetrics} = useMetrics()

    return (
        <section className = 'dashboard-admin-panel'>
            <div className = 'dashboard-admin-panel_container'>
                <TitleContainer 
                        title = {`Bienvenido ${user?.firstName + ' ' + user?.lastName}`}
                    />
                <TabContainer onTab = {handleTab}>
                    <CategoryDashboard currentTab = {tabName} metrics = {categoriesMetrics} />
                    <TagDashboard currentTab = {tabName} metrics = {tagsMetrics} />
                </TabContainer>
            </div>
        </section>
    )
}

export default DashboardPanel;