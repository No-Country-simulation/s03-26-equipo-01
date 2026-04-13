import Paginator from "../../../../shared/components/pagination/Paginator";
import useActive from "../../../../shared/hooks/use-active";
import AddButton from "../../components/add-button/AddButton";
import TitleContainer from "../../components/title-container/TitleContainer";
import useAdminUser from "./hooks/use-admin-user";

const UsersPanel = () => {    

    const {isActive, handleActive} = useActive();
    const {discharge, unsuscribe, data, page, setPage} = useAdminUser();

    return (
        <section className = 'users-admin-panel'>
            <div className = 'users-admin-panel_container'>
                <div className = 'users-admin-panel_container--info'>
                    <TitleContainer 
                        title = 'Usuarios'
                        text = 'Administra usuarios y gestiona sus roles.'
                    />
                    <AddButton text = "CREAR USUARIO" onSubmit = {handleActive} />
                    {data && <>
                        <Paginator
                            totalPages={data.totalPages}
                            currentPage={page}
                            onPageChange={setPage}
                            totalElements={data.totalElements}
                            pageSize={data.size}
                        />
                    </>}
                </div>
            </div>
        </section>
    )
}

export default UsersPanel;