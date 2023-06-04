import { useQuery } from '@tanstack/react-query';
import { AxiosInstance } from 'axios';
import { getCompanyVehicles } from '../../services/fare';

const useVehicles = (axiosSecure: AxiosInstance, companyId: number) => {
	return useQuery({
		queryKey: ['vehicles'],
		queryFn: () => getCompanyVehicles(axiosSecure, companyId),
	});
};

export default useVehicles;
