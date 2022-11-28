import { Users } from './users.model';

export class Category {
  id: number;
  name: string;
  users: Users;

  constructor(id: number, name: string, users: Users) {
    this.id = id;
    this.name = name;
    this.users = users;
  }
}
