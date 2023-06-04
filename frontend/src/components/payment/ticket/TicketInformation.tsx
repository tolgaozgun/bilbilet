import { Card, Divider, Flex, Stack, Text, Title } from '@mantine/core';
import { SeatTicket } from '../../../types/SeatTypes';
import { convertFlightColumnToAlphabetic } from '../../../utils/utils';

interface TicketInformationProps {
	ticket: SeatTicket;
}

const TicketInformation = ({ ticket }: TicketInformationProps) => {
	console.log(ticket);
	const { seatColumn, seatRow, seatType, ticketStatus, totalPrice } = ticket;
	return (
		<Card withBorder p={24}>
			<Stack spacing="lg">
				<Title>Ticket Information</Title>
				<Divider />
				<Flex direction="column">
					<Title order={4}>Seat location</Title>
					<Text>Seat row: {seatRow}</Text>
					<Text>
						Seat column: {convertFlightColumnToAlphabetic(seatColumn)}
					</Text>
					<Divider />
				</Flex>
				<Flex direction="column">
					<Title order={4}>Seat type</Title>
					<Text>{seatType}</Text>
					<Divider />
				</Flex>
				<Flex direction="column">
					<Title order={4}>Ticket price</Title>
					<Text>{totalPrice} TL</Text>
					<Divider />
				</Flex>
			</Stack>
		</Card>
	);
};

export default TicketInformation;
