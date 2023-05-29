import { Center } from '@mantine/core';
import { useForm } from '@mantine/form';
import AddFareForm from '../../components/fare/AddFareForm';

const AddFarePage = () => {
	const fareForm = useForm({
		initialValues: {
			companyName: '',
			price: 0,
			depDate: '',
			depTime: '',
			depLocation: '',
			depAbb: '',
			arrivalLocation: '',
			arrivalAbb: '',
			arrivalTime: '',
			capacity: 0,
			duration: '',
			vehicleType: '',
		},
		validate: {},
	});
	return (
		<Center>
			<AddFareForm form={fareForm}></AddFareForm>
		</Center>
	);
};

export default AddFarePage;
