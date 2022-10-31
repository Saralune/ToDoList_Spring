import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from './services/api.service';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  title = 'Toute Douce Liste';

  name = '';
  loggin = true;
  logout = false;
  display = false;

  constructor(
    private authenticateService: AuthService,
    private router: Router,
    private apiService: ApiService
  ) {}

  ngOnInit(): void {
    this.showName();
  }

  ngDoCheck(): void {
    this.showName();
  }
  showName() {
    let rep = this.authenticateService.getUserFromStorage();
    if (rep != null) {
      this.name = rep.username;
      this.display = true;
      this.loggin = false;
      this.logout = true;
    }
  }

  disconnect() {
    this.authenticateService.removeUserFromStorage();
    this.display = false;
    this.loggin = true;
    this.logout = false;
    this.router.navigateByUrl('home');
  }
}
