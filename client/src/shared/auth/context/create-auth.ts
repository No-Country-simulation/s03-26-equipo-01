import { createContext } from "react";
import type { Auth } from "../types/auth";


const AuthContext = createContext<Auth | null>(null);

export default AuthContext;