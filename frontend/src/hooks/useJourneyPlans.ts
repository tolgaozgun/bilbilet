import { useQuery } from '@tanstack/react-query';
import { AxiosInstance } from 'axios';
import { getJourneyPlans } from '../services/journey/JourneyService';

const useJourneyPlans = (axiosSecure: AxiosInstance, userId: number) => {
	return useQuery({
		queryKey: ['getHotels'],
		queryFn: () => getJourneyPlans(axiosSecure, userId),
	});
};

export default useJourneyPlans;
