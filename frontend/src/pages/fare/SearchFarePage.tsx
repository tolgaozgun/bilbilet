import { Card, Center, Flex, SelectItem, Tabs, Title } from '@mantine/core';
import { IconBuilding, IconBus, IconPlane } from '@tabler/icons-react';
import FareInfoCard from '../../components/fare/FareInfoCard';
import PlaneFilter from '../../components/fare/PlaneFilter';
import PlaneSearchBar from '../../components/fare/PlaneSearchbar';
import BusFilter from '../../components/fare/bus/BusFilter';
import BusSearchBar from '../../components/fare/bus/BusSearchbar';
import HotelFilter from '../../components/hotel/HotelFilter';
import HotelInfoCard from '../../components/hotel/HotelInfoCard';
import HotelTab from '../../components/hotel/HotelTab';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import useGetHotels from '../../hooks/hotel/useGetHotels';
import useGetStations from '../../hooks/location/useGetStations';
import { Hotel } from '../../types/HotelTypes';
import { Station } from '../../types/LocationTypes';

const SearchFarePage = () => {
	const axiosSecure = useAxiosSecure();
	const {
		data: allStations,
		isLoading: isStationsLoading,
		isError: isStationsError,
	} = useGetStations(axiosSecure);

	if (isStationsLoading || isStationsError || !allStations) {
		return <Flex></Flex>;
	}

	const stationList: Array<Station> = allStations.data!;
	const stationData: Array<SelectItem> = stationList!.map((station) => {
		return {
			stationType: station.stationType,
			label: station.title,
			value: station.title,
			description: station.city,
		};
	});
	return (
		<Center>
			<Tabs defaultValue="flight">
				<Tabs.List>
					<Tabs.Tab icon={<IconPlane size="1rem" />} value="flight">
						Flight
					</Tabs.Tab>
					<Tabs.Tab icon={<IconBus size="1rem" />} value="bus">
						Bus
					</Tabs.Tab>
					<Tabs.Tab icon={<IconBuilding size="1rem" />} value="hotel">
						Hotel
					</Tabs.Tab>
				</Tabs.List>
				<Tabs.Panel value="flight" pt="xs">
					<Card
						withBorder
						radius="xl"
						shadow="xl"
						p={48}
						sx={{ minWidth: 400 }}
						mx="auto"
					>
						<Flex direction={'column'} align={'start'} gap={'xl'}>
							<Title>Buy Ticket</Title>
							<PlaneSearchBar stationList={stationData}></PlaneSearchBar>
							<Flex direction={'row'} gap={'xl'}>
								<PlaneFilter></PlaneFilter>
								<Flex direction={'column'} gap={'xl'}>
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
								</Flex>
							</Flex>
						</Flex>
					</Card>
				</Tabs.Panel>
				<Tabs.Panel value="bus" pt="xs">
					<Card
						withBorder
						radius="xl"
						shadow="xl"
						p={48}
						sx={{ minWidth: 400 }}
						mx="auto"
					>
						<Flex direction={'column'} align={'start'} gap={'xl'}>
							<Title>Buy Ticket</Title>
							<BusSearchBar stationList={stationData}></BusSearchBar>
							<Flex direction={'row'} gap={'xl'}>
								<BusFilter></BusFilter>
								<Flex direction={'column'} gap={'xl'}>
									<FareInfoCard
										companyName={'Kamil Koç'}
										departureTime={'12:00'}
										arrivalTime={'18:00'}
										departureLocation={
											'Ankara Şehirlerarası Terminal'
										}
										arrivalLocation={'Esenler Otogar'}
										departureABB={'AŞTİ'}
										arrivalABB={'ESN'}
										duration={'6h 0min'}
										price={400}
									/>
								</Flex>
							</Flex>
						</Flex>
					</Card>
				</Tabs.Panel>
				<Tabs.Panel value="hotel" pt="xs">
					<HotelTab />
				</Tabs.Panel>
			</Tabs>
		</Center>
	);
};

export default SearchFarePage;
