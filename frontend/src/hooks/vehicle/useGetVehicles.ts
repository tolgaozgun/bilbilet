import { useQuery } from '@tanstack/react-query';
import { AxiosInstance } from 'axios';
import { getCompanyVehicles } from '../../services/vehicle/VehicleService';

const useGetVehicles = (
	axiosSecure: AxiosInstance,
    companyId: number,
) => {
	return useQuery({
		queryKey: ['getCompanyVehicles'],
		queryFn: () => getCompanyVehicles(axiosSecure, {}, companyId),
	});
};

export default useGetVehicles;
