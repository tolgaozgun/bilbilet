export type Station = AddStation & {
	stationId: number;
	addressId: number;
};

export type StationType = 'AIRPORT' | 'BUS_TERMINAL' | 'TRAIN_STATION' | 'PORT' | 'OTHER';

export type AddStation = {
	title: string;
	abbreviation: string;
	stationType: StationType;
	city: string;
	country: string;
};

export type AddAddress = {
	city: string;
	country: string;
	longitude: number;
	latitude: number;
};

export type Address = AddAddress & {
	addressId: number;
};
