import { Fare } from '.';

export type JourneyPlan = {
	journeyPlanId: number;
	planTitle: string;
	userId: number;
};

export type Journey = {
	journeyId: number;
	journeyPlanId: number;
	journeyTitle: string;
	fareId: number;
};

export type CreateJourney = {
	journeyPlanId: number;
	journeyTitle: string;
	fareId: number;
};

export type JourneyWithFare = Journey & Fare;
