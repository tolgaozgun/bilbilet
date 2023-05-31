import { Card, Title, Flex, TextInput, Select } from '@mantine/core';
import { UseFormReturnType } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { useState } from 'react';
import { primaryAccordionColor } from '../../constants/colors';
import { isErrorResponse } from '../../utils/utils';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';
import { AddStation, StationType } from '../../types/LocationTypes';

interface StationFormProps {
	form: UseFormReturnType<
		{
			title: string;
			abbreviation: string;
			stationType: StationType;
			city: string;
			country: string;
		},
		(values: {
			title: string;
			abbreviation: string;
			stationType: StationType;
			city: string;
			country: string;
		}) => {
			title: string;
			abbreviation: string;
			stationType: StationType;
			city: string;
			country: string;
		}
	>;
}
const AddStationForm = ({ form }: StationFormProps) => {
	//TODO: from enum
	const stationTypes = ['Airport', 'Bus Terminal', 'Port', 'Etc.'];

	//TODO: from backend get all cities available
	const cities = ['Ankara', 'İstanbul'];
	//TODO: from backend get all cities available
	const countries = ['Turkey', 'Italy'];

	// const { addStation } = useAddStation();

	const handleAddStation = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}
		// Send add station request

		const stationDetails: AddStation = {
			...form.values,
		};
		// const res = await addStation(stationDetails);
		// if (isErrorResponse(res)) {
		// 	notifications.show({
		// 		id: 'add-fail',
		// 		title: 'Station Add failed!',
		// 		message: res.msg,
		// 		autoClose: 5000,
		// 		withCloseButton: true,
		// 		style: { backgroundColor: 'red' },
		// 	});
		// 	return;
		// }

		// notifications.show({
		// 	id: 'add-success',
		// 	title: 'Station Add Successful!',
		// 	message: 'You have successfully added a new station!',
		// 	autoClose: 5000,
		// 	withCloseButton: true,
		// 	style: { backgroundColor: 'green' },
		// });
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
						<Select
							withAsterisk
							data={cities}
							label="City"
							{...form.getInputProps('city')}
						/>
						<Select
							withAsterisk
							data={countries}
							label="Country"
							{...form.getInputProps('country')}
						/>
					</Flex>
				</form>
				<CustomElevatedButton
					text={'Add Station'}
					onClick={handleAddStation}
				></CustomElevatedButton>
			</Flex>
		</Card>
	);
};

export default AddStationForm;
