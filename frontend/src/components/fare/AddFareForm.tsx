import { Card, Title, Flex, TextInput, NumberInput, Select } from '@mantine/core';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';
import { primaryAccordionColor } from '../../constants/colors';
import { UseFormReturnType } from '@mantine/form';
import { useState } from 'react';
import { isErrorResponse } from '../../utils/utils';
import { notifications } from '@mantine/notifications';
import { useAddFare } from '../../hooks/fare/useAddFare';
import { AddFare } from '../../types/FareTypes';
import { DatePickerInput, TimeInput } from '@mantine/dates';

interface FareFormProps {
	form: UseFormReturnType<
		{
			companyName: string;
			price: number;
			depDate: string;
			depTime: string;
			depLocation: string;
			depAbb: string;
			arrivalLocation: string;
			arrivalAbb: string;
			arrivalTime: string;
			capacity: number;
			duration: string;
			vehicleType: string;
		},
		(values: {
			companyName: string;
			price: number;
			depDate: string;
			depTime: string;
			depLocation: string;
			depAbb: string;
			arrivalLocation: string;
			arrivalAbb: string;
			arrivalTime: string;
			capacity: number;
			duration: string;
			vehicleType: string;
		}) => {
			companyName: string;
			price: number;
			depDate: string;
			depTime: string;
			depLocation: string;
			depAbb: string;
			arrivalLocation: string;
			arrivalAbb: string;
			arrivalTime: string;
			capacity: number;
			duration: string;
			vehicleType: string;
		}
	>;
}
const AddFareForm = ({ form }: FareFormProps) => {
	const { addFare } = useAddFare();

	const handleAddFare = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}
		// Send add hotel request

		const fareDetails: AddFare = {
			...form.values,
		};
		const res = await addFare(fareDetails);
		if (isErrorResponse(res)) {
			notifications.show({
				id: 'add-fail',
				title: 'Fare Add failed!',
				message: res.msg,
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
			});
			return;
		}

		notifications.show({
			id: 'add-success',
			title: 'Fare Add Successful!',
			message: 'You have successfully added a new fare!',
			autoClose: 5000,
			withCloseButton: true,
			style: { backgroundColor: 'green' },
		});
	};
	return (
		<Card padding={36} bg={primaryAccordionColor} withBorder radius="xl" shadow="xl">
			<Title>Add A New Fare</Title>
			<Flex direction={'column'} gap={'xs'}>
				<form>
					<Flex direction={'column'} gap={'xs'}>
						<TextInput
							withAsterisk
							label="Company Name"
							{...form.getInputProps('companyName')}
						/>
						<TextInput
							withAsterisk
							label="Price (TL)"
							{...form.getInputProps('price')}
						/>
						<DatePickerInput
							type="default"
							label="Departure Date"
							placeholder="Departure Date"
							withAsterisk
						/>
						<TimeInput label="Departure Time" withAsterisk></TimeInput>
						<TextInput
							withAsterisk
							label="Departure Location"
							{...form.getInputProps('depLocation')}
						></TextInput>
						<TextInput
							withAsterisk
							label="Departure Abbreviation"
							{...form.getInputProps('depAbb')}
						></TextInput>

						<DatePickerInput
							type="default"
							label="Arrival Date"
							placeholder="Arrival Date"
							withAsterisk
						/>
						<TimeInput label="Arrival Time" withAsterisk></TimeInput>
						<TextInput
							withAsterisk
							label="Arrival Location"
							{...form.getInputProps('arrivalLocation')}
						></TextInput>
						<TextInput
							withAsterisk
							label="Arrival Abbreviation"
							{...form.getInputProps('arrivalAbb')}
						></TextInput>
						<NumberInput
							withAsterisk
							label="Capacity"
							variant="filled"
							min={0}
							{...form.getInputProps('capacity')}
						></NumberInput>
						<TextInput
							withAsterisk
							label="Duration"
							variant="filled"
							{...form.getInputProps('duration')}
						></TextInput>
						<Select
							withAsterisk
							label="Transportation type"
							data={['Flight', 'Bus']}
							clearable
							{...form.getInputProps('vehicleType')}
						></Select>
					</Flex>
				</form>
				<CustomElevatedButton
					text={'Add Fare'}
					onClick={handleAddFare}
				></CustomElevatedButton>
			</Flex>
		</Card>
	);
};

export default AddFareForm;
