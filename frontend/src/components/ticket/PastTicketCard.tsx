import { Card, Flex, Title, Text } from '@mantine/core';
import { IconPlane, IconArrowRight, IconEdit } from '@tabler/icons-react';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';
import { Link } from 'react-router-dom';
import { RUserTicketView } from '../../types/TicketTypes';

interface PastTicketCardProps {
	ticket: RUserTicketView;
}
const PastTicketCard = ({ ticket }: PastTicketCardProps) => {
	return (
		<Card shadow="xl" withBorder radius={'lg'}>
			<Flex direction={'column'} gap={'xs'} align={'center'}>
				<Flex direction={'row'} gap={'sm'} align={'center'}>
					<IconPlane></IconPlane>
					<Title order={3}>{ticket.companyTitle}</Title>
				</Flex>
				<Flex direction={'row'} gap={'xl'}>
					<Flex direction={'column'} gap={'xs'} align={'center'}>
						<Text fw={700}>{ticket.departureTime.getTime()}</Text>
						<Text>{ticket.arrStationAbbr}</Text>
						<Text>{ticket.depStationTitle}</Text>
					</Flex>

					<Flex
						direction={'column'}
						gap={0}
						align={'center'}
						justify={'center'}
					>
						<IconArrowRight size={40}></IconArrowRight>
					</Flex>

					<Flex direction={'column'} gap={'xs'} align={'center'}>
						<Text fw={700}>{ticket.arrivalTime.getTime()}</Text>
						<Text>{ticket.arrStationAbbr}</Text>
						<Text>{ticket.arrStationTitle}</Text>
					</Flex>
					<Flex direction={'column'} gap={'xs'} align={'center'}>
						<Link to={`/add-review/trip/${5}`}>
							<CustomElevatedButton
								leftIcon={<IconEdit />}
								text={'Review Trip'}
							></CustomElevatedButton>
						</Link>
						<Link to={`/add-review/company/${4}`}>
							<CustomElevatedButton
								leftIcon={<IconEdit />}
								text={'Review Company'}
							></CustomElevatedButton>
						</Link>
					</Flex>
				</Flex>
				<Flex direction={'row'} gap={'xl'}>
					<Text fw={700}>
						Seat:
						<Text span>
							{' '}
							{ticket.seatColumn} {ticket.seatRow}
						</Text>
					</Text>
					<Text fw={700}>
						Price:
						<Text span fw={400}>
							{' '}
							{ticket.totalPrice}
							{' TL'}
						</Text>
					</Text>
					<Text fw={700}>
						Status:
						<Text span color="green">
							{' '}
							{ticket.ticketStatus}
						</Text>
					</Text>
				</Flex>
			</Flex>
		</Card>
	);
};

export default PastTicketCard;
