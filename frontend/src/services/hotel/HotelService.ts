import { AxiosInstance } from 'axios';
import { baseUrl } from '../../constants/api';
import { AddHotel, Hotel, HotelFilterParams } from '../../types/HotelTypes';
import { Response } from '../../types/ResponseTypes';

export async function addHotel(
	axiosSecure: AxiosInstance,
	hotelDetails: AddHotel,
): Promise<Response<Hotel>> {
	const res = await axiosSecure.post<Response<Hotel>>(`${baseUrl}/hotel`, hotelDetails);
	return res.data;
}

export async function getHotels(
	axiosSecure: AxiosInstance,
	filterParams: HotelFilterParams | {},
): Promise<Response<Array<Hotel>>> {
	const finalFilterParams = filterParams || {};
	const res = await axiosSecure.get<Response<Array<Hotel>>>(`${baseUrl}/hotel`, {
		params: { finalFilterParams },
	});
	return res.data;
}
