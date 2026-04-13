import { useState } from "react";
import useApi from "../../../../../core/api/hooks/use-api";
import usePaginator from "../../../../../shared/hooks/use-paginator";
import type { CreatedUser } from "../model/created-user";
import type { EditableUser } from "../model/editable-user";
import getUsers from "../service/get-users";

const useAdminUser = () => {

  const { deleted, put } = useApi();
  const {data, page, setPage } = usePaginator<EditableUser>(getUsers);
  const [user, setUser] = useState<CreatedUser | null>(null);

  const discharge = async (id: number) => await put(dischargeService, id); 
  const unsuscribe = async (id: number) => await deleted(unsuscribeService, id); 
  const created = async (createdUser: CreatedUser) => setUser(await createService(createdUser)); 

  return { created, discharge, unsuscribe, data, page, setPage }
};

export default useAdminUser;