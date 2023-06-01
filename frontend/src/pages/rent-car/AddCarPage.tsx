import { Center } from '@mantine/core';
import AddCarForm from '../../components/car-rental/AddCarForm';
import { useForm } from '@mantine/form';

const AddCarPage = () => {
	const addCarForm = useForm({
		initialValues: {
			capacity: 5,
			gear: 'AUTOMATIC',
			model: '',
			brand: '',
			category: '',
			fuelType: '',
			photoUrl: '',
			websiteUrl: '',
		},
		validate: {
			model: (value) => (value === '' ? 'This field cannot be left empty' : null),
			brand: (value) => (value === '' ? 'This field cannot be left empty' : null),
			category: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
			fuelType: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
			photoUrl: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
			websiteUrl: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
		},
	});
	return (
		<Center>
			<AddCarForm form={addCarForm}></AddCarForm>
		</Center>
	);
};

export default AddCarPage;
