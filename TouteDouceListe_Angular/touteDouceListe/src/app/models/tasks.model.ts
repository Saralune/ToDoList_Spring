import { Category } from './category.model';
import { Users } from './users.model';

export class Tasks {
  id: number;
  nameTask: string;
  dateTask: Date;
  description: string;
  checked: boolean;
  // deleted: boolean;
  category: Category;
  users: Users;

  constructor(
    id: number,
    nameTask: string,
    dateTask: Date,
    description: string,
    checked: boolean,
    category: Category,
    users: Users
  ) {
    this.id = id;
    this.nameTask = nameTask;
    this.dateTask = dateTask;
    this.description = description;
    this.checked = checked;
    this.category = category;
    this.users = users;
  }
}
