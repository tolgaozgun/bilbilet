import { axiosSecure } from '../axios';
import { baseUrl } from '../../constants/api';
import { Response } from '../../types/ResponseTypes';
import { AddHotel, Hotel } from '../../types/HotelTypes';

export async function addHotel(hotelDetails: AddHotel): Promise<Response<Hotel>> {
	const res = await axiosSecure.post<Response<Hotel>>(
		`${baseUrl}/hotels/add`,
		hotelDetails,
	);
	return res.data;
}
