import { AxiosInstance } from 'axios';
import { baseUrl } from '../../constants/api';
import { CreateJourney, Journey, JourneyPlan } from '../../types';

export async function getJourneyPlans(axiosSecure: AxiosInstance, userId: number) {
	const res = await axiosSecure.get<JourneyPlan[]>(
		`${baseUrl}/journeyPlan/user/${userId}`,
	);
	return res.data;
}

export async function getJourneys(axiosSecure: AxiosInstance, journeyPlanId: number) {
	const res = await axiosSecure.get<Journey[]>(
		`${baseUrl}/journey/${journeyPlanId}/journey`,
	);
}

export async function addJourney(
	axiosSecure: AxiosInstance,
	createJourney: CreateJourney,
) {
	const res = await axiosSecure.post<Journey>(`${baseUrl}/journeyPlan`, createJourney);
	return res.data;
}
