import { AxiosInstance } from 'axios';
import { getCars } from '../../services/car';
import { useQuery } from '@tanstack/react-query';

const useGetCars = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getCars'],
		queryFn: () => getCars(axiosSecure),
	});
};

export default useGetCars;
