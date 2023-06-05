import { useQuery } from '@tanstack/react-query';
import { AxiosInstance } from 'axios';
import { getSystemReports } from '../../services/system/SystemService';

const useSystemReports = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getSystemReports'],
		queryFn: () => getSystemReports(axiosSecure),
	});
};

export default useSystemReports;
