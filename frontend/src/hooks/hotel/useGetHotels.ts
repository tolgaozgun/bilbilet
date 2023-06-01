import { getHotels } from '../../services/hotel';
import { useQuery } from '@tanstack/react-query';

const useGetHotels = (location: string) => {
	return useQuery({
		queryKey: ['getHotels'],
		queryFn: () => getHotels(location),
	});
};

export default useGetHotels;
