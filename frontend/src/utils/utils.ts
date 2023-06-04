import { ErrorResponse, Response } from '../types/ResponseTypes';

export function isErrorResponse<T>(
	response: Response<T> | ErrorResponse,
): response is ErrorResponse {
	if (response.data === null || (response.status !== 200 && response.status !== 201)) {
		return true;
	}

	return false;
}

export function paramsSerializer(params: Record<string, any>) {
	return Object.entries(Object.assign({}, params))
		.map(([key, value]) => {
			if (Array.isArray(value)) {
				return `${key}=${value.join(',')}`;
			}
			return `${key}=${value}`;
		})
		.join('&');
}

export function convertFlightColumnToAlphabetic(num: number): string {
	if (num < 1 || num > 27) {
		return '';
	}

	return String.fromCharCode(64 + num);
}
