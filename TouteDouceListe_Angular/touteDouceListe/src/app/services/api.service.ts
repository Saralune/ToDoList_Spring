import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpEvent,
  HttpHeaders,
  HttpParams,
} from '@angular/common/http';

import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Tasks } from '../models/tasks.model';
import { Category } from '../models/category.model';

@Injectable({ providedIn: 'root' })
export class ApiService {
  token = localStorage.getItem('accessToken');
  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `Bearer ${this.token}`,
  });

  constructor(private http: HttpClient) {
    //console.log(this.headers)
  }

  public saveTask(task: Tasks) {
    return this.http.post<Tasks>(environment.host + '/task/saveTask', task, {
      headers: this.headers,
    });
  }

  public getUserTasks() {
    return this.http.get<any[]>(environment.host + '/task/all', {
      headers: this.headers,
    });
  }

  public login(data: any) {
    // console.log(data)
    return this.http.post<any>(environment.host + '/api/auth/signin', data);
  }

  // non utilisé
  // public getUserTask(id: number) {
  //   return this.http.get<Tasks>(environment.host + '/task' + id);
  // }

  public getCategories() {
    return this.http.get<Category[]>(environment.host + '/category/all');
  }

  // non utilisé
  // public getCategory(id: number) {
  //     return this.http.get<Category>(environment.host + "/category" + id)
  // }

  public editTask(task: Tasks) {
    return this.http.post<Tasks>(environment.host + '/editTask', task, {
      headers: this.headers,
    });
  }

  public getTasksBySearch(description: String) {
    return this.http.get<Tasks[]>(
      environment.host + '/task/research/' + description,
      { headers: this.headers }
    );
  }

  public delTask(task: Tasks) {
    return this.http.delete(environment.host + '/task/deleteTask/' + task.id, {
      headers: this.headers,
    });
  }

  public getUserTasksByCatId(id: number) {
    //console.log(id);
    return this.http.get<Tasks[]>(environment.host + '/task/category/' + id, {
      headers: this.headers,
    });
  }
}
