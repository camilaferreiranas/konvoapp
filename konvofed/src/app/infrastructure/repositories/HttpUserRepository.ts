import { LoginRequest } from "@/app/application/interfaces/dtos/LoginRequest";
import { LoginResponse } from "@/app/application/interfaces/dtos/LoginResponse";
import { UserRepository } from "@/app/application/interfaces/UserRepository";
import axios from "axios";

export class HttpUserRepository implements UserRepository {
    async login(login: LoginRequest): Promise<LoginResponse> {
        const response = await axios.post('/login', {login});
        return response.data;
    }

}