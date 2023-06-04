import { useQuery } from '@tanstack/react-query';
import { AxiosInstance } from 'axios';
import { getFlightFares } from '../../services/fare';
import { FlightFilterParams } from '../../types/FlightFareTypes';

const useFlightFares = (
	axiosSecure: AxiosInstance,
	filterParams: FlightFilterParams | {},
) => {
	return useQuery({
		queryKey: ['flightFares', filterParams],
		queryFn: () => getFlightFares(axiosSecure, filterParams),
	});
};

export default useFlightFares;
