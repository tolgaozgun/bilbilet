import {
	Card,
	Title,
	Flex,
	TextInput,
	Radio,
	Group,
	Select,
	NumberInput,
	Text,
	SelectItem,
} from '@mantine/core';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';
import { primaryAccordionColor } from '../../constants/colors';
import { UseFormReturnType } from '@mantine/form';
import { Car, AddCompanyCar } from '../../types/CarTypes';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import { notifications } from '@mantine/notifications';
import { useMutation } from '@tanstack/react-query';
import useGetCars from '../../hooks/car/useGetCars';
import { addCompanyCar } from '../../services/car';
import { IconCar } from '@tabler/icons-react';
import { forwardRef } from 'react';

interface ItemProps extends React.ComponentPropsWithoutRef<'div'> {
	carId: string;
	label: string;
	description: string;
}
const CustomSelectItem = forwardRef<HTMLDivElement, ItemProps>(
	({ carId, label, description, ...others }: ItemProps, ref) => (
		<div ref={ref} {...others}>
			<Group noWrap>
				<IconCar />
				<div>
					<Text size="sm">{label}</Text>
					<Text size="xs" opacity={0.65}>
						{description}
					</Text>
				</div>
			</Group>
		</div>
	),
);

interface AddCompanyCarFormProps {
	companyId: number;
	form: UseFormReturnType<
		{
			carId: string;
			pricePerDay: number;
			city: string;
			country: string;
		},
		(values: {
			carId: string;
			pricePerDay: number;
			city: string;
			country: string;
		}) => {
			carId: string;
			pricePerDay: number;
			city: string;
			country: string;
		}
	>;
}
const AddCompanyCarForm = ({ form, companyId }: AddCompanyCarFormProps) => {
	const axiosSecure = useAxiosSecure();
	const {
		data: allCars,
		isLoading: isCarsLoading,
		isError: isCarsError,
	} = useGetCars(axiosSecure);

	const { mutate: submitMutation, isLoading: isSubmitLoading } = useMutation({
		mutationKey: ['addCompanyCar'],
		mutationFn: () => addCompanyCar(axiosSecure, companyCarDetails),
		onSuccess: () => {
			//queryClient.invalidateQueries(['wishlist']);
			notifications.show({
				id: 'add-success',
				title: 'Company Car Add Successful!',
				message: 'You have successfully added a new company car!',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'green' },
			});
			form.reset();
		},
		onError: () =>
			notifications.show({
				id: 'add-fail',
				title: 'Company Car Add failed!',
				message: 'error.msg',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
			}),
	});
	if (isCarsLoading || isCarsError || !allCars) {
		return <Flex></Flex>;
	}

	const carList: Array<SelectItem> = allCars.data!.map((car) => {
		return {
			carId: car.carId,
			label: car.brand,
			description: car.model,
			value: car.carId.toString(),
		};
	});
	const companyCarDetails: AddCompanyCar = {
		...form.values,
		companyId: companyId,
	};

	const handleAddCompanyCar = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}
		// Send add company car request
		submitMutation();
	};

	return (
		<Card padding={36} bg={primaryAccordionColor} withBorder radius="xl" shadow="xl">
			<Title>Add A New Car</Title>
			<Flex direction={'column'} gap={'xs'}>
				<form>
					<Flex direction={'column'} gap={'xs'}>
						<Select
							withAsterisk
							label="Car Model"
							itemComponent={CustomSelectItem}
							data={carList}
							{...form.getInputProps('carId')}
						/>
						<TextInput
							withAsterisk
							label="City"
							{...form.getInputProps('city')}
						></TextInput>
						<TextInput
							withAsterisk
							label="Country"
							{...form.getInputProps('country')}
						></TextInput>
						<NumberInput
							withAsterisk
							min={1}
							label="Price Per Day (TL)"
							{...form.getInputProps('pricePerDay')}
						></NumberInput>
					</Flex>
				</form>
				<CustomElevatedButton
					text={'Add Car for Rent'}
					onClick={handleAddCompanyCar}
					isLoading={isSubmitLoading}
				></CustomElevatedButton>
			</Flex>
		</Card>
	);
};

export default AddCompanyCarForm;
