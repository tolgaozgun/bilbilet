import { useQuery } from '@tanstack/react-query';
import { AxiosInstance } from 'axios';
import { getFlightFares } from '../../services/fare';
import { FareSearchParams } from '../../types';

const useFlightFares = (
	axiosSecure: AxiosInstance,
	filterParams: FareSearchParams | {},
) => {
	return useQuery({
		queryKey: ['flightFares', filterParams],
		queryFn: () => getFlightFares(axiosSecure, filterParams),
	});
};

export default useFlightFares;
