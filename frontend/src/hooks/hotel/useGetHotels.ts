import { useQuery } from '@tanstack/react-query';
import { AxiosInstance } from 'axios';
import { getHotels } from '../../services/hotel';
import { HotelFilterParams } from '../../types';

const useGetHotels = (
	axiosSecure: AxiosInstance,
	filterParams: HotelFilterParams | {},
) => {
	return useQuery({
		queryKey: ['getHotels'],
		queryFn: () => getHotels(axiosSecure, filterParams),
	});
};

export default useGetHotels;
