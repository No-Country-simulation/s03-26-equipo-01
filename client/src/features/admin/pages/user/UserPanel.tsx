import TitleContainer from "../../components/title-container/TitleContainer";

const UsersPanel = () => {    

    return (
        <section className = 'users-admin-panel'>
            <div className = 'users-admin-panel_container'>
                <div className = 'users-admin-panel_container--info'>
                    <TitleContainer 
                        title = 'Usuarios'
                        text = 'Administra usuarios y gestiona sus roles.'
                    />
                </div>
            </div>
        </section>
    )
}

export default UsersPanel;