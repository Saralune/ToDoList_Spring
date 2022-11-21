import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from '../services/api.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  error = false;
  ngForm: FormGroup;
  data = {
    username: '',
    password: '',
  };
  constructor(private router: Router, private authService: AuthService) {
    this.ngForm = new FormGroup({
      username: new FormControl(this.data.username),
      password: new FormControl(this.data.password),
    });
  }

  ngOnInit(): void {}

  onLogin(form: FormGroup): void {
    console.log('form value :' + form.value);
    if (form.valid) {
      this.data.username = form.value.username;
      this.data.password = form.value.password;

      console.log('this data :' + this.data);

      let ok = this.authService.veriFyLogin(this.data);

      console.log('login component ' + ok);
      if (ok) {
        setTimeout(() => {
          console.log('this error ok : ' + this.error);
          this.error = false;
          this.router.navigateByUrl('tasks');
        }, 1500);
      } else {
        console.log('this error ' + this.error);
        this.error = true;
      }
    }
  }
}
