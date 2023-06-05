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
import { convertDateToTime, formatDate, getTimeDifference } from '../../../utils/utils';

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
	
	// const ticketCards = ticketsList.map((ticketData) => {
	// 	return (
	// 		<TicketInformation
	// 			ticket = { ticketData }
	// 		></TicketInformation>
	// 	);
	// });

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
					{ticketsList?.map((ticketData) => {
						const depTimeDateObj = new Date(ticketData.departureTime);
						const arrTimeDateObj = new Date(ticketData.arrivalTime);

						const depTimeD = convertDateToTime(depTimeDateObj);
						const arrTimeD = convertDateToTime(arrTimeDateObj);
						const depDateD = formatDate(depTimeDateObj);
						const arrDateD = formatDate(arrTimeDateObj);
						const durationD = getTimeDifference(
							depTimeDateObj,
							arrTimeDateObj,
						);
						
						return (
							<TicketInformation
					   			ticket = { ticketData }
								depTime = { depTimeD }
								arrTime = { arrTimeD }
								depDate = { depDateD }
								arrDate = { arrDateD }
								duration = { durationD }
					   		></TicketInformation>
						);
					})}
				</Tabs.Panel>

				<Tabs.Panel value="past" pt="xs">
				{ticketsList?.map((ticketData) => {
						const depTimeDateObj = new Date(ticketData.departureTime);
						const arrTimeDateObj = new Date(ticketData.arrivalTime);

						const depTimeD = convertDateToTime(depTimeDateObj);
						const arrTimeD = convertDateToTime(arrTimeDateObj);
						const depDateD = formatDate(depTimeDateObj);
						const arrDateD = formatDate(arrTimeDateObj);
						const durationD = getTimeDifference(
							depTimeDateObj,
							arrTimeDateObj,
						);
						
						return (
							<TicketInformation
					   			ticket = { ticketData }
								   depTime = { depTimeD }
								   arrTime = { arrTimeD }
								   depDate = { depDateD }
								   arrDate = { arrDateD }
								   duration = { durationD }
					   		></TicketInformation>
						);
					})}
				</Tabs.Panel>

				<Tabs.Panel value="cancelled" pt="xs">
				{ticketsList?.map((ticketData) => {
						const depTimeDateObj = new Date(ticketData.departureTime);
						const arrTimeDateObj = new Date(ticketData.arrivalTime);

						const depTimeD = convertDateToTime(depTimeDateObj);
						const arrTimeD = convertDateToTime(arrTimeDateObj);
						const depDateD = formatDate(depTimeDateObj);
						const arrDateD = formatDate(arrTimeDateObj);
						const durationD = getTimeDifference(
							depTimeDateObj,
							arrTimeDateObj,
						);
						
						return (
							<TicketInformation
					   			ticket = { ticketData }
								   depTime = { depTimeD }
								   arrTime = { arrTimeD }
								   depDate = { depDateD }
								   arrDate = { arrDateD }
								   duration = { durationD }
					   		></TicketInformation>
						);
					})}
				</Tabs.Panel>
			</Tabs>
		</Center>
			
		// </Center><Flex direction={'column'} gap={'sm'}>
		// </Center>	<Title>Past Tickets</Title>
		// </Center>	<SortPastTicketsBar></SortPastTicketsBar>
		// </Center>	<Flex direction={'row'} gap={'sm'}>
		// </Center>		<PastTicketsFilter></PastTicketsFilter>
		// </Center>		<Flex direction={'column'} gap={'sm'}>
		// </Center>			<PastTicketCard
		// </Center>				ticket = { }
		// </Center>			></PastTicketCard>
		// </Center>		</Flex>
		// </Center>	</Flex>
		// </Center></Flex>
	);
};

export default MyTicketsPage;
