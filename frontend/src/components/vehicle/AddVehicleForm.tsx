import { Card, Flex, NumberInput, Select, TextInput, Title } from '@mantine/core';
import { UseFormReturnType } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { useMutation } from '@tanstack/react-query';
import { useState } from 'react';
import { primaryAccordionColor } from '../../constants/colors';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';
import { CompanyVehicle, AddCompanyBus, AddCompanyPlane, AddCompanyVehicle } from '../../types/VehicleTypes';
import useCompany from '../../hooks/users/useCompany';
import { useUser } from '../../hooks/auth/useUser';
import LoadingPage from '../../pages/LoadingPage';
import { addCompanyBus, addCompanyPlane } from '../../services/vehicle/VehicleService';

import { CompanyInfo } from '../../types';
import { isErrorResponse } from '../../utils/utils';

interface VehicleFormProps {
	form: UseFormReturnType<
		{
			capacity: number;
			seatConfigurationId: number;
			companyId: number;
			vehicleType: string;
			plateNumber: string;
			tailNumber: string;
		},
		(values: {
			capacity: number;
			seatConfigurationId: number;
			companyId: number;
		}) => {
			capacity: number;
			seatConfigurationId: number;
			companyId: number;
		}
	>;
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
		seatConfigurationId: form.values.seatConfigurationId,
		companyId: companyId
	}
	const companyBus: AddCompanyBus = {
		...vehicle,
		plateNumber: form.values.plateNumber,
	}
	const companyPlane: AddCompanyPlane = {
		...vehicle,
		tailNumber: form.values.tailNumber,
	}

	const { mutate: submitBusMutation, isLoading: isBusSubmitLoading } = useMutation({
		mutationKey: ['addCompanyBus'],
		mutationFn: () => (addCompanyBus(axiosSecure, companyBus)),
		onSuccess: () => {
			//queryClient.invalidateQueries(['wishlist']);
			notifications.show({
				id: 'add-success',
				title: 'Bus Add Successful!',
				message: 'You have successfully added a new bus!',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'green' },
			});
			form.reset();
		},
		onError: () =>
			notifications.show({
				id: 'add-fail',
				title: 'Vehicle Add failed!',
				message: 'Hmmmmmmm',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
			}),
	});

	const { mutate: submitPlaneMutation, isLoading: isPlaneSubmitLoading } = useMutation({
		mutationKey: ['addCompanyPlane'],
		mutationFn: () => (addCompanyPlane(axiosSecure, companyPlane)),
		onSuccess: () => {
			//queryClient.invalidateQueries(['wishlist']);
			notifications.show({
				id: 'add-success',
				title: 'Plane Add Successful!',
				message: 'You have successfully added a new plane!',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'green' },
			});
			form.reset();
		},
		onError: () =>
			notifications.show({
				id: 'add-fail',
				title: 'Plane Add failed!',
				message: 'Hmmmmmmm',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
			}),
	});

	const handleAddVehicle = async () => {
		const validation = form.validate();
		console.log("validation", validation);
		if (validation.hasErrors) {
			console.log("validation has errors");
			return;
		}
		if (form.values.vehicleType === 'Bus') {
			console.log("submitting bus mutation");
			submitBusMutation();
		} else if (form.values.vehicleType === 'Plane') {
			console.log("submitting plane mutation");
			submitPlaneMutation();
		} else { 
			notifications.show({
				message: "Please select a vehicle type.",
			});
		}
	};

	let extraInfo;
	console.log(form.values);
	if(form.values.vehicleType === 'Bus'){
		extraInfo = (
			<TextInput
				withAsterisk
				label="Plate Number"
				{...form.getInputProps('plateNumber')}
			/>
		)
	} else if (form.values.vehicleType === 'Plane'){
		extraInfo = (
			<TextInput
				withAsterisk
				label="Tail Number"
				{...form.getInputProps('tailNumber')}
			/>
		)
	}
	
	if(isCompanyLoading) {
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
						<NumberInput
							withAsterisk
							label="Capacity"
							{...form.getInputProps('capacity')}
							size="md"
							min={0}
						/>
						<NumberInput
							withAsterisk
							label="Seat Configuration"
							{...form.getInputProps('seatConfigurationId')}
							size="md"
							min={0}
						/>

						{/* <Select
							withAsterisk
							label="Seat Configuration"
							clearable
							data={[]}
							{...form.getInputProps('seatConfigurationId')}
						/> */}
						<Select
							withAsterisk
							label="Vehicle Type"
							clearable
							data={vehicleTypes}
							{...form.getInputProps('vehicleType')}
						/>
						{extraInfo}
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
