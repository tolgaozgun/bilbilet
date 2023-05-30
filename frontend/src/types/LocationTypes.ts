export type Station = {
	title: string;
	stationId: number;
	stationType: StationType;
	addressId: number;
	city: string;
	country: string;
};
export type StationType = 'AIRPORT' | 'BUS_TERMINAL' | 'TRAIN_STATION' | 'PORT' | 'OTHER';
