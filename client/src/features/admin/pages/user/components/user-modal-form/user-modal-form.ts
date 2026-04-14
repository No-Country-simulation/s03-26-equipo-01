import type { CreatedUser } from "../../model/created-user";

export interface UserModalFormProps {
    onSubmit: (categoryCreated: CreatedUser) => void
}