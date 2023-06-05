import { AxiosInstance } from 'axios';
import { baseUrl } from '../../constants/api';
import { Response } from '../../types/ResponseTypes';
import { AllSystemReport } from '../../types/SystemReport';

export async function getSystemReports(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<AllSystemReport>>(
		`${baseUrl}/sysReport`,
	);
	return res.data;
}
