import TabContainer from "../../components/tab-container/TabContainer";
import TitleContainer from "../../components/title-container/TitleContainer";
import useTab from "../../hooks/use-tab";
import CategorySubPanel from "./components/category-table/CategorySubPanel";
import TagSubPanel from "./components/tag-table/TagSubPanel";
import './styles/magagment.css';

const ManagmentPanel = () => {    

    const {tabName, handleTab} = useTab();

    return (
        <section className = 'managment-admin-panel'>
            <div className = 'managment-admin-panel_container'>
                <TitleContainer 
                        title = 'Gestión'
                        text = 'Gestiona tags y categorías disponibles para asignar a testimonios.'
                    />
                <TabContainer onTab = {handleTab}>
                    <CategorySubPanel currentTab = {tabName} />
                    <TagSubPanel currentTab = {tabName} />
                </TabContainer>
            </div>
        </section>
    )
}


export default ManagmentPanel;