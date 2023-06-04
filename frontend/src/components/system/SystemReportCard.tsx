import { Card, Flex, Text } from '@mantine/core';

interface SystemReportCardProps {
	title: string;
    count: number;
	extraInfo: string;
}

const SystemReportCard = ({
	title,
    count,
	extraInfo
}: SystemReportCardProps) => {
	return (
		<Card shadow="xl" withBorder radius={'lg'}>
			<Flex direction={'column'} gap={'xs'} align={'start'}>
				<Flex direction={'column'} gap={'xs'} align={'center'}>
					<Text>{title}</Text>
					<Text>{count}{ }{extraInfo}</Text>
				</Flex>
			</Flex>
		</Card>
	);
};

export default SystemReportCard;
