import { AxiosInstance } from 'axios';
import { getAddresses } from '../../services/location';
import { useQuery } from '@tanstack/react-query';

const useGetAddresses = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getAddresses'],
		queryFn: () => getAddresses(axiosSecure),
	});
};

export default useGetAddresses;
