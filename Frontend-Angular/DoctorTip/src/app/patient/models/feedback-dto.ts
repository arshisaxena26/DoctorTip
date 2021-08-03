export class FeedbackDTO {
	feedbackContent : string;
	feedbackRating : number;

  constructor(feedbackContent: string, feedbackRating: number) {
    this.feedbackContent = feedbackContent
    this.feedbackRating = feedbackRating
  }

}

