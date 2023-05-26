import { Card, Title, Flex, TextInput } from '@mantine/core';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';
import { primaryAccordionColor } from '../../constants/colors';
import { UseFormReturnType } from '@mantine/form';
import { IconBuilding } from '@tabler/icons-react';
import RatingBar from '../common/RatingBar';
import { useState } from 'react';
import { useAddHotel } from '../../hooks/useAddHotel';
import { isErrorResponse } from '../../utils/utils';
import { notifications } from '@mantine/notifications';
import { useNavigate } from 'react-router-dom';
import { AddHotel } from '../../types/HotelTypes';

interface HotelFormProps {
	form: UseFormReturnType<
		{
			name: string;
			avgPrice: number;
			telephone: string;
			websiteUrl: string;
			coverPhotoUrl: string;
			photoUrl: string;
		},
		(values: {
			name: string;
			avgPrice: number;
			telephone: string;
			websiteUrl: string;
			coverPhotoUrl: string;
			photoUrl: string;
		}) => {
			name: string;
			avgPrice: number;
			telephone: string;
			websiteUrl: string;
			coverPhotoUrl: string;
			photoUrl: string;
		}
	>;
}
const AddHotelForm = ({ form }: HotelFormProps) => {
	const [rating, setRating] = useState(0);
	const { addHotel } = useAddHotel();
	const navigate = useNavigate();
	const handleAddHotel = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}
		// Send add hotel request

		const hotelDetails: AddHotel = {
			...form.values,
			rating: 0,
		};
		const res = await addHotel(hotelDetails);
		if (isErrorResponse(res)) {
			notifications.show({
				id: 'add-fail',
				title: 'Hotel Add failed!',
				message: res.msg,
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
			});
			return;
		}

		notifications.show({
			id: 'add-success',
			title: 'Hotel Add Successful!',
			message: 'You have successfully added a new hotel!',
			autoClose: 5000,
			withCloseButton: true,
			style: { backgroundColor: 'green' },
		});
		navigate('/search-fare');
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
					</Flex>
				</form>
				<CustomElevatedButton
					text={'Add Hotel'}
					onClick={handleAddHotel}
				></CustomElevatedButton>
			</Flex>
		</Card>
	);
};

export default AddHotelForm;
