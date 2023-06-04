import { Center } from '@mantine/core';
import AddVehicleForm from '../../components/vehicle/AddVehicleForm';
import { useForm } from '@mantine/form';

const AddVehiclePage = () => {
	const vehicleForm = useForm({
		initialValues: {
			capacity: 0,
			seatConfigurationId: 0,
			vehicleType: "",
			plateNumber: "",
			tailNumber: ""
		},
		validate: {
			plateNumber: (value, values) => (values.vehicleType.toLowerCase() === "bus" && value === '' ? 'This field cannot be left empty' : null),
			tailNumber: (value, values) => (values.vehicleType.toLowerCase() === "plane" && value === '' ? 'This field cannot be left empty' : null),
			capacity: (value) =>
				value == 0 ? 'This field cannot be left empty' : null,
			seatConfigurationId: (value) =>
				value == 0 ? 'This field cannot be left empty' : null
		},
	});
	return (
		<Center>
			<AddVehicleForm form={vehicleForm}></AddVehicleForm>
		</Center>
	);
};

export default AddVehiclePage;
