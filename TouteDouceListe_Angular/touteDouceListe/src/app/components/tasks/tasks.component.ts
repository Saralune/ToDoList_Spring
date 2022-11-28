import { Component, DoCheck, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Category } from 'src/app/models/category.model';
import { Tasks } from 'src/app/models/tasks.model';
import { ApiService } from 'src/app/services/api.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.scss'],
})
export class TasksComponent implements OnInit, DoCheck {
  categories: Category[] = [];
  tasks: Tasks[] = [];
  category: Category | undefined;
  task!: Tasks;
  pastelsColors!: string[];
  error = null;
  modalAction = '';

  searchForm: FormGroup;
  myForm: FormGroup;
  newSearch = '';
  idUser = -1;
  indexOfCategory = -1;

  pages: number = 1;

  //modal
  displayStyle = 'none';
  displayBlur = 'blur(0)';
  display = false;

  newTask = {
    id: 0,
    nameTask: '',
    dateTask: new Date(),
    description: '',
    checked: false,
    deleted: false,
    category: {} as Category,
  };

  constructor(
    public apiService: ApiService,
    private router: Router,
    private authenticateService: AuthService
  ) {
    this.myForm = new FormGroup({
      nameTask: new FormControl(this.newTask.nameTask),
      dateTask: new FormControl(this.newTask.dateTask),
      description: new FormControl(this.newTask.description),
      checked: new FormControl(this.newTask.checked),
      deleted: new FormControl(this.newTask.deleted),
      category: new FormControl(this.newTask.category),
    });
    this.searchForm = new FormGroup({
      newSearch: new FormControl(this.newSearch),
    });
    this.idUser = authenticateService.getUserFromStorage().id;
    this.buttonBgColor();
  }

  ngDoCheck(): void {}

  ngOnInit(): void {
    this.getAllTasksByUser();
    this.getAllCategories();
  }

  getAllTasksByUser() {
    this.apiService.getTasksByUser(this.idUser).subscribe({
      next: (data) => (this.tasks = data),
      //console.log("-------->" + data), this.tasks.forEach(t => console.log(t))
      error: (err) => (this.error = err.message),
      complete: () => (this.error = null),
    });
  }

  getAllCategories() {
    this.apiService.getCategories(this.idUser).subscribe({
      next: (data) => (this.categories = data),
      error: (err) => (this.error = err.message),
      complete: () => (this.error = null),
    });
  }

  getTasksByCategory(catId: number) {
    this.apiService.getUserTasksByCatId(catId).subscribe({
      next: (data) => (this.tasks = data),
      error: (err) => (this.error = err.message),
      complete: () => (this.error = null),
    });
    this.pages = 1;
  }

  createPopup() {
    this.modalAction = 'C';
    this.displayStyle = 'block';
    this.displayBlur = 'blur(4px)';
  }

  editPopup(t: Tasks) {
    this.modalAction = 'E';
    this.displayStyle = 'block';
    this.displayBlur = 'blur(4px)';
    this.task = t;
  }

  openCategories() {
    this.modalAction = 'CAT';
    this.displayStyle = 'block';
    this.displayBlur = 'blur(4px)';
  }

  closePopup() {
    this.displayStyle = 'none';
    this.displayBlur = 'blur(0)';
    setTimeout(() => {
      this.ngOnInit();
    }, 1500);
    location.reload();
  }

  getTargetTask() {
    return this.task;
  }

  deleteTask(task: Tasks) {
    if (confirm('Vous êtes sur de vouloir supprimer cette tache?')) {
      this.apiService.delTask(task).subscribe({
        //next: (data) => console.log(data),
        error: (err) => (this.error = err.message),
        complete: () => this.getAllTasksByUser(),
      });
    }
  }

  onSearch(form: FormGroup) {
    this.apiService
      .getTasksBySearch(form.value.newSearch, this.idUser)
      .subscribe({
        next: (data) => (
          (this.tasks = data),
          //console.log('++++++++++' + data),
          this.tasks.forEach((t) => console.log(t))
        ),
        error: (err) => (this.error = err.message),
        complete: () => (this.error = null),
      });
  }

  buttonBgColor(): string[] {
    this.pastelsColors = [
      'bgPastelPinkOrange',
      'bgPastelOrange',
      'bgPastelLightPink',
      'bgPastelPink',
      'bgPastelPurple',
      'bgPastelLightPurple',
      'bgPastelLightBlue',
      'bgPastelBlue',
      'bgPastelGreen',
      'bgPastelLightGreen',
    ];
    return this.pastelsColors;
  }

  getIndexOfCategory(name: string): number {
    for (let i = 0; i < this.categories.length; i++) {
      if (name == this.categories[i].name) {
        return i;
      }
    }
    return -1;
  }
}
