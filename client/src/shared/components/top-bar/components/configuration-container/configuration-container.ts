import type { User } from "../../../../user/models/user"

export interface ConfigurationContainerProps {
    user: User | null
    onActive: () => void
}