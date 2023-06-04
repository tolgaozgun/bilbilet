import { Axios, AxiosInstance } from 'axios';
import { baseUrl } from '../../constants/api';
import {
	PaymentWithBalanceRequest,
	PaymentWithCCRequest,
	Transaction,
	UploadMoneyToBalanceWithCCRequest,
} from '../../types/PaymentTypes';
import { Response } from '../../types/ResponseTypes';
import { RUserTicketView } from '../../types/TicketTypes';

export async function uploadMoneyToBalance(
	axiosSecure: AxiosInstance,
	balanceUploadRequest: UploadMoneyToBalanceWithCCRequest,
) {
	const res = await axiosSecure.post<Response<Transaction>>(
		`${baseUrl}/transaction/add-funds`,
		balanceUploadRequest,
	);
	return res.data;
}

export async function payTicketWithBalance(
	axiosSecure: AxiosInstance,
	paymentRequest: PaymentWithBalanceRequest,
) {
	const res = await axiosSecure.post<Response<RUserTicketView>>(
		`${baseUrl}/ticket/purchase/balance`,
		paymentRequest,
	);
	return res.data;
}

export async function payTicketPriceWithCC(
	axiosSecure: AxiosInstance,
	paymentRequest: PaymentWithCCRequest,
) {
	const res = await axiosSecure.post<Response<RUserTicketView[]>>(
		`${baseUrl}/ticket/purchase/cc`,
		paymentRequest,
	);
	return res.data;
}
