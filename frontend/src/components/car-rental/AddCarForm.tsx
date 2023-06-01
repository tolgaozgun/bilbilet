import {
	Card,
	Title,
	Flex,
	TextInput,
	Radio,
	Group,
	Select,
	NumberInput,
} from '@mantine/core';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';
import { primaryAccordionColor } from '../../constants/colors';
import { UseFormReturnType } from '@mantine/form';
import { GearType, CarCategoryType, FuelType, Car } from '../../types/CarTypes';
import useGetBrands from '../../hooks/car/useGetBrands';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import { addCar } from '../../services/car';
import { notifications } from '@mantine/notifications';
import { useMutation } from '@tanstack/react-query';

interface AddCarFormProps {
	form: UseFormReturnType<
		{
			capacity: number;
			gear: GearType;
			model: string;
			brand: string;
			category: CarCategoryType;
			fuelType: FuelType;
			photoUrl: string;
			websiteUrl: string;
		},
		(values: {
			capacity: number;
			gear: GearType;
			model: string;
			brand: string;
			category: CarCategoryType;
			fuelType: FuelType;
			photoUrl: string;
			websiteUrl: string;
		}) => {
			capacity: number;
			gear: GearType;
			model: string;
			brand: string;
			category: CarCategoryType;
			fuelType: FuelType;
			photoUrl: string;
			websiteUrl: string;
		}
	>;
}
const AddCarForm = ({ form }: AddCarFormProps) => {
	const axiosSecure = useAxiosSecure();
	const {
		data: allCarBrands,
		isLoading: isCarBrandsLoading,
		isError: isCarBrandsError,
	} = useGetBrands(axiosSecure);
	const { mutate: submitMutation, isLoading: isSubmitLoading } = useMutation({
		mutationKey: ['addCar'],
		mutationFn: () => addCar(axiosSecure, carDetails),
		onSuccess: () => {
			//queryClient.invalidateQueries(['wishlist']);
			notifications.show({
				id: 'add-success',
				title: 'Car Add Successful!',
				message: 'You have successfully added a new car!',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'green' },
			});
			form.reset();
		},
		onError: () =>
			notifications.show({
				id: 'add-fail',
				title: 'Car Add failed!',
				message: 'Hmmmmmmm',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
			}),
	});
	if (isCarBrandsLoading || isCarBrandsError || !allCarBrands) {
		return <Flex></Flex>;
	}

	const carBrandList = allCarBrands.data!.map((brand) => {
		return brand.brand;
	});
	const carDetails: Car = {
		...form.values,
		carId: 0,
	};

	const handleAddCar = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}
		// Send add hotel request
		submitMutation();
	};
	//TODO: Enum?
	const carCategoryList = ['SEDAN', 'SUV', 'SPORT', 'LUXURY', 'HATCHBACK', 'TRUCK'];
	const fuelList = ['PETROL', 'DIESEL', 'ELECTRIC', 'HYBRID', 'OTHER'];
	return (
		<Card padding={36} bg={primaryAccordionColor} withBorder radius="xl" shadow="xl">
			<Title>Add A New Car</Title>
			<Flex direction={'column'} gap={'xs'}>
				<form>
					<Flex direction={'column'} gap={'xs'}>
						<Select
							withAsterisk
							label="Brand"
							data={carBrandList}
							{...form.getInputProps('brand')}
						/>
						<TextInput
							withAsterisk
							label="Model"
							{...form.getInputProps('model')}
						/>
						<NumberInput
							min={1}
							withAsterisk
							label="Capacity"
							{...form.getInputProps('capacity')}
						/>
						<Radio.Group
							name="Gear"
							label="Gear"
							withAsterisk
							{...form.getInputProps('gear')}
						>
							<Group mt="xs">
								<Radio label="Automatic" value={'AUTOMATIC'} />
								<Radio
									{...form.getInputProps('gear')}
									label="Manual"
									value={'MANUAL'}
								/>
							</Group>
						</Radio.Group>
						<Select
							withAsterisk
							label="Fuel"
							clearable
							data={fuelList}
							{...form.getInputProps('fuelType')}
						/>
						<Select
							withAsterisk
							label="Car Category"
							clearable
							data={carCategoryList}
							{...form.getInputProps('category')}
						/>
						<TextInput
							withAsterisk
							label="Image URL"
							{...form.getInputProps('photoUrl')}
						></TextInput>
						<TextInput
							withAsterisk
							label="Website URL"
							{...form.getInputProps('websiteUrl')}
						></TextInput>
					</Flex>
				</form>
				<CustomElevatedButton
					text={'Add Car'}
					onClick={handleAddCar}
					isLoading={isSubmitLoading}
				></CustomElevatedButton>
			</Flex>
		</Card>
	);
};

export default AddCarForm;
