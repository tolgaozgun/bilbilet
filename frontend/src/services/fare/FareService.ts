import { axiosSecure } from '../axios';
import { baseUrl } from '../../constants/api';
import { Response } from '../../types/ResponseTypes';
import { AddFare, Fare } from '../../types/FareTypes';

export async function addFare(fareDetails: AddFare): Promise<Response<Fare>> {
	const res = await axiosSecure.post<Response<Fare>>(
		`${baseUrl}/fares/add`,
		fareDetails,
	);
	return res.data;
}
