import useApi from "../../../../../core/api/hooks/use-api";
import usePaginator from "../../../../../shared/hooks/use-paginator";
import type { EditableUser } from "../model/user";
import getUsers from "../service/get-users";

const useAdminUser = () => {

  const { deleted, put } = useApi();
  const {data, page, setPage } = usePaginator<EditableUser[]>(getUsers);

  const discharge = async (id: number) => await put(dischargeService, id); 
  const unsuscribe = async (id: number) => await deleted(unsuscribeService, id); 

  return { discharge, unsuscribe, data, page, setPage }
};

export default useAdminUser;