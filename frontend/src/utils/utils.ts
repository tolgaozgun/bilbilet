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

export function convertDateToTime(date: Date): string {
	if (date === null || date === undefined || date.toString() === 'Invalid Date') {
		return '';
	}
	const hours = date.getHours().toString().padStart(2, '0');
	const minutes = date.getMinutes().toString().padStart(2, '0');
	return `${hours}:${minutes}`;
}

export function getTimeDifference(start: Date, end: Date): string {
	const differenceInMilliseconds = end.getTime() - start.getTime();
	const hours = Math.floor(differenceInMilliseconds / (1000 * 60 * 60));
	const minutes = Math.floor(
		(differenceInMilliseconds % (1000 * 60 * 60)) / (1000 * 60),
	);
	return `${hours} hours ${minutes} minutes`;
}

export function formatDate(date: Date): string {
	const months: string[] = [
		'January',
		'February',
		'March',
		'April',
		'May',
		'June',
		'July',
		'August',
		'September',
		'October',
		'November',
		'December',
	];

	const day: number = date.getDate();
	const month: string = months[date.getMonth()];
	const year: number = date.getFullYear();

	return `${day}, ${month} ${year}`;
}
