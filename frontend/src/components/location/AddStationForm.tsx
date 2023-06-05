import { Card, Flex, Select, TextInput, Title } from '@mantine/core';
import { UseFormReturnType } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { useMutation } from '@tanstack/react-query';
import { primaryAccordionColor } from '../../constants/colors';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import { addStation } from '../../services/location';
import { AddStation, StationType } from '../../types/LocationTypes';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';

interface StationFormProps {
	form: UseFormReturnType<
		{
			title: string;
			abbreviation: string;
			stationType: string;
			city: string;
			country: string;
		},
		(values: {
			title: string;
			abbreviation: string;
			stationType: string;
			city: string;
			country: string;
		}) => {
			title: string;
			abbreviation: string;
			stationType: string;
			city: string;
			country: string;
		}
	>;
}
const AddStationForm = ({ form }: StationFormProps) => {
	const axiosSecure = useAxiosSecure();
	//TODO: from enum
	const stationTypes = ['AIRPORT', 'BUS_TERMINAL'];

	const stationDetails: AddStation = {
		...form.values,
		stationType: form.values.stationType as StationType,
	};
	// const { addStation } = useAddStation();
	const { mutate: submitMutation, isLoading: isSubmitLoading } = useMutation({
		mutationKey: ['addStation'],
		mutationFn: () => addStation(axiosSecure, stationDetails),
		onSuccess: () => {
			//queryClient.invalidateQueries(['wishlist']);
			notifications.show({
				id: 'add-success',
				title: 'Station Add Successful!',
				message: 'You have successfully added a new station!',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'green' },
				styles: (theme) => ({
					title: { color: theme.white },
					description: { color: theme.white }
				})
			});
			form.reset();
		},
		onError: () =>
			notifications.show({
				id: 'add-fail',
				title: 'Station Add failed!',
				message: 'Hmmmmmmmm',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
				styles: (theme) => ({
					title: { color: theme.white },
					description: { color: theme.white }
				})
			}),
	});
	const handleAddStation = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}

		// Send add station request
		submitMutation();
	};
	return (
		<Card padding={36} bg={primaryAccordionColor} withBorder radius="xl" shadow="xl">
			<Title>Add A New Station</Title>
			<Flex direction={'column'} gap={'xs'}>
				<form>
					<Flex direction={'column'} gap={'xs'}>
						<TextInput
							withAsterisk
							label="Title"
							{...form.getInputProps('title')}
						/>
						<TextInput
							withAsterisk
							label="Abbreviation"
							{...form.getInputProps('abbreviation')}
						/>
						<Select
							withAsterisk
							data={stationTypes}
							label="Station Type"
							{...form.getInputProps('stationType')}
						/>
						<TextInput
							withAsterisk
							label="City"
							{...form.getInputProps('city')}
						/>
						<TextInput
							withAsterisk
							label="Country"
							{...form.getInputProps('country')}
						/>
					</Flex>
				</form>
				<CustomElevatedButton
					text={'Add Station'}
					onClick={handleAddStation}
					isLoading={isSubmitLoading}
				></CustomElevatedButton>
			</Flex>
		</Card>
	);
};

export default AddStationForm;
