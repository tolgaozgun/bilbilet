import { Card, Divider, Flex, Stack, Text, Title } from '@mantine/core';
import { useQueries } from '@tanstack/react-query';
import { useUser } from '../../../hooks/auth';
import useAxiosSecure from '../../../hooks/auth/useAxiosSecure';
import LoadingPage from '../../../pages/LoadingPage';
import { axiosSecure } from '../../../services/axios';
import { getFareView, getVehicleSeatConfig } from '../../../services/fare';
import { getTicketByUserId } from '../../../services/payment/TicketService';
import { SeatTicket } from '../../../types/SeatTypes';
import { convertFlightColumnToAlphabetic } from '../../../utils/utils';
import SubtleLinkButton from '../../common/buttons/SubtleLinkButton';
import ItemsNotFoundPage from '../../common/feedback/ItemsNotFoundPage';

interface TicketInformationProps {
	ticket: SeatTicket;
	cTitle?: string;
	depTime: string;
	arrTime: string;
	depDate: string;
	arrDate: string;
	duration: string;
}

const TicketInformation = ({
	ticket,
	cTitle,
	depTime,
	arrTime,
	depDate,
	arrDate,
	duration,
}: TicketInformationProps) => {
	const { seatColumn, seatRow, seatType, ticketStatus, totalPrice, ticketId } = ticket;
	let ticketCompanyName = cTitle;

	if (!ticketCompanyName) {
		ticketCompanyName = 'Ticket Information';
	}

	return (
		<Card withBorder p={24}>
			<Stack spacing="lg">
				<Title>{ticketCompanyName}</Title>
				<Divider />
				<Flex direction="column">
					<Title order={4}>Seat - Type</Title>
					<Text>
						{seatRow}
						{convertFlightColumnToAlphabetic(seatColumn)} - {seatType}
					</Text>
					<Divider />
				</Flex>
				<Flex direction="column">
					<Title order={4}>Price - Status</Title>
					<Text>
						{totalPrice} TL - {ticketStatus}
					</Text>
					<Divider />
				</Flex>
				<Flex direction="column">
					<Title order={4}>Departure Date & Time</Title>
					<Text>
						{depDate} {depTime}
					</Text>
					<Divider />
				</Flex>
				<Flex direction="column">
					<Title order={4}>Arrival Date & Time</Title>
					<Text>
						{arrDate} {arrTime}
					</Text>
					<Divider />
				</Flex>
				<Flex direction="column">
					<Title order={4}>Duration</Title>
					<Text>{duration}</Text>
					<Divider />
				</Flex>
				<SubtleLinkButton to={`/add-review/trip/${ticketId}`}>
					Review Trip
				</SubtleLinkButton>
				<SubtleLinkButton to="/add-review/company/:id">
					Review company
				</SubtleLinkButton>
			</Stack>
		</Card>
	);
};

export default TicketInformation;
