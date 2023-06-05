import { Card, Center, Flex, Image, SimpleGrid, Text, Title } from '@mantine/core';
import {
	IconCalendar,
	IconGasStation,
	IconManualGearbox,
	IconUser,
} from '@tabler/icons-react';
import { RentalDetailRM } from '../../types/CarTypes';
interface MyRentedCarCardProps {
	rentDetail: RentalDetailRM;
}
const MyRentedCarCard = ({ rentDetail }: MyRentedCarCardProps) => {
	// const startDate: Date = rentDetail.startDate;
	// const formattedStart: string = startDate.toISOString().split('T')[0];
	// const endDate: Date = rentDetail.endDate;
	// const formattedEnd: string = endDate.toISOString().split('T')[0];

	return (
		<Card shadow="xl" withBorder radius={'lg'}>
			<Flex direction={'column'} gap={'md'}>
				<Center>
					<Title>
						{rentDetail.companyCar.car.brand +
							' ' +
							rentDetail.companyCar.car.model}
					</Title>
				</Center>
				<SimpleGrid cols={3} spacing={'lg'}>
					<Image
						src={rentDetail.companyCar.car.photoUrl}
						alt={
							rentDetail.companyCar.car.brand +
							' ' +
							rentDetail.companyCar.car.model
						}
					/>
					<Flex gap={'lg'} direction={'column'}>
						<Flex direction={'row'} gap={'xs'}>
							<IconUser></IconUser>
							<Text> {rentDetail.companyCar.car.capacity} seats</Text>
						</Flex>
						<Flex direction={'row'} gap={'xs'}>
							<IconGasStation></IconGasStation>
							<Text>{rentDetail.companyCar.car.fuelType}</Text>
						</Flex>
						<Flex direction={'row'} gap={'xs'}>
							<IconManualGearbox></IconManualGearbox>
							<Text>{rentDetail.companyCar.car.gear}</Text>
						</Flex>
					</Flex>
					{/* <Flex direction={'column'} gap={'lg'}>
						<Flex direction={'row'} gap={'xs'}>
							<IconCalendar></IconCalendar>
							<Text>Start Date: {formattedStart}</Text>
						</Flex>
						<Flex direction={'row'} gap={'xs'}>
							<IconCalendar></IconCalendar>
							<Text>EndDate: {formattedEnd}</Text>
						</Flex>
					</Flex> */}
				</SimpleGrid>
			</Flex>
		</Card>
	);
};
export default MyRentedCarCard;
