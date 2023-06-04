import { Card, Flex, Text, Title } from '@mantine/core';
import { IconArrowRight, IconPlane } from '@tabler/icons-react';
import SubtleLinkButton from '../common/buttons/SubtleLinkButton';
interface FareInfoCardProps {
	companyName: string;
	depDate: string;
	departureTime: string;
	arrDate: string;
	arrivalTime: string;
	departureLocation: string;
	arrivalLocation: string;
	departureABB: string;
	arrivalABB: string;
	duration: string;
	price: number;
	fareId: number;
}
const FareInfoCard = ({
	companyName,
	depDate,
	departureTime,
	arrDate,
	arrivalTime,
	departureLocation,
	arrivalLocation,
	departureABB,
	arrivalABB,
	duration,
	price,
	fareId,
}: FareInfoCardProps) => {
	return (
		<Card shadow="xl" withBorder radius={'lg'}>
			<Flex direction={'column'} gap={'xs'} align={'start'} rowGap={20}>
				<Flex direction={'row'} gap={'sm'} align={'center'}>
					<IconPlane></IconPlane>
					<Title color="blue" order={3}>
						{companyName}
					</Title>
				</Flex>
				<Flex direction={'row'} gap={'sm'}>
					<Flex direction={'column'} gap={'xs'} align={'center'}>
						<Text fw={700}>{depDate}</Text>
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
						<Text fw={700}>{arrDate}</Text>
						<Text fw={700}>{arrivalTime}</Text>
						<Text>{arrivalABB}</Text>
						<Text>{arrivalLocation}</Text>
					</Flex>
					<Flex fw={700} align="center">
						Starting from {price} TL
					</Flex>
					<Flex align="center">
						<SubtleLinkButton to={`/fare/${fareId}/select-seats`}>
							Select Seats
						</SubtleLinkButton>
					</Flex>
				</Flex>
			</Flex>
		</Card>
	);
};
export default FareInfoCard;
