import { Center } from '@mantine/core';
import { useForm } from '@mantine/form';
import { CarCategoryType, FuelType, GearType } from '../../types/CarTypes';
import AddCompanyCarForm from '../../components/car-rental/AddCompanyCarForm';

const AddCompanyCarPage = () => {
	const addCompanyCarForm = useForm({
		initialValues: {
			carId: '',
			pricePerDay: 0,
			city: '',
			country: '',
		},
		validate: {
			city: (value) => (value === '' ? 'This field cannot be left empty' : null),
			country: (value) => (value === '' ? 'This field cannot be left empty' : null),
		},
	});
	return (
		<Center>
			<AddCompanyCarForm form={addCompanyCarForm}></AddCompanyCarForm>
		</Center>
	);
};

export default AddCompanyCarPage;
