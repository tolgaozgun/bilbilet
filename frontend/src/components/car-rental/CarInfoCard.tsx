import { Card, Center, Flex, Image, SimpleGrid, Text, Title } from '@mantine/core';
import { IconGasStation, IconManualGearbox, IconUser } from '@tabler/icons-react';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';
import { CompanyCarRM } from '../../types/CarTypes';
interface CarInfoCardProps {
	car: CompanyCarRM;
	onClick?: React.MouseEventHandler<HTMLButtonElement> | undefined;
}
const CarInfoCard = ({ car, onClick }: CarInfoCardProps) => {
	return (
		<Card shadow="xl" withBorder radius={'lg'}>
			<Flex direction={'column'} gap={'sm'}>
				<Center>
					<Title>{car.car.brand + ' ' + car.car.model}</Title>
				</Center>
				<SimpleGrid cols={3} spacing={'lg'}>
					<Image
						src={car.car.photoUrl}
						alt={car.car.brand + ' ' + car.car.model}
					/>
					<SimpleGrid cols={2} spacing="lg" verticalSpacing={'xs'}>
						<Flex direction={'row'} gap={'xs'}>
							<IconUser></IconUser>
							<Text> {car.car.capacity} seats</Text>
						</Flex>
						<Flex direction={'row'} gap={'xs'}>
							<IconGasStation></IconGasStation>
							<Text>{car.car.fuelType}</Text>
						</Flex>
						<Flex direction={'row'} gap={'xs'}>
							<IconManualGearbox></IconManualGearbox>
							<Text>{car.car.gear}</Text>
						</Flex>
					</SimpleGrid>
					<Flex direction={'column'} gap={'sm'}>
						<Text color="red">TRY {car.pricePerDay} per day</Text>
						<CustomElevatedButton
							text={'Get Deal'}
							onClick={onClick}
						></CustomElevatedButton>
					</Flex>
				</SimpleGrid>
			</Flex>
		</Card>
	);
};
export default CarInfoCard;
