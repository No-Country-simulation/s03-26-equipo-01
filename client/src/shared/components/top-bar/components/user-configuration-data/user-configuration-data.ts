import type { User } from "../../../../user/models/user";

export interface UserConfigurationDataProps {
    user: User | null
    logout: (user: User) => void
}