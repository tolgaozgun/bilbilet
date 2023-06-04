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
import { DatePickerInput, DateTimePicker, TimeInput } from '@mantine/dates';
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
import { AddFare, CompanyVehicle, VehicleType } from '../../types/FareTypes';
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

interface VehicleSelectItemProps extends React.ComponentPropsWithoutRef<'div'> {
	vehicleType: VehicleType;
	label: string;
	description: string;
}

const CustomVehicleSelectItem = forwardRef<HTMLDivElement, VehicleSelectItemProps>(
	({ vehicleType, label, description, ...others }: VehicleSelectItemProps, ref) => (
		<div ref={ref} {...others}>
			<Group noWrap>
				{vehicleType === 'PLANE' ? <IconPlane /> : <IconBus />}
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
	const companyQuery = useCompany(axiosSecure, user?.id!);

	const form = useForm({
		initialValues: {
			price: 0,
			depDate: new Date(),
			depStation: 0,
			arrivalStation: 0,
			arrivalTime: new Date(),
			capacity: 0,
			duration: 0,
			businessExtraPrice: 0,
			firstClassExtraPrice: 0,
			premiumEconExtraPrice: 0,
			vehicleId: 0,
		},
		validate: {
			depDate: (value, values) =>
				value < values.arrivalTime
					? null
					: 'Departure date must be before arrival date',
			arrivalTime: (value, values) => {
				return value > values.depDate
					? null
					: 'Arrival date must be after departure date';
			},
			price: (value) => (value > 0 ? null : 'Price must be greater than 0'),
			duration: (value) => (value > 0 ? null : 'Duration must be greater than 0'),
			capacity: (value) => (value > 0 ? null : 'Capacity must be greater than 0'),
			arrivalStation: (value) =>
				Number(value) > 0 ? null : 'Arrival station must be selected',
			depStation: (value) =>
				Number(value) > 0 ? null : 'Departure station must be selected',
		},
	});
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

	const { mutateAsync: addFare } = useMutation({
		mutationKey: ['addFare'],
		mutationFn: (addFare: AddFare) => createFare(axiosSecure, addFare),
	});

	if (isStationsLoading || companyQuery.isLoading || vehiclesQuery.isLoading) {
		return <LoadingPage />;
	}
	if (isStationsError || companyQuery.isError || vehiclesQuery.isError) {
		return <div>Error</div>;
	}

	const stationList: Array<Station> = allStations.data!;
	const stationData: Array<SelectItem> = stationList!
		.map((station) => {
			return {
				stationType: station.stationType,
				label: station.title,
				value: String(station.stationId),
				description: station.city,
			};
		})
		.filter((station) => {
			if (
				station.stationType === 'AIRPORT' &&
				companyQuery.data?.data?.company.type === 'AIRLINE'
			) {
				return station;
			} else if (
				station.stationType === 'BUS_TERMINAL' &&
				companyQuery.data?.data?.company.type === 'BUS'
			) {
				return station;
			}
		});
	const vehicleList: Array<CompanyVehicle> = vehiclesQuery.data.data!;
	console.log(vehicleList);
	const vehicleData: Array<SelectItem> = vehicleList!.map((vehicle) => {
		return {
			vehicleType: vehicle.vehicleType,
			label: String(vehicle.vehicleId),
			value: String(vehicle.vehicleId),
			description: vehicle.vehicleType,
		};
	});
	const vehicleDataFinal = vehicleData.filter((vehicle) => {
		if (
			vehicle.vehicleType === 'BUS' &&
			companyQuery.data?.data?.company.type === 'BUS'
		) {
			return vehicle;
		} else if (
			vehicle.vehicleType === 'PLANE' &&
			companyQuery.data?.data?.company.type === 'AIRLINE'
		) {
			return vehicle;
		}
	});

	const handleAddFare = async () => {
		console.log(form.values);
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}

		const fareDetails: AddFare = {
			fareId: 0,
			depStationId: form.values.depStation,
			estimatedDepTime: form.values.depDate,
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
							disabled
							value={companyQuery.data?.data?.company.company_title}
						/>
						<TextInput
							withAsterisk
							label="Transportation type"
							disabled
							value={companyQuery.data?.data?.company.type}
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
						<DateTimePicker
							label="Departure Date"
							placeholder="Departure Date"
							withAsterisk
							valueFormat="DD-MM-YYYY"
							{...form.getInputProps('depDate')}
						/>
						<Select
							label="Vehicle"
							placeholder="Pick one"
							itemComponent={CustomVehicleSelectItem}
							data={vehicleDataFinal}
							searchable
							withAsterisk
							{...form.getInputProps('vehicleId')}
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
						<DateTimePicker
							label="Arrival Date"
							placeholder="Arrival Date"
							withAsterisk
							valueFormat="DD-MM-YYYY"
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
							label="First Class Extra Price"
							variant="filled"
							min={0}
							{...form.getInputProps('firstClassExtraPrice')}
						/>
						<NumberInput
							withAsterisk
							label="Business Extra Price"
							variant="filled"
							min={0}
							{...form.getInputProps('businessExtraPrice')}
						/>
						<NumberInput
							withAsterisk
							label="Premium Economy Extra Price"
							variant="filled"
							min={0}
							{...form.getInputProps('premiumEconExtraPrice')}
						/>
						<NumberInput
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
