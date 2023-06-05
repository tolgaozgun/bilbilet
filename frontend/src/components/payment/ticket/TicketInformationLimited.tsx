import { Card, Divider, Flex, Stack, Text, Title } from '@mantine/core';
import { SeatTicket } from '../../../types/SeatTypes';
import { convertFlightColumnToAlphabetic } from '../../../utils/utils';
import { useQueries } from '@tanstack/react-query';
import { getFareView, getVehicleSeatConfig } from '../../../services/fare';
import { axiosSecure } from '../../../services/axios';
import useAxiosSecure from '../../../hooks/auth/useAxiosSecure';
import LoadingPage from '../../../pages/LoadingPage';
import ItemsNotFoundPage from '../../common/feedback/ItemsNotFoundPage';
import { getTicketByUserId } from '../../../services/payment/TicketService';
import { useUser } from '../../../hooks/auth';

interface TicketInformationProps {
	ticket: SeatTicket;
}

const TicketInformationLimited = ({ ticket }: TicketInformationProps) => {
	const { seatColumn, seatRow, seatType, ticketStatus, totalPrice, ticketId } = ticket;

	return (
		<Card withBorder p={24}>
			<Stack spacing="lg">
				<Title>Ticket Information</Title>
				<Divider />
				<Flex direction="column">
					<Title order={4}>Seat - Type</Title>
					<Text>
						{seatRow}{convertFlightColumnToAlphabetic(seatColumn)} - {seatType}
					</Text>
					<Divider />
				</Flex>
				<Flex direction="column">
					<Title order={4}>Price - Status</Title>
					<Text>{totalPrice} TL - {ticketStatus}</Text>
					<Divider />
				</Flex>
			</Stack>
		</Card>
	);
};

export default TicketInformationLimited;
