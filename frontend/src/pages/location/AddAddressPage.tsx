import { Center } from '@mantine/core';
import AddAddressForm from '../../components/location/AddAddressForm';
import { useForm } from '@mantine/form';

const AddAddressPage = () => {
	const newAddressForm = useForm({
		initialValues: {
			city: '',
			country: '',
			longitude: 0,
			latitude: 0,
		},
		validate: {
			city: (value) => (value === '' ? 'This field cannot be left empty' : null),
			country: (value) => (value === '' ? 'This field cannot be left empty' : null),
		},
	});
	return (
		<Center>
			<AddAddressForm form={newAddressForm}></AddAddressForm>
		</Center>
	);
};

export default AddAddressPage;
