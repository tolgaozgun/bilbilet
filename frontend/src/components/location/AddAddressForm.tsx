import { Card, Title, Flex, TextInput, Select, NumberInput } from '@mantine/core';
import { UseFormReturnType } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { useState } from 'react';
import { primaryAccordionColor } from '../../constants/colors';
import { isErrorResponse } from '../../utils/utils';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';
import { AddAddress } from '../../types/LocationTypes';
import { useMutation } from '@tanstack/react-query';
import { addAddress } from '../../services/location';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';

interface AddressFormProps {
	form: UseFormReturnType<
		{
			city: string;
			country: string;
			longitude: number;
			latitude: number;
		},
		(values: {
			city: string;
			country: string;
			longitude: number;
			latitude: number;
		}) => {
			city: string;
			country: string;
			longitude: number;
			latitude: number;
		}
	>;
}
const AddAddressForm = ({ form }: AddressFormProps) => {
	const axiosSecure = useAxiosSecure();
	const addressDetails: AddAddress = {
		...form.values,
	};
	const { mutate: submitMutation, isLoading: isSubmitLoading } = useMutation({
		mutationKey: ['submitAddress'],
		mutationFn: () => addAddress(axiosSecure, addressDetails),
		onSuccess: () => {
			//queryClient.invalidateQueries(['address']);
			notifications.show({
				id: 'add-success',
				title: 'Address Add Successful!',
				message: 'You have successfully added a new address!',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'green' },
			});
			form.reset();
		},
		onError: () =>
			notifications.show({
				id: 'add-fail',
				title: 'Address Add failed!',
				message: 'Hmmmmmm...',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
			}),
	});
	const handleAddAddress = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}
		// Send add address request
		submitMutation();
	};
	return (
		<Card padding={36} bg={primaryAccordionColor} withBorder radius="xl" shadow="xl">
			<Title>Add A New Address</Title>
			<Flex direction={'column'} gap={'xs'}>
				<form>
					<Flex direction={'column'} gap={'xs'}>
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
						<NumberInput
							withAsterisk
							label="Longitude"
							min={0}
							{...form.getInputProps('longitude')}
						/>
						<NumberInput
							withAsterisk
							label="Latitude"
							type="number"
							min={0}
							{...form.getInputProps('latitude')}
						/>
					</Flex>
				</form>
				<CustomElevatedButton
					text={'Add Address'}
					onClick={handleAddAddress}
					isLoading={isSubmitLoading}
				></CustomElevatedButton>
			</Flex>
		</Card>
	);
};

export default AddAddressForm;
