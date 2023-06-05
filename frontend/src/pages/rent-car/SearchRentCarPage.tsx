import { Center, Flex, Modal, SelectItem, Stack, Title, Text } from '@mantine/core';
import CarFilter from '../../components/car-rental/CarFilter';
import CarInfoCard from '../../components/car-rental/CarInfoCard';
import CarRentSearchBar from '../../components/car-rental/CarSearchBar';
import useGetAddresses from '../../hooks/location/useGetAddresses';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import { Address } from '../../types/LocationTypes';
import useGetRentals from '../../hooks/rent/useGetRentals';
import { useState } from 'react';
import LoadingPage from '../LoadingPage';
import { CompanyCarRM, RentDetail } from '../../types/CarTypes';
import { useDisclosure } from '@mantine/hooks';
import CustomElevatedButton from '../../components/common/buttons/CustomElevatedButton';
import { useMutation } from '@tanstack/react-query';
import { rentCar } from '../../services/rent';
import { notifications } from '@mantine/notifications';
import { useUser } from '../../hooks/auth';
import ItemsNotFoundPage from '../../components/common/feedback/ItemsNotFoundPage';

const SearchRentCarPage = () => {
	const axiosSecure = useAxiosSecure();
	const user = useUser();

	const [filterParams, setFilterParams] = useState<URLSearchParams>(
		new URLSearchParams(),
	);
	const [opened, { open, close }] = useDisclosure(false);
	const [carId, setCarId] = useState(0);
	const {
		data: allAddresses,
		isLoading: isAddressesLoading,
		isError: isAddressesError,
	} = useGetAddresses(axiosSecure);
	const {
		data: allRentals,
		isLoading: isRentalsLoading,
		isError: isRentalsError,
	} = useGetRentals(axiosSecure, filterParams);

	const { mutate: submitMutation, isLoading: isSubmitLoading } = useMutation({
		mutationKey: ['rentCar'],
		mutationFn: () => rentCar(axiosSecure, rentDetails),
		onSuccess: () => {
			notifications.show({
				id: 'rent-success',
				title: 'Reserved Car Successful!',
				message: 'You have successfully reserved a car!',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'green' },
			});
		},
		onError: (error) =>
			notifications.show({
				id: 'rent-fail',
				title: 'Car Rent failed!',
				message: error.response ? error.response.data.msg : 'Something went wrong',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
			}),
	});
	if (
		isAddressesLoading ||
		isAddressesError ||
		!allAddresses ||
		isRentalsLoading ||
		!allRentals ||
		isRentalsError
	) {
		return <LoadingPage></LoadingPage>;
	}
	const addressList: Array<Address> = allAddresses.data!;
	const addressData: Array<SelectItem> = addressList!.map((address) => {
		return {
			label: address.city,
			value: address.city,
			description: address.country,
		};
	});

	const handleSearch = (filterParams: URLSearchParams) => {
		setFilterParams(filterParams);
	};
	const carRentalList: Array<CompanyCarRM> = allRentals.data!;
	const carListCards = carRentalList.map((car) => (
		<CarInfoCard
			car={car}
			onClick={() => {
				if (filterParams.get('startDate') === '') {
					notifications.show({
						id: 'no-date',
						title: 'No date selected',
						message: 'Please select and search for pickup dates',
						autoClose: 5000,
						withCloseButton: true,
						style: { backgroundColor: 'red' },
					});
				}
				open();
				setCarId(car.companyCarId);
			}}
		/>
	));
	const rentDetails: RentDetail = {
		userId: user?.id!,
		companyCarId: carId,
		startDate: filterParams.get('startDate')!,
		endDate: filterParams.get('endDate')!,
	};
	const handleConfirm = () => {
		submitMutation();
		close();
	};
	return (
		<>
			<Modal opened={opened} onClose={close} centered title="Confirm Reservation">
				<Stack spacing="lg">
					<Text>Are you sure you want to reserve the car?</Text>
					<Flex direction={'row'} justify={'flex-end'} gap={'sm'}>
						<CustomElevatedButton
							text="Cancel"
							color="red"
							onClick={close}
						></CustomElevatedButton>
						<CustomElevatedButton
							text="Confirm"
							color="green"
							onClick={handleConfirm}
						></CustomElevatedButton>
					</Flex>
				</Stack>
			</Modal>
			<Flex direction={'column'} align={'start'} gap={'xl'}>
				<Title>Rent A Car</Title>
				<CarRentSearchBar
					addressList={addressData}
					search={handleSearch}
				></CarRentSearchBar>
				<Flex direction={'row'} gap={'xl'}>
					<CarFilter></CarFilter>
					<Flex direction={'column'} gap={'xl'}>
						{carListCards.length !== 0 ? carListCards : <ItemsNotFoundPage />}
					</Flex>
				</Flex>
			</Flex>
		</>
	);
};

export default SearchRentCarPage;
