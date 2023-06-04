import { useQueries } from '@tanstack/react-query';
import { useParams } from 'react-router-dom';
import ItemsNotFoundPage from '../../components/common/feedback/ItemsNotFoundPage';
import SeatConfiguration from '../../components/seat/SeatConfiguration';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import { getFareView, getVehicleSeatConfig } from '../../services/fare/FareService';
import { VehicleSeatConfig } from '../../types/SeatTypes';
import LoadingPage from '../LoadingPage';

const SeatSelectionPage = () => {
	const { fareId } = useParams();
	const axiosSecure = useAxiosSecure();

	const result = useQueries({
		queries: [
			{
				queryKey: ['getFareView', 1],
				queryFn: () => getFareView(axiosSecure, Number(fareId)),
			},
			{
				queryKey: ['getVehicleSeatConfig', 2],
				queryFn: () => getVehicleSeatConfig(axiosSecure, Number(fareId)),
			},
		],
	});

	console.log(fareId);
	console.log(result);
	if (result[0].isLoading || result[1].isLoading) {
		return <LoadingPage />;
	}

	if (result[0].isError || result[1].isError) {
		return <ItemsNotFoundPage />;
	}

	const seatTickets = result[0].data;
	const seatConfig = result[1].data;

	return (
		<SeatConfiguration
			seatConfig={seatConfig.data!}
			seatTickets={seatTickets.data!}
		/>
	);
};

export default SeatSelectionPage;
