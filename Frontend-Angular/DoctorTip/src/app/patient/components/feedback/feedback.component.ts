import { Component, OnInit } from '@angular/core';
import { PatientService } from '../../services/patient.service';
import { Subscription } from 'rxjs';
import { FeedbackDTO } from '../../models/feedback-dto';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.scss'],
})
export class FeedbackComponent implements OnInit {
  subscription: Subscription | undefined;
  rating: number;
  content: string;
  feedback: FeedbackDTO;
  id: any;
  constructor(private _patientService: PatientService,private _toastrService: ToastrService,private _router: Router) {}

  ngOnInit(): void {
    this._toastrService.toastrConfig.positionClass = 'toast-top-center';
  }

  // onSubmit() {
  //   this.feedback = new FeedbackDTO(this.content, this.rating);
  //   this.id = sessionStorage.getItem('feedid');
  //   console.log(this.feedback);
  //   console.log(this.rating);
  //   if (this.feedback == null) {
  //     alert('please fill appropriate feedback');
  //   } else if (this.content == null) {
  //     alert('Please fill Feedback');
  //   } else {
  //     this.subscription = this._patientService
  //       .addFeedback(this.feedback, this.id)
  //       .subscribe((res: any) => {
  //         alert('Successfully Entered Feedback');
  //       });
  //   }
  // }

  onSubmit(feedbackForm : any){
    
    this.feedback = new FeedbackDTO(feedbackForm.value.content, feedbackForm.value.rating);
    this.id = sessionStorage.getItem('feedid');
    this.subscription = this._patientService
          .addFeedback(this.feedback, this.id)
          .subscribe((res: any) => {
            this._toastrService.success('Successfully Entered Feedback');
            this._router.navigate(["/patient/appointments"]);
          });
    
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
