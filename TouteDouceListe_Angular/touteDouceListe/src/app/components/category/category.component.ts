import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Category } from 'src/app/models/category.model';
import { Users } from 'src/app/models/users.model';
import { ApiService } from 'src/app/services/api.service';
import { AuthService } from 'src/app/services/auth.service';
import { TasksComponent } from '../tasks/tasks.component';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss'],
})
export class CategoryComponent implements OnInit {
  categories: Category[] = [];
  category: Category | undefined;
  error = null;
  idUser = -1;
  myForm: FormGroup;
  newCategory = {
    id: 0,
    name: '',
    users: {} as Users,
  };

  constructor(
    public apiService: ApiService,
    //private router: Router,
    public authService: AuthService,
    public userTasks: TasksComponent
  ) {
    this.myForm = new FormGroup({
      name: new FormControl(this.newCategory.name),
      users: new FormControl(authService.getUserFromStorage()),
    });
    this.idUser = authService.getUserFromStorage().id;
    this.userTasks.getAllCategories();
    this.categories = this.userTasks.categories;
    //console.log(this.categories);
  }

  ngOnInit(): void {
    this.userTasks.getAllCategories();
  }

  onAddCategory(form: FormGroup) {
    this.newCategory.name = form.value.nameCat;
    console.log('newCat name : ' + this.newCategory.name);
    this.newCategory.users = this.authService.getUserFromStorage();

    console.log(this.newCategory.users);

    if (this.newCategory.name.length > 255) {
      alert('La description est trop longue, merci de recommencer.');
    } else {
      if (confirm("Valider l'ajout de la catégorie ?")) {
        this.apiService.saveCategory(this.newCategory).subscribe({
          next: (data) => console.log('data ' + data),
          error: (err) => (this.error = err.message),
          complete: () => (this.error = null),
        });
      }
    }
    this.userTasks.closePopup();
  }

  onEditCategory(form: FormGroup) {}

  onDeleteCategory(c: Category) {}
}
