import { AxiosInstance } from 'axios';
import { baseUrl } from '../../constants/api';
import { Fare } from '../../types';
import { FlightFilterParams } from '../../types/FlightFareTypes';
import { Response } from '../../types/ResponseTypes';
import { paramsSerializer } from '../../utils/utils';

export async function getFlightFares(
	axiosSecure: AxiosInstance,
	filterParams: FlightFilterParams | {},
) {
	const finalFilterParams = filterParams || {};
	const res = await axiosSecure.get<Response<Fare[]>>(`${baseUrl}/fare/plane`, {
		params: Object.keys(finalFilterParams).length === 0 ? {} : finalFilterParams,
		paramsSerializer: paramsSerializer,
	});
	return res.data;
}
