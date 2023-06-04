import {
	Card,
	Flex,
	Group,
	NumberInput,
	Select,
	SelectItem,
	Text,
	TextInput,
	Title,
} from '@mantine/core';
import { DatePickerInput, TimeInput } from '@mantine/dates';
import { UseFormReturnType, useForm } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { IconBus, IconPlane } from '@tabler/icons-react';
import { useMutation, useQuery } from '@tanstack/react-query';
import axios from 'axios';
import { forwardRef, useState } from 'react';
import { primaryAccordionColor } from '../../constants/colors';
import { useUser } from '../../hooks/auth';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import useVehicles from '../../hooks/fare/useVehicles';
import useGetStations from '../../hooks/location/useGetStations';
import useCompany from '../../hooks/users/useCompany';
import LoadingPage from '../../pages/LoadingPage';
import { createFare, getCompanyVehicles } from '../../services/fare';
import { AddFare, CompanyVehicle } from '../../types/FareTypes';
import { Station, StationType } from '../../types/LocationTypes';
import { isErrorResponse } from '../../utils/utils';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';

interface FareFormProps {}
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
const AddFareForm = () => {
	const axiosSecure = useAxiosSecure();
	const user = useUser();
	const form = useForm({
		initialValues: {
			companyName: '',
			transportationType: '',
			price: 0,
			depDate: new Date(),
			depTime: new Date(),
			depStation: 0,
			arrivalStation: 0,
			arrivalTime: new Date(),
			capacity: 0,
			duration: '',
			vehicleType: '',
			businessExtraPrice: 0,
			firstClassExtraPrice: 0,
			premiumEconExtraPrice: 0,
			vehicleId: 0,
		},
		validate: {
			companyName: (value) =>
				value.trim().length > 0 ? true : 'Company name is required',
			price: (value) => (value > 0 ? true : 'Price must be greater than 0'),
			depDate: (value) =>
				value > new Date() ? true : 'Departure date must be in the future',
			depTime: (value) =>
				value > new Date() ? true : 'Departure time must be in the future',
			depStation: (value) => (value > 0 ? true : 'Departure station is required'),
			arrivalStation: (value) => (value > 0 ? true : 'Arrival station is required'),
			arrivalTime: (value) =>
				value > new Date() ? true : 'Arrival time must be in the future',
			capacity: (value) => (value > 0 ? true : 'Capacity must be greater than 0'),
			duration: (value) =>
				value.trim().length > 0 ? true : 'Duration is required',
			vehicleType: (value) =>
				value.trim().length > 0 ? true : 'Vehicle type is required',
			businessExtraPrice: (value) =>
				value > 0 ? true : 'Business extra price must be greater than 0',
			firstClassExtraPrice: (value) =>
				value > 0 ? true : 'First class extra price must be greater than 0',
			premiumEconExtraPrice: (value) =>
				value > 0 ? true : 'Premium extra price must be greater than 0',
			vehicleId: (value) => (value > 0 ? true : 'Vehicle id is required'),
		},
	});

	const companyQuery = useCompany(axiosSecure, user?.id!);
	const {
		data: allStations,
		isLoading: isStationsLoading,
		isError: isStationsError,
	} = useGetStations(axiosSecure);
	const vehiclesQuery = useQuery({
		queryKey: ['vehicles', companyQuery.data?.data?.company.company_id],
		queryFn: () =>
			getCompanyVehicles(axiosSecure, companyQuery.data?.data?.company.company_id!),
		enabled: !!companyQuery.data?.data?.company.company_id,
	});

	if (isStationsLoading || companyQuery.isLoading || vehiclesQuery.isLoading) {
		return <LoadingPage />;
	}
	if (isStationsError || companyQuery.isError || vehiclesQuery.isError) {
		return <div>Error</div>;
	}

	const stationList: Array<Station> = allStations.data!;
	const stationData: Array<SelectItem> = stationList!.map((station) => {
		return {
			stationType: station.stationType,
			label: station.title,
			value: String(station.stationId),
			description: station.city,
		};
	});
	const vehicleList: Array<CompanyVehicle> = vehiclesQuery.data.data!;
	const vehicleData: Array<SelectItem> = vehicleList!.map((vehicle) => {
		return {
			label: String(vehicle.vehicleId),
			value: String(vehicle.vehicleId),
			description: vehicle.vehicleType,
		};
	});

	const { mutateAsync: addFare } = useMutation({
		mutationKey: ['addFare'],
		mutationFn: (addFare: AddFare) => createFare(axiosSecure, addFare),
	});
	const handleAddFare = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}

		const estimatedDepTime = new Date(form.values.depDate);
		estimatedDepTime.setHours(form.values.depTime.getHours());
		estimatedDepTime.setMinutes(form.values.depTime.getMinutes());

		const fareDetails: AddFare = {
			fareId: 0,
			depStationId: form.values.depStation,
			estimatedDepTime: estimatedDepTime,
			arrStationId: form.values.arrivalStation,
			estimatedArrTime: form.values.arrivalTime,
			firstClassExtraPrice: form.values.firstClassExtraPrice,
			businessExtraPrice: form.values.businessExtraPrice,
			premiumEconExtraPrice: form.values.premiumEconExtraPrice,
			reservationFee: 0,
			vehicleId: form.values.vehicleId,
			companyId: companyQuery?.data?.data?.company.company_id!,
			price: form.values.price,
		};
		const res = await addFare(fareDetails);
		if (isErrorResponse(res)) {
			notifications.show({
				message: res?.msg || "Something went wrong. Couldn't add the fare",
				color: 'red',
			});
		} else {
			notifications.show({
				message: 'Fare added successfully.',
				color: 'green',
			});
		}
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
						<Select
							withAsterisk
							label="Transportation type"
							data={['FLIGHT', 'BUS_TERMINAL']}
							clearable
							{...form.getInputProps('transportationType')}
						/>
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
						/>
						<Select
							label="Vehicle"
							placeholder="Pick one"
							itemComponent={CustomSelectItem}
							data={vehicleData}
							searchable
							withAsterisk
							{...form.getInputProps('depStation')}
						/>
						<Select
							label="Arrival Station"
							placeholder="Pick one"
							itemComponent={CustomSelectItem}
							data={stationData}
							searchable
							withAsterisk
							{...form.getInputProps('arrivalStation')}
							maxDropdownHeight={400}
							nothingFound="No station found"
						/>
						<TimeInput
							label="Arrival Time"
							withAsterisk
							{...form.getInputProps('arrivalTime')}
						/>
						<TextInput
							withAsterisk
							label="Price (TL)"
							{...form.getInputProps('price')}
						/>
						<NumberInput
							withAsterisk
							label="Capacity"
							variant="filled"
							min={0}
							{...form.getInputProps('capacity')}
						/>
						<NumberInput
							withAsterisk
							label="Capacity"
							variant="filled"
							min={0}
							{...form.getInputProps('firstClassExtraPrice')}
						/>
						<NumberInput
							withAsterisk
							label="Capacity"
							variant="filled"
							min={0}
							{...form.getInputProps('businessExtraPrice')}
						/>
						<NumberInput
							withAsterisk
							label="Capacity"
							variant="filled"
							min={0}
							{...form.getInputProps('premiumEconExtraPrice')}
						/>
						<NumberInput
							withAsterisk
							label="Capacity"
							variant="filled"
							min={0}
							{...form.getInputProps('capacity')}
						/>
						<TextInput
							withAsterisk
							label="Duration"
							variant="filled"
							{...form.getInputProps('duration')}
						/>
					</Flex>
				</form>
				<CustomElevatedButton text={'Add Fare'} onClick={handleAddFare} />
			</Flex>
		</Card>
	);
};

export default AddFareForm;
