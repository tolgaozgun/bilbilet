import { useQuery } from '@tanstack/react-query';
import { AxiosInstance } from 'axios';
import { getStations } from '../../services/location';
import { StationType } from '../../types/LocationTypes';

const useGetStations = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getStations'],
		queryFn: () => getStations(axiosSecure),
	});
};

export default useGetStations;
