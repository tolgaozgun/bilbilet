import { Card, Flex, Title, Text } from '@mantine/core';
import { IconPlane, IconArrowRight, IconEdit } from '@tabler/icons-react';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';

interface PastTicketCardProps {
	companyName: string;
	departureTime: string;
	arrivalTime: string;
	departureLocation: string;
	arrivalLocation: string;
	departureABB: string;
	arrivalABB: string;
	duration: string;
	price: number;
	status: string;
	seat: string;
}
const PastTicketCard = ({
	companyName,
	departureTime,
	arrivalTime,
	departureLocation,
	arrivalLocation,
	departureABB,
	arrivalABB,
	duration,
	price,
	status,
	seat,
}: PastTicketCardProps) => {
	return (
		<Card shadow="xl" withBorder radius={'lg'}>
			<Flex direction={'column'} gap={'xs'} align={'center'}>
				<Flex direction={'row'} gap={'sm'} align={'center'}>
					<IconPlane></IconPlane>
					<Title order={3}>{companyName}</Title>
				</Flex>
				<Flex direction={'row'} gap={'sm'}>
					<Flex direction={'column'} gap={'xs'} align={'center'}>
						<Text fw={700}>{departureTime}</Text>
						<Text>{departureABB}</Text>
						<Text>{departureLocation}</Text>
					</Flex>

					<Flex
						direction={'column'}
						gap={0}
						align={'center'}
						justify={'center'}
					>
						<Text>{duration}</Text>
						<IconArrowRight size={40}></IconArrowRight>
					</Flex>

					<Flex direction={'column'} gap={'xs'} align={'center'}>
						<Text fw={700}>{arrivalTime}</Text>
						<Text>{arrivalABB}</Text>
						<Text>{arrivalLocation}</Text>
					</Flex>
					<Flex direction={'column'} gap={'sm'}>
						<CustomElevatedButton
							leftIcon={<IconEdit />}
							text={'Review Trip'}
						></CustomElevatedButton>
						<CustomElevatedButton
							leftIcon={<IconEdit />}
							text={'Review Company'}
						></CustomElevatedButton>
					</Flex>
				</Flex>
				<Flex direction={'row'} gap={'xl'}>
					<Text fw={700}>
						Seat:
						<Text span>{seat}</Text>
					</Text>
					<Text fw={700}>
						Price:
						<Text span color="red">
							{price}
						</Text>
					</Text>
					<Text fw={700}>
						Status:
						<Text span color="green">
							{status}
						</Text>
					</Text>
				</Flex>
			</Flex>
		</Card>
	);
};

export default PastTicketCard;
