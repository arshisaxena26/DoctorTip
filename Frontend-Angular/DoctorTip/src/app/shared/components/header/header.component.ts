import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/home/services/login.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  @Input() navItems: any;
  subscription: Subscription | undefined;
  constructor(private _router: Router,private _loginService:LoginService) { }

  ngOnInit(): void {
  }

  navigateTo(route: any) {
    this._router.navigate([route]);
  }


  loggedOut(){
    this._loginService.logOut().subscribe((res: any) => {
      this._router.navigate(['']);
    });
  };
  
}
