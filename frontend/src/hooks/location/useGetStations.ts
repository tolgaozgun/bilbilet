import { AxiosInstance } from 'axios';
import { getStations } from '../../services/location';
import { useQuery } from '@tanstack/react-query';

const useGetStations = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getStations'],
		queryFn: () => getStations(axiosSecure),
	});
};

export default useGetStations;
