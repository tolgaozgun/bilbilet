import {
	Card,
	Title,
	Flex,
	TextInput,
	NumberInput,
	Select,
	Group,
	Text,
	SelectItem,
} from '@mantine/core';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';
import { primaryAccordionColor } from '../../constants/colors';
import { UseFormReturnType } from '@mantine/form';
import { forwardRef, useState } from 'react';
import { isErrorResponse } from '../../utils/utils';
import { notifications } from '@mantine/notifications';
import { useAddFare } from '../../hooks/fare/useAddFare';
import { AddFare } from '../../types/FareTypes';
import { DatePickerInput, TimeInput } from '@mantine/dates';
import { IconBus, IconCircuitPushbutton } from '@tabler/icons-react';
import { StationType } from '../../types/LocationTypes';
import { IconPlane } from '@tabler/icons-react';
import useGetStations from '../../hooks/location/useGetStations';
import LoadingLottie from '../common/LoadingLottie';
import LoadingPage from '../../pages/LoadingPage';
import ErrorPage from '../../pages/ErrorPage';

interface FareFormProps {
	form: UseFormReturnType<
		{
			companyName: string;
			price: number;
			depDate: Date;
			depTime: Date;
			depStation: number;
			arrivalStation: number;
			arrivalTime: Date;
			capacity: number;
			duration: string;
			vehicleType: string;
		},
		(values: {
			companyName: string;
			price: number;
			depDate: Date;
			depTime: Date;
			depStation: number;
			arrivalStation: number;
			arrivalTime: Date;
			capacity: number;
			duration: string;
			vehicleType: string;
		}) => {
			companyName: string;
			price: number;
			depDate: Date;
			depTime: Date;
			depStation: number;
			arrivalStation: number;
			arrivalTime: Date;
			capacity: number;
			duration: string;
			vehicleType: string;
		}
	>;
}
interface ItemProps extends React.ComponentPropsWithoutRef<'div'> {
	stationType: StationType;
	label: string;
	description: string;
}

const CustomSelectItem = forwardRef<HTMLDivElement, ItemProps>(
	({ stationType, label, description, ...others }: ItemProps, ref) => (
		<div ref={ref} {...others}>
			<Group noWrap>
				{stationType == 'AIRPORT' ? <IconPlane /> : <IconBus />}
				<div>
					<Text size="sm">{label}</Text>
					<Text size="xs" opacity={0.65}>
						{description}
					</Text>
				</div>
			</Group>
		</div>
	),
);
const AddFareForm = ({ form }: FareFormProps) => {
	// const { addFare } = useAddFare();
	const {
		data: stationsData,
		isLoading: isStationsLoading,
		isError: isStationsError,
	} = useGetStations();

	if (isStationsLoading) {
		return <LoadingPage />;
	}
	if (isStationsError || !stationsData) {
		return <ErrorPage />;
	}

	const stations = stationsData.data;
	const stationData: Array<SelectItem> = stations!.map((station) => {
		return {
			stationType: station.stationType,
			label: station.title,
			value: station.title,
			description: station.city,
		};
	});

	// const stationData = [
	// 	{
	// 		stationType: 'AIRPORT',
	// 		label: 'Esenboğa',
	// 		value: 'Bender Bending Rodríguez',
	// 		description: 'Ankara',
	// 	},
	// 	{
	// 		stationType: 'BUS_TERMINAL',
	// 		label: 'AŞTİ',
	// 		value: 'Carol Miller',
	// 		description: 'Ankara',
	// 	},
	// 	{
	// 		stationType: 'AIRPORT',
	// 		label: 'Sabiha Gökçen',
	// 		value: 'Homer Simpson',
	// 		description: 'İstanbul',
	// 	},
	// 	{
	// 		stationType: 'BUS_TERMINAL',
	// 		label: 'Esenler',
	// 		value: 'Spongebob Squarepants',
	// 		description: 'İstanbul',
	// 	},
	// ];

	const handleAddFare = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}
		// Send add fare request

		const fareDetails: AddFare = {
			...form.values,
		};
		// const res = await addFare(fareDetails);
		// if (isErrorResponse(res)) {
		// 	notifications.show({
		// 		id: 'add-fail',
		// 		title: 'Fare Add failed!',
		// 		message: res.msg,
		// 		autoClose: 5000,
		// 		withCloseButton: true,
		// 		style: { backgroundColor: 'red' },
		// 	});
		// 	return;
		// }

		// notifications.show({
		// 	id: 'add-success',
		// 	title: 'Fare Add Successful!',
		// 	message: 'You have successfully added a new fare!',
		// 	autoClose: 5000,
		// 	withCloseButton: true,
		// 	style: { backgroundColor: 'green' },
		// });
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
							valueFormat="DD-MM-YYYY"
							{...form.getInputProps('depDate')}
						/>
						<TimeInput
							label="Departure Time"
							withAsterisk
							{...form.getInputProps('depTime')}
						></TimeInput>
						<Select
							label="Departure Station"
							placeholder="Pick one"
							itemComponent={CustomSelectItem}
							data={stationData}
							searchable
							withAsterisk
							{...form.getInputProps('depStation')}
							maxDropdownHeight={400}
							nothingFound="No station found"
						/>
						<TimeInput
							label="Arrival Time"
							withAsterisk
							{...form.getInputProps('arrivalTime')}
						></TimeInput>
						<TextInput
							withAsterisk
							label="Arrival Location"
							{...form.getInputProps('arrivalStation')}
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
