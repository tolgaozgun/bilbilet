import { baseUrl } from '../../constants/api';
import { Station } from '../../types/LocationTypes';
import { axiosSecure } from '../axios';
import { Response } from '../../types/ResponseTypes';

export async function getStations() {
	const res = await axiosSecure.get<Response<Station>>(`${baseUrl}/stations`);
	return res.data;
}
