import TabContainer from "../../components/tab-container/TabContainer";
import TitleContainer from "../../components/title-container/TitleContainer";
import useTab from "../../hooks/use-tab";
import CategoryTable from "./components/category-table/CategoryTable";
import TagTable from "./components/tag-table/TagTable";
import './styles/magagment.css';

const ManagmentPanel = () => {    

    const {tabName, handleTab} = useTab();

    return (
        <section className = 'managment-admin-panel'>
            <div className = 'managment-admin-panel_container'>
                <div className = 'managment-admin-panel_container--info'>
                    <TitleContainer 
                        title = 'Gestión'
                        text = 'Gestiona tags y categorías disponibles para asignar a testimonios.'
                    />
                    <TabContainer onTab = {handleTab}>
                        <CategoryTable currentTab = {tabName} />
                        <TagTable currentTab = {tabName} />
                    </TabContainer>
                </div>
            </div>
        </section>
    )
}


export default ManagmentPanel;