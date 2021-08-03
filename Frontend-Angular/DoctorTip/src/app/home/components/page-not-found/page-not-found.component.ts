import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-page-not-found',
  templateUrl: './page-not-found.component.html',
  styleUrls: ['./page-not-found.component.scss']
})
export class PageNotFoundComponent implements OnInit {

  msg: any;
  statusCode: any;
  constructor() { }

  ngOnInit(): void {
    this.msg = history.state.errormsg;
    this.statusCode = history.state.data;
    console.log(this.msg);
  }

}
