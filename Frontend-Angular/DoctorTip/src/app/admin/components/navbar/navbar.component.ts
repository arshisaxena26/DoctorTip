import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  adminNav: any[] = [];

  constructor() { }

  ngOnInit(): void {
    this.adminNav = [
      {
        name: 'Patients',
        route: '/admin/patients',
        data: null,
      },
      {
        name: 'Doctors',
        route: '/admin/doctors',
        data: null,
      },
      {
        name: 'Reports',
        route: '/admin/reports',
        data: null,
      },
      {
        name: 'Logs',
        route: '/admin/logs',
        data: null,
      }
    ];
  }

}
