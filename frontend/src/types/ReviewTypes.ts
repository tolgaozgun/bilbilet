export type AddCompanyReview = ReviewContents & {
	userId: number;
	companyId: number;
};

export type AddTripReview = ReviewContents & {
	userId: number;
	ticketId: number;
};

export type Review = ReviewContents & {
	userId: number;
	reviewId: number;
};

export type ReviewContents = {
	comment: string;
	punctuality: number;
	cleanliness: number;
	customerService: number;
};

export type RCompanyReview = {
	companyId: number;
	companyTitle: string;
	review: Review;
};

export type RTripReview = {
	ticketId: number;
	review: Review;
};

export type RTripReviewDetailedPublic = {
	ticketId: number;
	review: Review;
	depStationTitle: string;
	depStationAbbr: string;
	arrStationTitle: string;
	arrStationAbbr: string;
	depTime: Date;
	arrTime: Date;
	companyTitle: string;
};

export type RTripReviewDetailedPrivate = {
	ticketId: number;
	review: Review;
	ticketStatus: string;
	depStationTitle: string;
	depStationAbbr: string;
	arrStationTitle: string;
	arrStationAbbr: string;
	depTime: Date;
	arrTime: Date;
	price: number;
	companyTitle: string;
};

export type RReviewAvg = {
	avgPunct: number;
	avgClean: number;
	avgCustSer: number;
};
