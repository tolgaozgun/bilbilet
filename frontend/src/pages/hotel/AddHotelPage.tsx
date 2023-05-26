import { Center } from '@mantine/core';
import AddHotelForm from '../../components/hotel/AddHotelForm';
import { useForm } from '@mantine/form';

const AddHotelPage = () => {
	const hotelForm = useForm({
		initialValues: {
			name: '',
			avgPrice: 0,
			telephone: '',
			websiteUrl: '',
			coverPhotoUrl: '',
			photoUrl: '',
		},
		validate: {
			name: (value) => (value === '' ? 'This field cannot be left empty' : null),
			telephone: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
			websiteUrl: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
			coverPhotoUrl: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
			photoUrl: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
		},
	});
	return (
		<Center>
			<AddHotelForm form={hotelForm}></AddHotelForm>
		</Center>
	);
};

export default AddHotelPage;
