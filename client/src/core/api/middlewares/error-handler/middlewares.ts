import { AxiosError } from "axios";
import { InternalError } from "../../errors/server-error/internal-error";
import { UknowError } from "../../errors/server-error/uknow-error";
import { NotFoundError } from "../../errors/client-error/not-found";

function handleErrors(error: AxiosError) {
    if (!error.response) throw new UknowError();
    if (error.response.status === 404) throw new NotFoundError();
    if (error.response.status >= 500) throw new InternalError();
    throw error;
}

export default handleErrors;