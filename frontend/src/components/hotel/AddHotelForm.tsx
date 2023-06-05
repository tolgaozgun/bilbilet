import { Card, Flex, TextInput, Title } from '@mantine/core';
import { UseFormReturnType } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { IconBuilding } from '@tabler/icons-react';
import { useMutation } from '@tanstack/react-query';
import { useState } from 'react';
import { AddHotel, Hotel } from '../../types/HotelTypes';
import { primaryAccordionColor } from '../../constants/colors';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import { addHotel } from '../../services/hotel';
import RatingBar from '../common/RatingBar';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';

interface HotelFormProps {
	form: UseFormReturnType<
		{
			name: string;
			avgPrice: number;
			telephone: string;
			websiteUrl: string;
			coverPhotoUrl: string;
			photoUrl: string;
			country: string;
			city: string;
		},
		(values: {
			name: string;
			avgPrice: number;
			telephone: string;
			websiteUrl: string;
			coverPhotoUrl: string;
			photoUrl: string;
			country: string;
			city: string;
		}) => {
			name: string;
			avgPrice: number;
			telephone: string;
			websiteUrl: string;
			coverPhotoUrl: string;
			photoUrl: string;
			country: string;
			city: string;
		}
	>;
}
const AddHotelForm = ({ form }: HotelFormProps) => {
	const [rating, setRating] = useState(0);
	const axiosSecure = useAxiosSecure();
	const hotel: Hotel = {
		...form.values,
		addressId: 0,
		hotelId: 0,
		rating: rating,
	};
	const hotelDetails: AddHotel = {
		hotel: hotel,
		city: form.values.city,
		country: form.values.country,
	};
	const { mutate: submitMutation, isLoading: isSubmitLoading } = useMutation({
		mutationKey: ['addHotel'],
		mutationFn: () => addHotel(axiosSecure, hotelDetails),
		onSuccess: () => {
			//queryClient.invalidateQueries(['wishlist']);
			notifications.show({
				id: 'add-success',
				title: 'Hotel Add Successful!',
				message: 'You have successfully added a new hotel!',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'green' },
				styles: (theme) => ({
					title: { color: theme.white },
					description: { color: theme.white }
				})
			});
			form.reset();
			setRating(0);
		},
		onError: (error) =>
			notifications.show({
				id: 'add-fail',
				title: 'Hotel Add failed!',
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

	const handleAddHotel = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}
		// Send add hotel request
		submitMutation();
	};
	return (
		<Card padding={36} bg={primaryAccordionColor} withBorder radius="xl" shadow="xl">
			<Title>Add A New Hotel</Title>
			<Flex direction={'column'} gap={'xs'}>
				<form>
					<Flex direction={'column'} gap={'xs'}>
						<TextInput
							withAsterisk
							label="Name"
							{...form.getInputProps('name')}
						/>
						<TextInput
							withAsterisk
							label="Average Price (TL)"
							{...form.getInputProps('avgPrice')}
						/>
						<TextInput
							withAsterisk
							label="Telephone"
							{...form.getInputProps('telephone')}
						/>
						<RatingBar
							label="Rating: "
							editable={true}
							value={rating}
							emptySymbol={<IconBuilding color="gray" />}
							fullSymbol={<IconBuilding color="blue" />}
							setValue={setRating}
						></RatingBar>
						<TextInput
							withAsterisk
							label="Website URL"
							{...form.getInputProps('websiteUrl')}
						></TextInput>
						<TextInput
							withAsterisk
							label="Cover Photo URL"
							{...form.getInputProps('coverPhotoUrl')}
						></TextInput>
						<TextInput
							withAsterisk
							label="Photo URL"
							{...form.getInputProps('photoUrl')}
						></TextInput>
						<TextInput
							withAsterisk
							label="Country"
							{...form.getInputProps('country')}
						></TextInput>
						<TextInput
							withAsterisk
							label="City"
							{...form.getInputProps('city')}
						></TextInput>
					</Flex>
				</form>
				<CustomElevatedButton
					text={'Add Hotel'}
					onClick={handleAddHotel}
					isLoading={isSubmitLoading}
				></CustomElevatedButton>
			</Flex>
		</Card>
	);
};

export default AddHotelForm;
