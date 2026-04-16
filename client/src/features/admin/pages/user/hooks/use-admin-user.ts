import useApi from '../../../../../core/api/hooks/use-api';
import usePaginator from '../../../../../shared/hooks/use-paginator';
import type { CreatedUser } from '../model/created-user';
import type { EditableUser } from '../model/editable-user';
import getUsers from '../service/get-users.service';
import dischargeService from '../service/discharge.service';
import unsuscribeService from '../service/unsuscribe.service';
import createService from '../service/create-user.service';

const useAdminUser = () => {
  const { deletedWithBody, patchWithBody } = useApi();
  const { data, page, setPage, fetchData } = usePaginator<EditableUser>(getUsers);

  const discharge = async (id: number) => {
    await patchWithBody(dischargeService, id);
    fetchData();
  }

  const unsuscribe = async (id: number) => {
    await deletedWithBody(unsuscribeService, id);
    fetchData();
  }

  const created = async (createdUser: CreatedUser) => {
    await createService(createdUser);
  }

  return { created, discharge, unsuscribe, data, page, setPage };
};

export default useAdminUser;
