import type { User } from "../../../../types/user/user";

export interface UserConfigurationDataProps {
    user: User | null
}

export interface ConfigurationContainerProps {
    user: User | null
    onActive: () => void
}

export interface CardUserDataProps {
    user: User | null
}