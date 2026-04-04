import { createContext } from "react";
import type { Auth } from "../models/auth";

const AuthContext = createContext<Auth | null>(null);

export default AuthContext;