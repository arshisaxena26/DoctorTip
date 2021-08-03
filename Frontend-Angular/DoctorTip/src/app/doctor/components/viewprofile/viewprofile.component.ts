import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { DoctorService } from '../../services/doctor.service';
import { Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-viewprofile',
  templateUrl: './viewprofile.component.html',
  styleUrls: ['./viewprofile.component.scss']
})
export class ViewprofileComponent implements OnInit, OnDestroy {

  subscription: Subscription | undefined;
  doctors: any;
  textWritten: any;
  docImage: any;
  docEmail: any;

  constructor(private _router: Router, private _doctorService: DoctorService, private sanitizer: DomSanitizer) { }

  ngOnInit(): void {

    this.subscription = this._doctorService
      .viewDoctorProfile()
      .subscribe((res: any) => {
        this.doctors = res;
        this.docImage = 'data:image/jpeg;base64,' + this.doctors.image;
        this.docEmail = sessionStorage.getItem('userEmail');
        // this.docImage=this.sanitizer.bypassSecurityTrustUrl(url);
        console.log(this.doctors);
      });
  }

  setAvailablity(doctorId: any) {
    console.log("hello");
    this._router.navigate(["/doctor/availablity/" + doctorId]);

  }



  heartClicked(commentId: number) {
    console.log("helo ")
    console.log(commentId)
    this._doctorService.heartclick(commentId).subscribe((res: any) => {
      console.log(commentId)
      console.log("dil color changed")
      location.reload();
    })
  }
  replied(commentId: number) {
    this.textWritten;
    this._doctorService
      .reply(commentId, this.textWritten)
      .subscribe((res: any) => {
        console.log(res);
        location.reload();
      });
  }



  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
