import { Center, Flex, Title } from '@mantine/core';
import PastTicketCard from '../../../components/ticket/PastTicketCard';
import SortPastTicketsBar from '../../../components/ticket/SortPastTicketsBar';
import PastTicketsFilter from '../../../components/ticket/PastTicketsFilter';

const MyTicketsPage = () => {
	return (
		<Center>
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
