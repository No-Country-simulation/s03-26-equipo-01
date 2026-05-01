import useActive from "../../../../../shared/hooks/use-active";

const useModalActive = () => {

    const addActive = useActive();
    const editActive = useActive();
    const deleteActive = useActive();

    const modalActive = {
        add: {
            isActive: addActive.isActive,
            handle: addActive.handleActive,
            onClose: addActive.handleActive,
        },
        edit: {
            isActive: editActive.isActive,
            handle: editActive.handleActive,
            onClose: editActive.handleActive,
            id: editActive.id
        },
        deleted: {
            isActive: deleteActive.isActive,
            handle: deleteActive.handleActive,
            onClose: deleteActive.handleActive,
            id: deleteActive.id as number
        }
    }

    return {...modalActive}
}

export default useModalActive;