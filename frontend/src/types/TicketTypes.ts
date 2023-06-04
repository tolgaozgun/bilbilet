import { SeatType, TicketStatus } from './SeatTypes';

export type BuyTicketDto = {
	ticketId: number;
	userId: number;
};

export type RUserTicketView = {
	ticketId: number;
	ticketStatus: TicketStatus;
	seatType: SeatType;
	seatRow: number;
	seatColumn: number;
	fareId: number;
	travelerId: number;
	totalPrice: number;
	departureTime: Date;
	arrivalTime: Date;
	companyTitle: number;
	depStationTitle: number;
	depStationAbbr: number;
	arrStationTitle: number;
	arrStationAbbr: number;
};
