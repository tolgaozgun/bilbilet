import { useQuery } from '@tanstack/react-query';
import { getStations } from '../../services/location';

const useGetStations = () => {
	return useQuery({
		queryKey: ['getStations'],
		queryFn: () => getStations(),
	});
};

export default useGetStations;
