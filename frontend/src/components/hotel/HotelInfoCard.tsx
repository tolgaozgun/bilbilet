import { Card, Flex, Title, Image, Text, Rating, Anchor } from '@mantine/core';
import { IconBuilding, IconPhone } from '@tabler/icons-react';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';
import { useNavigate } from 'react-router-dom';
import { Hotel } from '../../types/HotelTypes';
interface HotelInfoCardProps {
	hotel: Hotel;
}
const HotelInfoCard = ({ hotel }: HotelInfoCardProps) => {
	const navigate = useNavigate();
	return (
		<Card shadow="xl" withBorder radius={'lg'}>
			<Flex direction={'column'} gap={'xs'} align={'start'}>
				<Flex direction={'row'} gap={'sm'} align={'center'}>
					<IconBuilding></IconBuilding>
					<Title order={3}>{hotel.name}</Title>
				</Flex>
				<Flex direction={'row'} gap={'md'} align={'center'}>
					<Image height={160} src={hotel.photoUrl} alt="Hotel Image" />
					<Flex direction={'column'} gap={'sm'} align={'center'}>
						<Text>{hotel.avgPrice} TRY per night</Text>
						<Rating
							readOnly={true}
							value={hotel.rating}
							fractions={2}
							emptySymbol={<IconBuilding color="gray" />}
							fullSymbol={<IconBuilding color="blue" />}
						/>
						<Flex direction={'row'} gap={'xs'}>
							<IconPhone></IconPhone>
							<Text>{hotel.telephone}</Text>
						</Flex>
					</Flex>
					<Anchor color="white" target="_blank" href={hotel.websiteUrl} span>
						<CustomElevatedButton
							text={'Go Hotel Website'}
						></CustomElevatedButton>
					</Anchor>
				</Flex>
			</Flex>
		</Card>
	);
};
export default HotelInfoCard;
