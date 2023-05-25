import { ErrorResponse, Response } from '../types/ResponseTypes';

export function isErrorResponse<T>(
	response: Response<T> | ErrorResponse,
): response is ErrorResponse {
	if (response.data === null || (response.status !== 200 && response.status !== 201)) {
		return true;
	}

	return false;
}
