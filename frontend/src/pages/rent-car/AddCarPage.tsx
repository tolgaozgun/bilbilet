import { Center } from '@mantine/core';
import AddCarForm from '../../components/car-rental/AddCarForm';
import { useForm } from '@mantine/form';
import { CarCategoryType, FuelType, GearType } from '../../types/CarTypes';

const AddCarPage = () => {
	const addCarForm = useForm({
		initialValues: {
			capacity: 5,
			gear: GearType.AUTOMATIC,
			model: '',
			brand: '',
			category: CarCategoryType.HATCHBACK,
			fuelType: FuelType.DIESEL,
			photoUrl: '',
			websiteUrl: '',
		},
		validate: {
			model: (value) => (value === '' ? 'This field cannot be left empty' : null),
			brand: (value) => (value === '' ? 'This field cannot be left empty' : null),
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
