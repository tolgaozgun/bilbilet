import { Center, SelectItem, Tabs } from '@mantine/core';
import { IconBuilding, IconBus, IconPlane } from '@tabler/icons-react';
import BusTab from '../../components/fare/bus/BusTab';
import PlaneTab from '../../components/fare/plane/PlaneTab';
import HotelTab from '../../components/hotel/HotelTab';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import useGetStations from '../../hooks/location/useGetStations';
import { Station } from '../../types/LocationTypes';
import LoadingPage from '../LoadingPage';

const SearchFarePage = () => {
	const axiosSecure = useAxiosSecure();
	const {
		data: allStations,
		isLoading: isStationsLoading,
		isError: isStationsError,
	} = useGetStations(axiosSecure);

	if (isStationsLoading || isStationsError || !allStations) {
		return <LoadingPage />;
	}

	const stationList: Array<Station> = allStations.data!;
	const stationData: Array<SelectItem> = stationList!.map((station) => {
		const id = station.stationId;
		return {
			stationType: station.stationType,
			label: station.title,
			value: id.toString(),
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
					<PlaneTab stationData={stationData} />
				</Tabs.Panel>
				<Tabs.Panel value="bus" pt="xs">
					<BusTab stationData={stationData} />
				</Tabs.Panel>
				<Tabs.Panel value="hotel" pt="xs">
					<HotelTab />
				</Tabs.Panel>
			</Tabs>
		</Center>
	);
};

export default SearchFarePage;
