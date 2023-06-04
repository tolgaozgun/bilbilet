import SeatConfiguration from '../../components/seat/SeatConfiguration';
import { VehicleSeatConfig } from '../../types/SeatTypes';

const SeatSelectionPage = () => {
	const config: VehicleSeatConfig = {
		configId: 1,
		configName: 'Deneme',
		seatingArrangement: '3,2,3',
		configTotalRows: 30,
		configTotalColumns: 8,
		firstClassAfter: 3,
		businessClassAfter: 8,
		premiumEconomyClassAfter: 15,
	};

	return <SeatConfiguration seatConfig={config} />;
};

export default SeatSelectionPage;
