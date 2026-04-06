import TitleContainer from "../../components/title-container/TitleContainer";

const ManagmentPanel = () => {    

    return (
        <section className = 'managment-admin-panel'>
            <div className = 'managment-admin-panel_container'>
                <div className = 'managment-admin-panel_container--info'>
                    <TitleContainer 
                        title = 'Gestión'
                        text = 'Gestiona tags y categorías disponibles para asignar a testimonios.'
                    />
                </div>
            </div>
        </section>
    )
}

export default ManagmentPanel;