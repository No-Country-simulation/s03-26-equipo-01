import useApi from "../../../../../core/api/hooks/use-api";
import usePaginator from "../../../../../shared/hooks/use-paginator";
import type { CreatedUser } from "../model/created-user";
import type { EditableUser } from "../model/editable-user";
import getUsers from "../service/get-users.service";
import dischargeService from "../service/discharge.service";
import unsuscribeService from "../service/unsuscribe.service";
import createService from "../service/create-user.service";

const useAdminUser = () => {

  const { deleted, patch } = useApi();
  const {data, page, setPage, addRow } = usePaginator<EditableUser>(getUsers);

  const discharge = async (id: number) => await patch(dischargeService, id); 
  const unsuscribe = async (id: number) => await deleted(unsuscribeService, id); 
  const created = async (createdUser: CreatedUser) => {
    const newUser = await createService(createdUser);
    addRow(newUser, newUser.id);
  } 

  return { created, discharge, unsuscribe, data, page, setPage }
};

export default useAdminUser;