import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  patientNav: any[] = [];

  constructor() { }

  ngOnInit(): void {
    this.patientNav = [
      {
        name: 'Search Doctor',
        route: '/patient/search',
        data: null,
      },
      {
        name: 'Appointments',
        route: '/patient/appointments',
        data: null,
      },
      {
        name: 'Profile',
        route: '/patient/profile',
        data: null,
      }
    ];
  }
}
