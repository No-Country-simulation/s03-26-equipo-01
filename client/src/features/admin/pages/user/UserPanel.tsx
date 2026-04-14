import useActive from "../../../../shared/hooks/use-active";
import AddButton from "../../components/add-button/AddButton";
import TitleContainer from "../../components/title-container/TitleContainer";
import UserModalForm from "./components/user-modal-form/UserModalForm";
import UsersTable from "./components/users-table/UsersTable";
import useAdminUser from "./hooks/use-admin-user";
import './styles/user-panel.css';

const UsersPanel = () => {    

    const {isActive, handleActive} = useActive();
    const {created, discharge, unsuscribe, data, page, setPage} = useAdminUser();

    return (
        <section className = 'users-admin-panel'>
            <div className = 'users-admin-panel_container'>
                <div className = 'users-admin-panel_container--info'>
                    <TitleContainer 
                        title = 'Usuarios'
                        text = 'Administra usuarios y gestiona sus roles.'
                    />
                    <AddButton text = "CREAR USUARIO" onSubmit = {handleActive} />
                    {isActive && <UserModalForm onSubmit = {created} />}
                    {data && 
                    <UsersTable 
                        data={data} 
                        setPage={setPage} 
                        page={page} 
                        discharge={discharge} 
                        unsuscribe={unsuscribe} 
                    />}
                </div>
            </div>
        </section>
    )
}

export default UsersPanel;