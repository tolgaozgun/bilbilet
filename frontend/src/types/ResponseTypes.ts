export type Response<T> = SuccessResponse<T> | ErrorResponse;
export interface SuccessResponse<T> {
	status: number;
	msg: string;
	data: T;
}

export interface ErrorResponse {
	status: number;
	msg: string;
	data: null;
}
