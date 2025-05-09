import { LoginRequest } from "./dtos/LoginRequest";
import { LoginResponse } from "./dtos/LoginResponse";

export interface UserRepository  {
    login(login: LoginRequest): Promise<LoginResponse>;
}