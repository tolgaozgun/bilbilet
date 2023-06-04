import { useQuery } from '@tanstack/react-query';
import { AxiosInstance } from 'axios';
import { getBusFares } from '../../services/fare';
import { FareSearchParams } from '../../types';

const useBusFares = (axiosSecure: AxiosInstance, filterParams: FareSearchParams | {}) => {
	return useQuery({
		queryKey: ['flightFares', filterParams],
		queryFn: () => getBusFares(axiosSecure, filterParams),
	});
};

export default useBusFares;
