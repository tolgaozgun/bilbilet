import { baseUrl } from '../../constants/api';
import { Response } from '../../types/ResponseTypes';
import { AddHotel, Hotel } from '../../types/HotelTypes';
import { AxiosInstance } from 'axios';

export async function addHotel(
	axiosSecure: AxiosInstance,
	hotelDetails: AddHotel,
): Promise<Response<Hotel>> {
	const res = await axiosSecure.post<Response<Hotel>>(`${baseUrl}/hotel`, hotelDetails);
	return res.data;
}

export async function getHotels(
	axiosSecure: AxiosInstance,
	city: string,
	country: string,
): Promise<Response<Array<Hotel>>> {
	const res = await axiosSecure.get<Response<Array<Hotel>>>(
		`${baseUrl}/hotel/${city}/${country}`,
	);
	return res.data;
}
