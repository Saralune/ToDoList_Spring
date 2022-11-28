import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Category } from 'src/app/models/category.model';
import { Tasks } from 'src/app/models/tasks.model';
import { ApiService } from 'src/app/services/api.service';
import { AuthService } from 'src/app/services/auth.service';
import { TasksComponent } from '../tasks.component';

@Component({
  selector: 'app-edit-task',
  templateUrl: './edit-task.component.html',
  styleUrls: ['./edit-task.component.scss'],
})
export class EditTaskComponent implements OnInit {
  myForm: FormGroup;
  categories: Category[] = [];
  tasks: Tasks[] = [];
  category: Category | undefined;
  task: Tasks | undefined;
  error = null;
  taskToEdit!: Tasks;
  idUser = -1;

  constructor(
    public apiService: ApiService,
    public authService: AuthService,
    public userTasks: TasksComponent
  ) {
    this.taskToEdit = userTasks.getTargetTask();
    this.myForm = new FormGroup({
      nameTask: new FormControl(this.taskToEdit.nameTask),
      dateTask: new FormControl(
        this.taskToEdit.dateTask.toLocaleString().substring(0, 10)
      ),
      description: new FormControl(this.taskToEdit.description),
      checked: new FormControl(this.taskToEdit.checked),
      // deleted: new FormControl(this.newTask.deleted),
      category: new FormControl(this.taskToEdit.category),
      //users: new FormControl(this.newTask.user)
    });
    this.idUser = authService.getUserFromStorage().id;
  }

  ngOnInit(): void {
    this.getAllCategories();
    console.log(this.taskToEdit.nameTask);
  }

  getAllCategories() {
    this.apiService.getCategories(this.idUser).subscribe({
      next: (data) => (this.categories = data),
      error: (err) => (this.error = err.message),
      complete: () => (this.error = null),
    });
  }

  onEditTask(form: FormGroup): void {
    this.taskToEdit.nameTask = form.value.nameTask;
    this.taskToEdit.dateTask = form.value.dateTask;
    this.taskToEdit.description = form.value.description;
    this.taskToEdit.checked = form.value.checked;
    this.taskToEdit.category = form.value.category;
    this.taskToEdit.users = this.authService.getUserFromStorage();

    if (this.taskToEdit.description.length > 255) {
      alert(
        'La taille de la description doit être inférieure à 255 caractères, merci de recommencer.'
      );
    } else if (this.taskToEdit.nameTask.length > 30) {
      alert(
        'La taille du nom doit être inférieure à 30 caractères, merci de recommencer.'
      );
    } else {
      if (confirm('Valider la modification ?')) {
        this.apiService.saveTask(this.taskToEdit).subscribe({
          next: (data) => console.log(data),
          error: (err) => (this.error = err.message),
          complete: () => (this.error = null),
        });
      }
      this.userTasks.closePopup();
    }
  }
}
