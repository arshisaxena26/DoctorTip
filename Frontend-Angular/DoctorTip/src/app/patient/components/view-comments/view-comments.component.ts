import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Subscription } from 'rxjs';
import { CommentDTO } from '../../models/icomment-dto';
import { SaveCommentDTO } from '../../models/isavecomment-dto';
import { PatientService } from '../../services/patient.service';

@Component({
  selector: 'app-view-comments',
  templateUrl: './view-comments.component.html',
  styleUrls: ['./view-comments.component.scss']
})
export class ViewCommentsComponent implements OnInit {

  subscription: Subscription | undefined;
  @Input() docid: number;
  userId : any;
  comments : CommentDTO[];
  saveComment : SaveCommentDTO;
  commentContent :any;
  
  constructor(private _patientService: PatientService,
    private _router: Router, private _activeroute: ActivatedRoute) { 

      // this.docid=this._activeroute.snapshot.paramMap.get('id');
      
    }

  ngOnInit(): void {
    this.userId = sessionStorage.getItem('userId');
    this.subscription = this._patientService
      .showComment(this.docid)
      .subscribe((res: any) => {
        this.comments = res;
        this.comments.forEach(item => {
          item.image = 'data:image/jpeg;base64,' + item.image
        });
     });
  }

  onSubmit(commentForm : any){
    this.commentContent=commentForm;
    this.saveComment= new SaveCommentDTO(this.commentContent,this.docid,this.userId);
    console.log(this.saveComment);
    
    this.subscription = this._patientService.saveComment(this.saveComment).subscribe((res: any) => {
      location.reload();
    });

  }
  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
