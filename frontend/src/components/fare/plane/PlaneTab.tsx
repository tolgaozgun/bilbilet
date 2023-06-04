import { Card, Flex, SelectItem, Title } from '@mantine/core';
import { notifications } from '@mantine/notifications';
import { useState } from 'react';
import useAxiosSecure from '../../../hooks/auth/useAxiosSecure';
import useFlightFares from '../../../hooks/fare/useFlightFares';
import LoadingPage from '../../../pages/LoadingPage';
import { FlightFilterParams } from '../../../types/FlightFareTypes';
import { isErrorResponse } from '../../../utils/utils';
import ItemsNotFoundPage from '../../common/feedback/ItemsNotFoundPage';
import FareInfoCard from '../FareInfoCard';
import PlaneFilter from './PlaneFilter';
import PlaneSearchBar from './PlaneSearchbar';

interface PlaneTabProps {
	stationData: Array<SelectItem>;
}

const PlaneTab = ({ stationData }: PlaneTabProps) => {
	const axiosSecure = useAxiosSecure();
	const [filterParams, setFilterParams] = useState<FlightFilterParams | {}>({});
	const {
		isLoading: isFareLoading,
		isError: isFareFetchError,
		data: flightResponse,
	} = useFlightFares(axiosSecure, filterParams);

	if (isFareLoading) {
		return <LoadingPage />;
	}
	if (isFareFetchError) {
		if (!flightResponse) {
			notifications.show({
				message: 'Something went wrong',
			});
		} else if (isErrorResponse(flightResponse)) {
			notifications.show({
				message: flightResponse.msg,
			});
		}
		return <ItemsNotFoundPage />;
	}

	return (
		<Card withBorder radius="xl" shadow="xl" p={48} sx={{ minWidth: 400 }} mx="auto">
			<Flex direction={'column'} align={'start'} gap={'xl'}>
				<Title>Buy Ticket</Title>
				<PlaneSearchBar stationList={stationData} />
				<Flex direction={'row'} gap={'xl'}>
					<PlaneFilter />
					<Flex direction={'column'} gap={'xl'}>
						{flightResponse.data?.map((flight) => {
							return (
								<FareInfoCard
									companyName={'Pegasus Airlines'}
									departureTime={'12:05'}
									arrivalTime={'13:30'}
									departureLocation={'Ankara Esenboğa Airport'}
									arrivalLocation={'İstanbul Sabiha Gökçen'}
									departureABB={'ESB'}
									arrivalABB={'SAW'}
									duration={'1h 25min'}
									price={900}
								/>
							);
						})}
					</Flex>
				</Flex>
			</Flex>
		</Card>
	);
};

export default PlaneTab;
