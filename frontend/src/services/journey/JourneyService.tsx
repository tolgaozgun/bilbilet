import { AxiosInstance } from 'axios';
import { baseUrl } from '../../constants/api';
import { CreateJourney, Journey, JourneyPlan } from '../../types';
import { Response } from '../../types/ResponseTypes';

export async function getJourneyPlans(axiosSecure: AxiosInstance, userId: number) {
	const res = await axiosSecure.get<Response<JourneyPlan[]>>(
		`${baseUrl}/journeyPlan/user/${userId}`,
	);
	return res.data;
}
