import { AxiosInstance } from 'axios';
import { baseUrl } from '../../constants/api';
import { PaymentWithCCRequest, Transaction } from '../../types/PaymentTypes';
import { Response } from '../../types/ResponseTypes';

export async function uploadMoneyToBalance(
	axiosSecure: AxiosInstance,
	balanceUploadRequest: PaymentWithCCRequest,
) {
	const res = await axiosSecure.post<Response<Transaction>>(
		`${baseUrl}/transaction/add-funds`,
		balanceUploadRequest,
	);
	return res.data;
}

export async function payTicketPriceWithCC(
	axiosSecure: AxiosInstance,
	paymentRequest: PaymentWithCCRequest,
) {
	const res = await axiosSecure.post<Response<Transaction>>(
		`${baseUrl}/transaction/card-payment`,
		paymentRequest,
	);
	return res.data;
}
