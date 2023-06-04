import { AxiosInstance } from 'axios';
import { baseUrl } from '../../constants/api';
import { AddHotel, Hotel, HotelFilterParams } from '../../types/HotelTypes';
import { Response } from '../../types/ResponseTypes';
import { AddCompanyBus, AddCompanyPlane, CompanyVehicle } from '../../types/VehicleTypes';

export async function addCompanyBus(
	axiosSecure: AxiosInstance,
	vehicleDetails: AddCompanyBus,
): Promise<Response<CompanyVehicle>> {
	const res = await axiosSecure.post<Response<CompanyVehicle>>(`${baseUrl}/company-vehicles/create/bus`, vehicleDetails);
	return res.data;
}

export async function addCompanyPlane(
	axiosSecure: AxiosInstance,
	vehicleDetails: AddCompanyPlane,
): Promise<Response<CompanyVehicle>> {
	const res = await axiosSecure.post<Response<CompanyVehicle>>(`${baseUrl}/company-vehicles/create/plane`, vehicleDetails);
	return res.data;
}

export async function getCompanyVehicles(
	axiosSecure: AxiosInstance,
	filterParams: HotelFilterParams | {},
    companyId: number,
): Promise<Response<Array<CompanyVehicle>>> {
	const finalFilterParams = filterParams || {};
	const res = await axiosSecure.get<Response<Array<CompanyVehicle>>>(`${baseUrl}/company-vehicles/company/${companyId}`, {
		params: Object.keys(finalFilterParams).length === 0 ? {} : finalFilterParams,
		paramsSerializer: function paramsSerializer(params) {
			return Object.entries(Object.assign({}, params))
				.map(([key, value]) => {
					if (Array.isArray(value)) {
						return `${key}=${value.join(',')}`;
					}
					return `${key}=${value}`;
				})
				.join('&');
		},
	});
	return res.data;
}
