import { isErrorResponse } from '../../utils/utils';
import { addHotel as addHotelFn } from '../../services/hotel';
import { AddHotel } from '../../types/HotelTypes';
export const useAddHotel = () => {
	const addHotel = async (hotelDetails: AddHotel) => {
		const res = await addHotelFn(hotelDetails);

		return res;
	};

	return { addHotel };
};
