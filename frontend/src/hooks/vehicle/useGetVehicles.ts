import { useQuery } from '@tanstack/react-query';
import { AxiosInstance } from 'axios';
import { getCompanyVehicles } from '../../services/vehicle/VehicleService';

const useGetVehicles = (
	axiosSecure: AxiosInstance,
    companyId: number,
) => {
	return useQuery({
		queryKey: ['getCompanyVehicles', companyId],
		queryFn: () => getCompanyVehicles(axiosSecure, {}, companyId),
		enabled: !!companyId,
	});
};

export default useGetVehicles;
