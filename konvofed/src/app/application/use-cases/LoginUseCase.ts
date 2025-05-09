import { LoginRequest } from "../interfaces/dtos/LoginRequest";
import { LoginResponse } from "../interfaces/dtos/LoginResponse";
import { UserRepository } from "../interfaces/UserRepository";

export class LoginUseCase  {
    constructor(private userRepository: UserRepository) {}

    async execute(login: LoginRequest): Promise<LoginResponse> {
        return this.userRepository.login(login);
    }
}