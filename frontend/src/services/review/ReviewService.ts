import { baseUrl } from '../../constants/api';
import { Response } from '../../types/ResponseTypes';
import { AxiosInstance } from 'axios';
import {
	AddCompanyReview,
	AddTripReview,
	RCompanyReview,
	RReviewAvg,
	RTripReview,
	RTripReviewDetailedPrivate,
	RTripReviewDetailedPublic,
} from '../../types/ReviewTypes';

export async function addCompanyReview(
	axiosSecure: AxiosInstance,
	reviewDetails: AddCompanyReview,
) {
	const res = await axiosSecure.post<Response<RCompanyReview>>(
		`${baseUrl}/address`,
		reviewDetails,
	);
	return res.data;
}

export async function addTripReview(
	axiosSecure: AxiosInstance,
	reviewDetails: AddTripReview,
) {
	const res = await axiosSecure.post<Response<RTripReview>>(
		`${baseUrl}/address`,
		reviewDetails,
	);
	return res.data;
}

export async function getCompanyReviews(axiosSecure: AxiosInstance, companyId: number) {
	const res = await axiosSecure.get<Response<Array<RCompanyReview>>>(
		`${baseUrl}/review/company/${companyId}`,
	);
	return res.data;
}

export async function getCompanyTripsReviews(
	axiosSecure: AxiosInstance,
	companyId: number,
) {
	const res = await axiosSecure.get<Response<Array<RTripReviewDetailedPublic>>>(
		`${baseUrl}/review/trip/${companyId}`,
	);
	return res.data;
}

export async function getMyTripReviews(axiosSecure: AxiosInstance, userId: number) {
	const res = await axiosSecure.get<Response<Array<RTripReviewDetailedPrivate>>>(
		`${baseUrl}/review/trip/user/${userId}`,
	);
	return res.data;
}

export async function getMyCompanyReviews(axiosSecure: AxiosInstance, userId: number) {
	const res = await axiosSecure.get<Response<Array<RCompanyReview>>>(
		`${baseUrl}/review/company/user/${userId}`,
	);
	return res.data;
}

export async function getCompanyAverages(axiosSecure: AxiosInstance, companyId: number) {
	const res = await axiosSecure.get<Response<RReviewAvg>>(
		`${baseUrl}/review/company/${companyId}/avg`,
	);
	return res.data;
}

export async function getTripAverageByCompany(
	axiosSecure: AxiosInstance,
	companyId: number,
) {
	const res = await axiosSecure.get<Response<RReviewAvg>>(
		`${baseUrl}/review/trip/${companyId}/avg`,
	);
	return res.data;
}
