export class UserClient {
    constructor(
      public readonly id: number,
      public readonly email: string,
      public readonly username: string, 
      public readonly name: string,
      public readonly password: string,
    ) {}
  }
  