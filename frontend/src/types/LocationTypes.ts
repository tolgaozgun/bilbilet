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
};
