import { Card, Center, Flex, Tabs, Title, Text, Textarea } from '@mantine/core';
import {
	IconArrowRight,
	IconBuilding,
	IconClock,
	IconMoodSmile,
	IconPlane,
	IconWashMachine,
} from '@tabler/icons-react';
import { useUser } from '../../hooks/auth';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import useGetMyTripReviews from '../../hooks/review/useGetMyTripReviews';
import useGetMyCompanyReviews from '../../hooks/review/useGetMyCompanyReviews';
import LoadingPage from '../LoadingPage';
import RatingBar from '../../components/common/RatingBar';
const MyReviewsPage = () => {
	const user = useUser();
	const axiosSecure = useAxiosSecure();
	const {
		data: allTripReviews,
		isLoading: isTripReviewsLoading,
		isError: isTripReviewsError,
	} = useGetMyTripReviews(axiosSecure, user?.id!);
	const {
		data: allCompanyReviews,
		isLoading: isCompanyReviewsLoading,
		isError: isCompanyReviewsError,
	} = useGetMyCompanyReviews(axiosSecure, user?.id!);
	if (isCompanyReviewsLoading || isTripReviewsLoading) {
		return <LoadingPage></LoadingPage>;
	}
	if (
		isCompanyReviewsError ||
		isTripReviewsError ||
		!allTripReviews ||
		!allCompanyReviews
	) {
		return <Title>An error has occured</Title>;
	}

	const companyReviewsData = allCompanyReviews.data;
	const myCompanyReviewsCards = companyReviewsData?.map((review) => {
		return (
			<Card shadow="xl" withBorder radius={'lg'}>
				<Center>
					<Text color="green" size={32} fw={600}>
						{review.companyTitle}
					</Text>
				</Center>
				<Title order={3}>Ratings</Title>
				<Flex direction={'column'} gap={'sm'}>
					<RatingBar
						label={'Cleanliness:'}
						editable={false}
						emptySymbol={<IconWashMachine></IconWashMachine>}
						fullSymbol={<IconWashMachine color="blue"></IconWashMachine>}
						value={review.review.cleanliness}
						setValue={() => {}}
					></RatingBar>
					<RatingBar
						label={'Punctuality:'}
						editable={true}
						emptySymbol={<IconClock></IconClock>}
						fullSymbol={<IconClock color="blue"></IconClock>}
						value={review.review.punctuality}
						setValue={() => {}}
					></RatingBar>
					<RatingBar
						label={'Customer Service:'}
						editable={true}
						emptySymbol={<IconMoodSmile></IconMoodSmile>}
						fullSymbol={<IconMoodSmile color="blue"></IconMoodSmile>}
						value={review.review.customerService}
						setValue={() => {}}
					></RatingBar>
					<Title order={3}>Comments:</Title>
					<Text>{review.review.comment}</Text>
				</Flex>
			</Card>
		);
	});
	const tripReviewsData = allTripReviews.data;
	const myTripReviewsCards = tripReviewsData?.map((review) => {
		return (
			<Card shadow="xl" withBorder radius={'lg'}>
				<Center>
					<Text color="green" size={32} fw={600}>
						{review.companyTitle}
					</Text>
				</Center>
				<Flex direction={'row'} gap={'xs'}>
					<Text>{review.depStationTitle}</Text>
					<IconArrowRight></IconArrowRight>
					<Text>{review.arrStationTitle}</Text>
				</Flex>
				<Title order={3}>Ratings</Title>
				<Flex direction={'column'} gap={'sm'}>
					<RatingBar
						label={'Cleanliness:'}
						editable={false}
						emptySymbol={<IconWashMachine></IconWashMachine>}
						fullSymbol={<IconWashMachine color="blue"></IconWashMachine>}
						value={review.review.cleanliness}
						setValue={() => {}}
					></RatingBar>
					<RatingBar
						label={'Punctuality:'}
						editable={true}
						emptySymbol={<IconClock></IconClock>}
						fullSymbol={<IconClock color="blue"></IconClock>}
						value={review.review.punctuality}
						setValue={() => {}}
					></RatingBar>
					<RatingBar
						label={'Customer Service:'}
						editable={true}
						emptySymbol={<IconMoodSmile></IconMoodSmile>}
						fullSymbol={<IconMoodSmile color="blue"></IconMoodSmile>}
						value={review.review.customerService}
						setValue={() => {}}
					></RatingBar>
					<Title order={3}>Comments:</Title>
					<Text>{review.review.comment}</Text>
				</Flex>
			</Card>
		);
	});

	return (
		<Center miw={400}>
			<Tabs defaultValue="company">
				<Tabs.List>
					<Tabs.Tab icon={<IconBuilding size="1rem" />} value="company">
						My Company Reviews
					</Tabs.Tab>
					<Tabs.Tab icon={<IconPlane size="1rem" />} value="trip">
						My Trip Reviews
					</Tabs.Tab>
				</Tabs.List>

				<Tabs.Panel value="company" pt="md">
					<Flex direction={'column'} gap={'md'}>
						<Title>My Company Reviews</Title>
						<Card shadow="xl" withBorder radius={'lg'}>
							{myCompanyReviewsCards}
						</Card>
					</Flex>
				</Tabs.Panel>

				<Tabs.Panel value="trip" pt="md">
					<Title>My Trip Reviews</Title>
					<Card shadow="xl" withBorder radius={'lg'}>
						{myTripReviewsCards}
					</Card>
				</Tabs.Panel>
			</Tabs>
		</Center>
	);
};

export default MyReviewsPage;
