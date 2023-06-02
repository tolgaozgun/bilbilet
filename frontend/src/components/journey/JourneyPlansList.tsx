import { Card, SimpleGrid, Text, Title } from '@mantine/core';
import { Link } from 'react-router-dom';
import { JourneyPlan } from '../../types';

interface JourneyPlansInterface {
	journeyPlans: JourneyPlan[] | null | undefined;
}

const JourneyPlansList = ({ journeyPlans }: JourneyPlansInterface) => {
	return (
		<SimpleGrid cols={4}>
			{journeyPlans &&
				journeyPlans.map((j) => {
					return (
						<Link to={`${j.journeyPlanId}`}>
							<Card padding={18} shadow="lg">
								<Title order={3}>{j.planTitle}</Title>
								<Text>{/* TODO: Add plan description */}</Text>
							</Card>
						</Link>
					);
				})}
		</SimpleGrid>
	);
};

export default JourneyPlansList;
