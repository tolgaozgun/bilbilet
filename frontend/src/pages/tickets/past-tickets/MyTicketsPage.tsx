import { AVAILABLE_TRANSITIONS, Center, Flex, Tabs, Title } from '@mantine/core';
import PastTicketCard from '../../../components/ticket/PastTicketCard';
import SortPastTicketsBar from '../../../components/ticket/SortPastTicketsBar';
import PastTicketsFilter from '../../../components/ticket/PastTicketsFilter';
import useGetTickets from '../../../hooks/tickets/useTickets';
import useAxiosSecure from '../../../hooks/auth/useAxiosSecure';
import { useUser } from '../../../hooks/auth';
import LoadingPage from '../../LoadingPage';
import { IconPlane, IconBus, IconBuilding } from '@tabler/icons-react';
import BusTab from '../../../components/fare/bus/BusTab';
import PlaneTab from '../../../components/fare/plane/PlaneTab';
import HotelTab from '../../../components/hotel/HotelTab';
import FareInfoCard from '../../../components/fare/FareInfoCard';
import TicketInformation from '../../../components/payment/ticket/TicketInformation';

const MyTicketsPage = () => {
	const axiosSecure = useAxiosSecure();
	const user = useUser();
	const {
		isLoading: isTicketsLoading,
		isError: isTicketsError,
		data: ticketsData,
	} = useGetTickets(axiosSecure, user?.id!);
	if (isTicketsLoading || !ticketsData || isTicketsError) {
		return <LoadingPage></LoadingPage>;
	}
	const ticketsList = ticketsData?.data!;
	
	const ticketCards = ticketsList.map((ticket) => {
		return (
			<TicketInformation
				ticket={{
					ticketId: 0,
					ticketStatus: 'AVAILABE',
					seatType: 'ECONOMY',
					seatRow: 0,
					seatColumn: 0,
					fareId: 0,
					totalPrice: 0,
				}}
			></TicketInformation>
		);
	});
	return (
		<Center>
			<Tabs defaultValue="current">
				<Tabs.List>
					<Tabs.Tab icon={<IconPlane size="1rem" />} value="current">
						Current Tickets
					</Tabs.Tab>
					<Tabs.Tab icon={<IconBus size="1rem" />} value="past">
						Past
					</Tabs.Tab>
					<Tabs.Tab icon={<IconBuilding size="1rem" />} value="cancelled">
						Cancelled
					</Tabs.Tab>
				</Tabs.List>
				<Tabs.Panel value="current" pt="xs">
				<{ticketCards}> 
				</Tabs.Panel>
				<Tabs.Panel value="past" pt="xs">
						<{ticketCards}> 
				</Tabs.Panel>
				<Tabs.Panel value="cancelled" pt="xs">
						<{ticketCards}> 
				</Tabs.Panel>
			</Tabs>
			<Flex direction={'column'} gap={'sm'}>
				<Title>Past Tickets</Title>
				<SortPastTicketsBar></SortPastTicketsBar>
				<Flex direction={'row'} gap={'sm'}>
					<PastTicketsFilter></PastTicketsFilter>
					<Flex direction={'column'} gap={'sm'}>
						<PastTicketCard
							companyName={'Pegasus Airlines'}
							departureTime={'12:05'}
							arrivalTime={'13:30'}
							departureLocation={'Ankara Esenboğa Airport'}
							arrivalLocation={'İstanbul Sabiha Gökçen'}
							departureABB={'ESB'}
							arrivalABB={'SAW'}
							duration={'1h 25min'}
							price={900}
							status="Done"
							seat="20C"
						></PastTicketCard>
					</Flex>
				</Flex>
			</Flex>
		</Center>
	);
};

export default MyTicketsPage;
