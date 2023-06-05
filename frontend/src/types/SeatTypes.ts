export type VehicleSeatConfig = {
	configId: number;
	configName: string;
	seatingArrangement: string;
	configTotalRows: number;
	configTotalColumns: number;
	firstClassAfter: number;
	businessClassAfter: number;
	premiumEconomyClassAfter: number;
};

export type SeatTicket = {
	ticketId: number;
	ticketStatus: string;
	seatType: string;
	seatRow: number;
	seatColumn: number;
	fareId: number;
	totalPrice: number;
};

export enum TicketStatus {
	AVAILABLE = 'AVAILABLE',
	PURCHASED = 'PURCHASED',
	EXPIRED = 'EXPIRED',
	USED = 'USED',
	ON_HOLD = 'ON_HOLD',
	RESERVED = 'RESERVED',
	UNAVAILABLE = 'UNAVAILABLE',
	OTHER = 'OTHER',
}

export enum SeatType {
	ECONOMY = 'ECONOMY',
	PREMIUM_ECONOMY = 'PREMIUM_ECONOMY',
	BUSINESS = 'BUSINESS',
	FIRST_CLASS = 'FIRST_CLASS',
	OTHER = 'OTHER',
}

export type SeatLocation = [number, number];
