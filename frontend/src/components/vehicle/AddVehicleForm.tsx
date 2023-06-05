import {
	Card,
	Divider,
	Flex,
	NumberInput,
	Select,
	TextInput,
	Title,
} from '@mantine/core';
import { UseFormReturnType } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { useMutation } from '@tanstack/react-query';
import { useState } from 'react';
import { primaryAccordionColor } from '../../constants/colors';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import { useUser } from '../../hooks/auth/useUser';
import useCompany from '../../hooks/users/useCompany';
import LoadingPage from '../../pages/LoadingPage';
import { addCompanyBus, addCompanyPlane } from '../../services/vehicle/VehicleService';
import {
	AddCompanyBus,
	AddCompanyPlane,
	AddCompanyVehicle,
	CompanyVehicle,
} from '../../types/VehicleTypes';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';

import { CompanyInfo } from '../../types';
import { isErrorResponse } from '../../utils/utils';

interface VehicleFormProps {
	form: UseFormReturnType<{
		capacity: number;
		vehicleType: string;
		plateNumber: string;
		tailNumber: string;
		configName: string;
		seatingArrangement: string;
		configTotalRows: number;
		configTotalColumns: number;
		firstClassAfter: number;
		businessClassAfter: number;
		premiumEconomyClassAfter: number;
	}>;
}

const vehicleTypes = ['Bus', 'Plane'];

const AddVehicleForm = ({ form }: VehicleFormProps) => {
	const user = useUser();
	const axiosSecure = useAxiosSecure();
	const {
		isLoading: isCompanyLoading,
		isError: isCompanyError,
		data: companyResponse,
	} = useCompany(axiosSecure, user?.id!);
	const companyId = companyResponse?.data?.company.company_id!;

	const vehicle: AddCompanyVehicle = {
		capacity: form.values.capacity,
		companyId: companyId,
		seatConfig: {
			configName: form.values.configName,
			seatingArrangement: form.values.seatingArrangement,
			configTotalRows: form.values.configTotalRows,
			configTotalColumns: form.values.configTotalColumns,
			firstClassAfter: form.values.firstClassAfter,
			businessClassAfter: form.values.businessClassAfter,
			premiumEconomyClassAfter: form.values.premiumEconomyClassAfter,
		},
	};
	const companyBus: AddCompanyBus = {
		...vehicle,
		plateNumber: form.values.plateNumber,
	};
	const companyPlane: AddCompanyPlane = {
		...vehicle,
		tailNumber: form.values.tailNumber,
	};

	const { mutate: submitBusMutation, isLoading: isBusSubmitLoading } = useMutation({
		mutationKey: ['addCompanyBus'],
		mutationFn: () => addCompanyBus(axiosSecure, companyBus),
		onSuccess: () => {
			notifications.show({
				id: 'add-success',
				title: 'Bus Add Successful!',
				message: 'You have successfully added a new bus!',
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
		onError: (error) =>
			notifications.show({
				id: 'add-fail',
				title: 'Vehicle Add failed!',
				message: error.response ? error.response.data.msg : 'Something went wrong',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
				styles: (theme) => ({
					title: { color: theme.white },
					description: { color: theme.white }
				})
			}),
	});

	const { mutate: submitPlaneMutation, isLoading: isPlaneSubmitLoading } = useMutation({
		mutationKey: ['addCompanyPlane'],
		mutationFn: () => addCompanyPlane(axiosSecure, companyPlane),
		onSuccess: () => {
			notifications.show({
				id: 'add-success',
				title: 'Plane Add Successful!',
				message: 'You have successfully added a new plane!',
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
		onError: (error) =>
			notifications.show({
				id: 'add-fail',
				title: 'Plane Add failed!',
				message: error.response ? error.response.data.msg : 'Something went wrong',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
				styles: (theme) => ({
					title: { color: theme.white },
					description: { color: theme.white }
				})
			}),
	});

	const handleAddVehicle = async () => {
		const validation = form.validate();
		console.log('validation', validation);
		if (validation.hasErrors) {
			console.log('validation has errors');
			return;
		}
		if (form.values.vehicleType === 'Bus') {
			console.log('submitting bus mutation');
			submitBusMutation();
		} else if (form.values.vehicleType === 'Plane') {
			console.log('submitting plane mutation');
			submitPlaneMutation();
		} else {
			notifications.show({
				message: 'Please select a vehicle type.',
			});
		}
	};

	let extraInfo;
	console.log(form.values);
	if (form.values.vehicleType === 'Bus') {
		extraInfo = (
			<TextInput
				withAsterisk
				label="Plate Number"
				{...form.getInputProps('plateNumber')}
			/>
		);
	} else if (form.values.vehicleType === 'Plane') {
		extraInfo = (
			<TextInput
				withAsterisk
				label="Tail Number"
				{...form.getInputProps('tailNumber')}
			/>
		);
	}

	if (isCompanyLoading) {
		return <LoadingPage />;
	}

	if (isCompanyError) {
		if (companyResponse?.msg) {
			notifications.show({
				message: companyResponse.msg,
			});
		}
		return <div></div>; // TODO: error page
	}

	return (
		<Card padding={36} bg={primaryAccordionColor} withBorder radius="xl" shadow="xl">
			<Title>Add A New Vehicle</Title>
			<Flex direction={'column'} gap={'xs'}>
				<form>
					<Flex direction={'column'} gap={'xs'}>
						<Divider />
						<Title size={20}>Vehicle Configuration</Title>
						<NumberInput
							withAsterisk
							label="Capacity"
							{...form.getInputProps('capacity')}
							min={0}
						/>
						<Select
							withAsterisk
							label="Vehicle Type"
							clearable
							data={vehicleTypes}
							{...form.getInputProps('vehicleType')}
						/>
						{extraInfo}

						<Divider />
						<Title size={20}>Seating Configuration</Title>

						<TextInput
							withAsterisk
							label="Config Name"
							{...form.getInputProps('configName')}
						/>

						<TextInput
							withAsterisk
							label="Seating Arrangement"
							description="Please use the following format: 2,3,2"
							{...form.getInputProps('seatingArrangement')}
						/>

						<NumberInput
							withAsterisk
							label="Total Rows"
							{...form.getInputProps('configTotalRows')}
							min={0}
						/>

						<NumberInput
							withAsterisk
							label="Total Columns"
							{...form.getInputProps('configTotalColumns')}
							min={0}
						/>

						<NumberInput
							withAsterisk
							label="First Class After"
							{...form.getInputProps('firstClassAfter')}
							min={0}
							description="Please enter 0 if there is no first class"
						/>

						<NumberInput
							withAsterisk
							label="Business Class After"
							{...form.getInputProps('businessClassAfter')}
							min={0}
							description="Please enter 0 if there is no business class"
						/>

						<NumberInput
							withAsterisk
							label="Premium Economy Class After"
							{...form.getInputProps('premiumEconomyClassAfter')}
							min={0}
							description="Please enter 0 if there is no premium economy class"
						/>
					</Flex>
				</form>
				<CustomElevatedButton
					text={'Add Vehicle'}
					onClick={handleAddVehicle}
					isLoading={isBusSubmitLoading || isPlaneSubmitLoading}
				></CustomElevatedButton>
			</Flex>
		</Card>
	);
};

export default AddVehicleForm;
