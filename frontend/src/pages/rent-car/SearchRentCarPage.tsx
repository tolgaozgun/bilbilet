import { Center, Flex, SelectItem, Title } from '@mantine/core';
import CarFilter from '../../components/car-rental/CarFilter';
import CarInfoCard from '../../components/car-rental/CarInfoCard';
import CarRentSearchBar from '../../components/car-rental/CarSearchBar';
import useGetAddresses from '../../hooks/location/useGetAddresses';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import { Address } from '../../types/LocationTypes';

const SearchRentCarPage = () => {
	const axiosSecure = useAxiosSecure();
	const {
		data: allAddresses,
		isLoading: isAddressesLoading,
		isError: isAddressesError,
	} = useGetAddresses(axiosSecure);

	if (isAddressesLoading || isAddressesError || !allAddresses) {
		return <Flex></Flex>;
	}
	const addressList: Array<Address> = allAddresses.data!;
	const addressData: Array<SelectItem> = addressList!.map((address) => {
		return {
			label: address.city,
			value: address.addressId.toString(),
			description: address.country,
		};
	});
	return (
		<Flex direction={'column'} align={'start'} gap={'xl'}>
			<Title>Rent A Car</Title>
			<CarRentSearchBar addressList={addressData}></CarRentSearchBar>
			<Flex direction={'row'} gap={'xl'}>
				<CarFilter></CarFilter>
				<Flex direction={'column'} gap={'xl'}>
					<CarInfoCard
						carName={'Hyundai Kona'}
						seat={5}
						fuel={'Gas'}
						gear={'Automatic'}
						dailyPrice={1200}
						totalPrice={3600}
					/>
					<CarInfoCard
						carName={'Hyundai Kona'}
						seat={5}
						fuel={'Gas'}
						gear={'Automatic'}
						dailyPrice={1200}
						totalPrice={3600}
					/>
					<CarInfoCard
						carName={'Hyundai Kona'}
						seat={5}
						fuel={'Gas'}
						gear={'Automatic'}
						dailyPrice={1200}
						totalPrice={3600}
					/>
				</Flex>
			</Flex>
		</Flex>
	);
};

export default SearchRentCarPage;
