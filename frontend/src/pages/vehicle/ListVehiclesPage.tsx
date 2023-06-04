import { Card, Flex, Title } from '@mantine/core';
import { notifications } from '@mantine/notifications';
import { useQueryClient } from '@tanstack/react-query';
import { useEffect, useState } from 'react';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import useGetHotels from '../../hooks/hotel/useGetHotels';
import LoadingPage from '../../pages/LoadingPage';
import { Hotel, HotelFilterParams } from '../../types';
import { isErrorResponse } from '../../utils/utils';
import ItemsNotFoundPage from '../common/feedback/ItemsNotFoundPage';
import HotelFilter from './HotelFilter';
import HotelInfoCard from './HotelInfoCard';
import useGetVehicles from '../../hooks/vehicle/useGetVehicles';
import { useUser } from '../../hooks/auth/useUser';
import useCompany from '../../hooks/users/useCompany';
import { CompanyVehicle } from '../../types/VehicleTypes';

const ListVehiclesPage = () => {
	const user = useUser();
	const axiosSecure = useAxiosSecure();
	const {
		isLoading: isCompanyLoading,
		isError: isCompanyError,
		data: companyResponse,
	} = useCompany(axiosSecure, user?.id!);
	const companyId: number = companyResponse?.data?.company.company_id!;
    
	const {
		data: vehiclesListRes,
		isLoading: isVehiclesLoading,
		isError: isVehiclesError,
	} = useGetVehicles(axiosSecure, companyId);

	if (isVehiclesError) {
		if (!vehiclesListRes) {
			notifications.show({
				message: 'Error with connection to the server.',
			});
		} else if (isErrorResponse(vehiclesListRes)) {
			notifications.show({
				message: vehiclesListRes.msg,
			});
		}
		return <div>Couldn't fetch vehicles...</div>; // TODO: Error page
	}

	const hotelList: Array<CompanyVehicle> = vehiclesListRes.data!;
	const hotelListCards = hotelList.map((hotel) => <HotelInfoCard hotel={hotel} />);



	if (isVehiclesLoading || isCompanyLoading) {
		return <LoadingPage />;
	}

	return (
		<Card withBorder radius="xl" shadow="xl" p={48} miw={500} mx="auto">
			<Flex direction={'column'} align={'start'} gap={'xl'}>
				<Title>Discover Hotels</Title>
				<Flex direction={'row'} gap={'xl'}>
					<HotelFilter onFilter={onFilter} />
					<Flex direction={'column'} gap={'xl'}>
						{hotelListCards.length === 0 ? (
							<ItemsNotFoundPage />
						) : (
							hotelListCards
						)}
					</Flex>
				</Flex>
			</Flex>
		</Card>
	);
};

export default ListVehiclesPage;
