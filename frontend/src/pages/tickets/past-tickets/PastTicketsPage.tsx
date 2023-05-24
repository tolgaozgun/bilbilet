import { Center, Flex, Title } from '@mantine/core';
import PastTicketCard from '../../../components/ticket/PastTicketCard';

const PastTicketsPage = () => {
	return (
		<Center>
			<Flex direction={'column'} gap={'sm'}>
				<Title>Past Tickets</Title>
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
		</Center>
	);
};

export default PastTicketsPage;
