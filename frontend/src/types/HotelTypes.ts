export type Hotel = {
	name: string;
	avgPrice: number;
	telephone: string;
	websiteUrl: string;
	coverPhotoUrl: string;
	photoUrl: string;
	rating: number;
	hotelId: number;
	addressId: number;
};

export type AddHotel = {
	hotel: Hotel;
	city: string;
	country: string;
};

export type HotelFilterParams = {
	address_id: number | '';
	name: string;
	avg_price: [number, number];
	order_by: [HotelSortByOptions, HotelSortDirections];
	rating?: [number, number];
};

export type HotelSortBy = {
	sortBy: HotelSortByOptions;
	sortDirection: HotelSortDirections;
};

export type HotelSortDirections = 'ASC' | 'DESC' | '';

export type HotelSortByOptions = 'name' | 'avg_price' | '';

export const hotelSortByOptionsToRealValues = {
	name: 'Name',
	avg_price: 'Average Price',
};

export const hotelSortByOptions = [
	{ value: 'name', label: 'Name' },
	{ value: 'avg_price', label: 'Average Price' },
];
