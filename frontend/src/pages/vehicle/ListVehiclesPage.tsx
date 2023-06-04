import { Card, Flex, Title } from '@mantine/core';
import { notifications } from '@mantine/notifications';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import LoadingPage from '../../pages/LoadingPage';
import { isErrorResponse } from '../../utils/utils';
import useGetVehicles from '../../hooks/vehicle/useGetVehicles';
import { useUser } from '../../hooks/auth/useUser';
import useCompany from '../../hooks/users/useCompany';
import { CompanyVehicle } from '../../types/VehicleTypes';
import VehicleInfoCard from '../../components/vehicle/VehicleInfoCard';
import ItemsNotFoundPage from '../../components/common/feedback/ItemsNotFoundPage';
import { useEffect } from 'react';
import CustomElevatedButton from '../../components/common/buttons/CustomElevatedButton';
import { useNavigate } from 'react-router-dom';
import { IconCar } from '@tabler/icons-react';

const ListVehiclesPage = () => {
	const user = useUser();
	const axiosSecure = useAxiosSecure();
	const {
		isLoading: isCompanyLoading,
		isError: isCompanyError,
		data: companyResponse,
	} = useCompany(axiosSecure, user?.id!);
	const companyId: number = companyResponse?.data?.company.company_id!
    
	const {
		data: vehiclesListRes,
		isLoading: isVehiclesLoading,
		isError: isVehiclesError,
	} = useGetVehicles(axiosSecure, companyId);

	const navigate = useNavigate();
	


	const vehiclesList: Array<CompanyVehicle> = vehiclesListRes ? vehiclesListRes.data!: [];
    console.log(vehiclesList);
    console.log(vehiclesListRes);
	const vehiclesListCards = vehiclesList.map((vehicle) => <VehicleInfoCard vehicle={vehicle} />);


	if (isVehiclesLoading || isCompanyLoading) {
		return <LoadingPage />;
	}

	if (isVehiclesError || isCompanyError) {
		if (!vehiclesListRes) {
			notifications.show({
				message: 'Error with connection to the server.',
			});
		} else if (isErrorResponse(vehiclesListRes)) {
			notifications.show({
				message: vehiclesListRes.msg,
			});
		}
        if (companyResponse?.msg) {
			notifications.show({
				message: companyResponse.msg,
			});
		}
		return <div>Couldn't fetch vehicles...</div>; // TODO: Error page
	}

	return (
		<Card withBorder radius="xl" shadow="xl" p={48} miw={500} mx="auto">
			<CustomElevatedButton
				text={'Add Vehicle'}
				leftIcon={<IconCar />}
				size={'lg'}
				onClick={() => {
					navigate('/add-vehicle');
				}}
			/>
			<Title mt={20}>Company Vehicles</Title>
			

			<Flex mt={20} direction={'column'} align={'start'} gap={'xl'}>
					{vehiclesListCards.length === 0 ? (
						<ItemsNotFoundPage />
					) : (
						vehiclesListCards
					)}
			</Flex>
		</Card>
	);
};

export default ListVehiclesPage;
