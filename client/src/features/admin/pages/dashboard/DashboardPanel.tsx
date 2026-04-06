import TitleContainer from "../../components/title-container/TitleContainer";

const DashboardPanel = () => {    

    return (
        <section className = 'dashboard-admin-panel'>
            <div className = 'dashboard-admin-panel_container'>
                <div className = 'dashboard-admin-panel_container--info'>
                    <TitleContainer 
                        title = "Bienvenido" 
                    />
                </div>
            </div>
        </section>
    )
}

export default DashboardPanel;