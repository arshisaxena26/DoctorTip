import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { PatientService } from '../../services/patient.service';
@Component({
  selector: 'app-view-docprofile',
  templateUrl: './view-docprofile.component.html',
  styleUrls: ['./view-docprofile.component.scss']
})
export class ViewDocprofileComponent implements OnInit, OnDestroy {

  subscription: Subscription | undefined;
  docid: any;
  doctor: any;
  likeDislike: any;
  likeCount: any;
  dislikeCount: any;

  userId: any;
  constructor(private _patientService: PatientService,
    private _router: Router, private _activeroute: ActivatedRoute) {
    this.docid = this._activeroute.snapshot.paramMap.get('id');
    this.userId = sessionStorage.getItem('userId');
    this.subscription = this._patientService.showLikeDislike(this.docid, this.userId).subscribe((res: any) => {
      this.likeDislike = res;
      console.log(this.likeDislike);
    });

    this.subscription = this._patientService.showLikeCount(this.docid).subscribe((res: any) => {
      this.likeCount = res;
      console.log(this.likeCount);
    });

    this.subscription = this._patientService.showDisikeCount(this.docid).subscribe((res: any) => {
      this.dislikeCount = res;
      console.log(this.likeCount);
    });


  }

  ngOnInit(): void {
    this.subscription = this._patientService
      .showDoctorProfile(this.docid)
      .subscribe((res: any) => {
        this.doctor = res;
        this.doctor.doctorImage = 'data:image/jpeg;base64,' + this.doctor.doctorImage;
      });
  }
  likefunction(like: boolean, dislike: boolean) {
    console.log(like);
    console.log(dislike);

    this.likeDislike.like = like;
    this.likeDislike.dislike = dislike;

    console.log(this.likeDislike);

    this.subscription = this._patientService.setLikeDislike(this.likeDislike).subscribe((res: any) => {
      location.reload();
    });
  }

  bookAppointment() {
    this._router.navigate(["/patient/book-appointment"]);
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
