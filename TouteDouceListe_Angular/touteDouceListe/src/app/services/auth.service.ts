import { Injectable } from '@angular/core';
import { Users } from '../models/users.model';
import { ApiService } from './api.service';

@Injectable({ providedIn: 'root' })
export class AuthService {
  error = null;
  ok = true;
  constructor(private apiService: ApiService) {}
  // login verification
  veriFyLogin(data: any) {
    console.log('data auth service' + data);

    this.apiService.login(data).subscribe((response) => {
      console.log('response auth service' + response);

      //if existant user mail in response && decode password verif
      if (response.accessToken) {
        this.setUserInStorage({
          id: response.id,
          mail: response.mail,
          username: response.username,
          roles: response.roles,
        });
        localStorage.setItem('accessToken', response.accessToken);
        this.ok = true;
      } else {
        this.ok = false;
      }
    });
    if (this.ok) {
      console.log('auth service ok');
      return true;
    } else {
      console.log('auth service false');
      return false;
    }
  }

  // set user in storage
  setUserInStorage(data: any) {
    localStorage.setItem('user', JSON.stringify(data));
  }
  // get user from storage
  getUserFromStorage() {
    let user = localStorage.getItem('user');
    if (user) return JSON.parse(user);
    return null;
  }
  removeUserFromStorage() {
    localStorage.removeItem('user');
    localStorage.removeItem('accessToken');
  }

  getToken() {
    let token = localStorage.getItem('accessToken');
    if (token) return token;
    return null;
  }
}
