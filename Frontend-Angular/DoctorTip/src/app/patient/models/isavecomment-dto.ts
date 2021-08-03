export class SaveCommentDTO {
     commentContent : string ;
	 docId : number;
	 userId : number;


  constructor(commentContent: string , docId: number, userId: number) {
    this.commentContent = commentContent
    this.docId = docId
    this.userId = userId
  }

      

    }