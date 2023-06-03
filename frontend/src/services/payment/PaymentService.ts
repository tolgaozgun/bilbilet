import { AxiosInstance } from 'axios';
import { baseUrl } from '../../constants/api';
import { BalanceAddFundsRequest, Transaction } from '../../types/PaymentTypes';
import { Response } from '../../types/ResponseTypes';

export async function uploadMoneyToBalance(
	axiosSecure: AxiosInstance,
	balanceUploadRequest: BalanceAddFundsRequest,
) {
	const res = await axiosSecure.post<Response<Transaction>>(
		`${baseUrl}/transaction/add-funds`,
		balanceUploadRequest,
	);
	return res.data;
}
