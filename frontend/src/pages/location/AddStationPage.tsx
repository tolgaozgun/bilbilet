import { Center } from '@mantine/core';
import { useForm } from '@mantine/form';
import AddStationForm from '../../components/location/AddStationForm';

const AddStationPage = () => {
	const newStationForm = useForm({
		initialValues: {
			title: '',
			abbreviation: '',
			stationType: '',
			city: '',
			country: '',
		},
		validate: {
			title: (value) => (value === '' ? 'This field cannot be left empty' : null),
			abbreviation: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
			stationType: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
			city: (value) => (value === '' ? 'This field cannot be left empty' : null),
			country: (value) => (value === '' ? 'This field cannot be left empty' : null),
		},
	});
	return (
		<Center>
			<AddStationForm form={newStationForm}></AddStationForm>
		</Center>
	);
};

export default AddStationPage;
