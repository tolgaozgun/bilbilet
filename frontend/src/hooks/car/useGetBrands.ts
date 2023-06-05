import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getBrands } from '../../services/car';

const useGetBrands = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getCarBrands'],
		queryFn: () => getBrands(axiosSecure),
	});
};

export default useGetBrands;
