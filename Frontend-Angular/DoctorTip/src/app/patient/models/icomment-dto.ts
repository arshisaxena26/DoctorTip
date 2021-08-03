import { Byte } from "@angular/compiler/src/util";

export interface CommentDTO {
    commentId:number;
	commentContent:string;
    commentReply : string;
    isLiked : boolean;
	patientName : string;
	image : any;
    }
    