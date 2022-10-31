import { Category } from './category.model';
import { Role } from './role.model';

export class Users {
  id: number;
  username: string;
  mail: String;
  task: Task[];
  active: boolean;
  role: Role[];

  constructor(
    id: number,
    username: string,
    mail: string,
    task: Task[],
    active: boolean,
    role: Role[]
  ) {
    this.id = id;
    this.username = username;
    this.mail = mail;
    this.task = task;
    this.active = active;
    this.role = role;
  }
}
