import { Card, Center, Loader, SimpleGrid, Stack, Title } from '@mantine/core';
import { notifications } from '@mantine/notifications';
import { Link } from 'react-router-dom';
import CenteredLoader from '../../components/common/other/CenteredLoader';
import JourneyPlansList from '../../components/journey/JourneyPlansList';
import { useUser } from '../../hooks/auth';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import useJourneyPlans from '../../hooks/useJourneyPlans';

const JourneyPlansPage = () => {
	// TODO: Fetch journey plans
	const axiosSecure = useAxiosSecure();
	const user = useUser();
	const { isLoading, isError, data: journeyPlans } = useJourneyPlans(axiosSecure, 1);

	if (isError) {
		notifications.show({
			message: journeyPlans?.msg,
		});
	}
	return (
		<Stack>
			<Title order={2}>Journey Plans</Title>
			{isLoading ? (
				<CenteredLoader />
			) : (
				<JourneyPlansList journeyPlans={journeyPlans?.data} />
			)}
		</Stack>
	);
};

export default JourneyPlansPage;
