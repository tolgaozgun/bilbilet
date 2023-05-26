export type AddHotel = {
	name: string;
	avgPrice: number;
	telephone: string;
	websiteUrl: string;
	coverPhotoUrl: string;
	photoUrl: string;
	rating: number;
};

export type Hotel = AddHotel & {
	hotel_id: number;
};
