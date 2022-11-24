import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Category } from 'src/app/models/category.model';
import { Tasks } from 'src/app/models/tasks.model';
import { Users } from 'src/app/models/users.model';
import { ApiService } from 'src/app/services/api.service';
import { AuthService } from 'src/app/services/auth.service';
import { TasksComponent } from '../tasks.component';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.scss'],
})
export class AddTaskComponent implements OnInit {
  myForm: FormGroup;
  categories: Category[] = [];
  tasks: Tasks[] = [];
  category: Category | undefined;
  task: Tasks | undefined;
  error = null;
  idUser = -1;

  newTask = {
    id: 0,
    nameTask: '',
    dateTask: new Date(),
    description: '',
    checked: false,
    // deleted: false,
    category: {} as Category,
    users: {} as Users,
  };

  constructor(
    public apiService: ApiService,
    //private router: Router,
    public authService: AuthService,
    public userTasks: TasksComponent
  ) {
    this.myForm = new FormGroup({
      nameTask: new FormControl(this.newTask.nameTask),
      dateTask: new FormControl(this.newTask.dateTask),
      description: new FormControl(this.newTask.description),
      checked: new FormControl(false),
      // deleted: new FormControl(this.newTask.deleted),
      category: new FormControl(this.newTask.category),
      //users: new FormControl(this.newTask.user)
    });
    this.idUser = authService.getUserFromStorage().id;
  }

  ngOnInit(): void {
    this.getAllCategories();
  }

  getAllCategories() {
    this.apiService.getCategories(this.idUser).subscribe({
      next: (data) => (this.categories = data),
      error: (err) => (this.error = err.message),
      complete: () => (this.error = null),
    });
  }

  onAddTask(form: FormGroup): void {
    this.newTask.nameTask = form.value.nameTask;
    this.newTask.dateTask = form.value.dateTask;
    this.newTask.description = form.value.description;
    this.newTask.checked = false;
    // this.newTask.deleted = false;
    this.newTask.category = form.value.category;
    this.newTask.users = this.authService.getUserFromStorage();

    // console.log(typeof this.authService.getUserFromStorage());
    // console.log(this.authService.getUserFromStorage());
    // console.log(this.newTask);

    console.log('taille descr : ' + this.newTask.description.length);
    console.log('taille nom : ' + this.newTask.nameTask.length);

    if (this.newTask.description.length > 100) {
      alert(
        'La taille de la description doit être inférieure à 100 caractères, merci de recommencer.'
      );
    } else if (this.newTask.nameTask.length > 20) {
      alert(
        'La taille du nom doit être inférieure à 20 caractères, merci de recommencer.'
      );
    } else {
      if (confirm("Valider l'ajout de la tâche ?")) {
        this.apiService.saveTask(this.newTask).subscribe({
          next: (data) => console.log('data ' + data),
          error: (err) => (this.error = err.message),
          complete: () => (this.error = null),
        });
      }
    }

    this.userTasks.closePopup();
  }
}
