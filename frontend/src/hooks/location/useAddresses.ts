import { useQuery } from '@tanstack/react-query';
import { AxiosInstance } from 'axios';
import { getAddresses } from '../../services/location';

const useAddresses = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getAddresses'],
		queryFn: () => getAddresses(axiosSecure),
	});
};

export default useAddresses;
