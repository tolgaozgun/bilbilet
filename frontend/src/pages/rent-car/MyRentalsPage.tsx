import { Card, Center, Flex } from '@mantine/core';
import { primaryAccordionColor } from '../../constants/colors';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import { useUser } from '../../hooks/auth';
import useGetMyRentals from '../../hooks/rent/useGetMyRentals';
import LoadingPage from '../LoadingPage';
import { notifications } from '@mantine/notifications';
import { isErrorResponse } from '../../utils/utils';
import { RentalDetailRM } from '../../types/CarTypes';
import MyRentedCarCard from '../../components/car-rental/MyRentedCarCard';

const MyRentalsPage = () => {
	const axiosSecure = useAxiosSecure();
	const user = useUser();

	const {
		data: allMyRentals,
		isLoading: isMyRentalsLoading,
		isError: isMyRentalsError,
	} = useGetMyRentals(axiosSecure, user?.id!);

	if (isMyRentalsLoading) {
		return <LoadingPage />;
	}

	if (isMyRentalsError || !allMyRentals) {
		if (isErrorResponse<Array<RentalDetailRM>>(allMyRentals)) {
			notifications.show({
				message: allMyRentals.msg,
			});
		}
		return <div>Something went wrong...</div>; // TODO: error page
	}
	const myRentData: Array<RentalDetailRM> = allMyRentals.data!;
	const myRentList = myRentData!.map((rent) => {
		return <MyRentedCarCard rentDetail={rent} />;
	});
	return (
		<Center>
			<Card
				padding={36}
				bg={primaryAccordionColor}
				withBorder
				radius="xl"
				shadow="xl"
			>
				<Flex direction={'column'} gap={'md'}>
					{myRentList}
				</Flex>
			</Card>
		</Center>
	);
};

export default MyRentalsPage;
