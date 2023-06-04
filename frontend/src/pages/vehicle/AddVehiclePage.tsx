import { Center } from '@mantine/core';
import AddVehicleForm from '../../components/vehicle/AddVehicleForm';
import { useForm } from '@mantine/form';

const AddVehiclePage = () => {
	const vehicleForm = useForm({
		initialValues: {
			capacity: 0,
			vehicleType: "",
			plateNumber: "",
			tailNumber: "",
			configName: "",
			seatingArrangement: "",
			configTotalRows: 0,
			configTotalColumns: 0,
			firstClassAfter: 0,
			businessClassAfter: 0,
			premiumEconomyClassAfter: 0,
		},
		validate: {
			plateNumber: (value, values) => (values.vehicleType.toLowerCase() === "bus" && value === '' ? 'This field cannot be left empty' : null),
			tailNumber: (value, values) => (values.vehicleType.toLowerCase() === "plane" && value === '' ? 'This field cannot be left empty' : null),
			vehicleType: (value) => (value === '' || (value.toLowerCase() != "bus" && value.toLowerCase() != "plane") ? 'This field should be Bus or Plane': null),
			capacity: (value) =>
				value == 0 ? 'This field cannot be left empty' : null,
			configName: (value) =>
				value == '' ? 'This field cannot be left empty' : null,
			seatingArrangement: (value) =>
				value == '' ? 'This field cannot be left empty' : null,
			configTotalRows: (value) =>
				value == 0 ? 'This field cannot be left empty' : null,
			configTotalColumns: (value) =>	
				value == 0 ? 'This field cannot be left empty' : null,
		},
	});
	return (
		<Center>
			<AddVehicleForm form={vehicleForm}></AddVehicleForm>
		</Center>
	);
};

export default AddVehiclePage;
