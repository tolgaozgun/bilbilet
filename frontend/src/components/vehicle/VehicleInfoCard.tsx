import { Anchor, Card, Flex, Image, Rating, Text, Title } from '@mantine/core';
import { IconBuilding, IconPhone, IconUserCircle } from '@tabler/icons-react';
import { useNavigate } from 'react-router-dom';
import { CompanyVehicle } from '../../types/VehicleTypes';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';
interface VehicleInfoCardProps {
	vehicle: CompanyVehicle;
}
const VehicleInfoCard = ({ vehicle }: VehicleInfoCardProps) => {
	const navigate = useNavigate();


	return (
		<Card shadow="xl" withBorder radius={'lg'}>
			<Flex direction={'column'} gap={'xs'} align={'start'}>
				<Flex direction={'row'} gap={'sm'} align={'center'}>
					<IconBuilding></IconBuilding>
					<Title order={3}>{vehicle.vehicleType}</Title>
				</Flex>
				<Flex direction={'row'} gap={'md'} align={'center'}>
					<Image
						withPlaceholder
						height={100}
						// src={hotel.photoUrl || null}
						alt="Hotel Image"
					/>
					<Flex direction={'column'} gap={'sm'} align={'center'}>
						{/* <Text>Capacity: {vehicle.capacity}</Text> */}
						{/* <Rating
							readOnly={true}
							value={hotel.rating}
							fractions={2}
							emptySymbol={<IconBuilding color="gray" />}
							fullSymbol={<IconBuilding color="blue" />}
						/> */}
						<Flex direction={'row'} gap={'xs'}>
							<IconUserCircle />
							<Text>Capacity: {vehicle.capacity}</Text>
						</Flex>
					</Flex>
					{/* <Anchor color="white" target="_blank" href={hotel.websiteUrl} span> */}
						{/* <CustomElevatedButton
							text={'Go Hotel Website'}
						></CustomElevatedButton>
					</Anchor> */}
				</Flex>
			</Flex>
		</Card>
	);
};
export default VehicleInfoCard;
