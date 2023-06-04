import { Center, SelectItem } from '@mantine/core';
import { useForm } from '@mantine/form';
import AddFareForm from '../../components/fare/AddFareForm';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import useGetStations from '../../hooks/location/useGetStations';
import { Station } from '../../types/LocationTypes';
import LoadingPage from '../LoadingPage';

const AddFarePage = () => {
	return (
		<Center>
			<AddFareForm></AddFareForm>
		</Center>
	);
};

export default AddFarePage;
