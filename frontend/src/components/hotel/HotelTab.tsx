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

const HotelTab = () => {
	const axiosSecure = useAxiosSecure();
	const [filterParams, setFilterParams] = useState<HotelFilterParams | {}>({});
	const {
		data: hotelsListRes,
		isLoading: isHotelsLoading,
		isError: isHotelsError,
	} = useGetHotels(axiosSecure, filterParams);

	if (isHotelsLoading) {
		return <LoadingPage />;
	}
	if (isHotelsError) {
		if (!hotelsListRes) {
			notifications.show({
				message: 'Something went wrong',
			});
		} else if (isErrorResponse(hotelsListRes)) {
			notifications.show({
				message: hotelsListRes.msg,
			});
		}
		return <div>Couldn't fetch hotels...</div>; // TODO: Error page
	}

	const onFilter = (filterParams: HotelFilterParams | {}) => {
		setFilterParams(filterParams);
	};

	const hotelList: Array<Hotel> = hotelsListRes.data!;
	const hotelListCards = hotelList.map((hotel) => <HotelInfoCard hotel={hotel} />);

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

export default HotelTab;
