import type { User } from "../../../../user/models/user";

export interface CardUserDataProps {
    user: User | null
    logout: (user: User) => void
}

export interface UserDataSectionProps {
    user: User | null
}

export interface UserDropboxSectionProps {
    user: User | null
    logout: (user: User) => void
}

