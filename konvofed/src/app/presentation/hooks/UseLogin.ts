import { LoginRequest } from "@/app/application/interfaces/dtos/LoginRequest";
import { LoginUseCase } from "@/app/application/use-cases/LoginUseCase";
import { HttpUserRepository } from "@/app/infrastructure/repositories/HttpUserRepository";
import { useMutation } from "@tanstack/react-query";

const repositoryHtpp = new HttpUserRepository();
const loginUseCase = new LoginUseCase(repositoryHtpp);

export const useLogin = () => {
    return useMutation({
        mutationFn: (data: LoginRequest) =>
            loginUseCase.execute(data)
    });
}