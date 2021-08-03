import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  doctorNav: any[] = [];

  constructor() { }

  ngOnInit(): void {
    this.doctorNav = [
      {
        name: 'Appointments',
        route: '/doctor/appointments',
        data: null,
      },
      {
        name: 'Profile',
        route: '/doctor/profile',
        data: null,
      },
      {
        name: 'Warnings',
        route: '/doctor/warnings',
        data: null,
      }
    ];
  }
}
