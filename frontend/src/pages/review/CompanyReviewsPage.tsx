import { Card, Center, Flex, Tabs, Title, Text } from '@mantine/core';
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
import LoadingPage from '../LoadingPage';
import RatingBar from '../../components/common/RatingBar';
import useCompany from '../../hooks/users/useCompany';
import useGetCompanyReviews from '../../hooks/review/useGetCompanyReviews';
import useGetCompanyTripsReviews from '../../hooks/review/useGetCompanyTripsReviews';
import useGetCompanyAverages from '../../hooks/review/useGetCompanyAverages';
import ItemsNotFoundPage from '../../components/common/feedback/ItemsNotFoundPage';
const CompanyReviewsPage = () => {
	const user = useUser();
	const axiosSecure = useAxiosSecure();
	const {
		isLoading: isCompanyLoading,
		isError: isCompanyError,
		data: companyResponse,
	} = useCompany(axiosSecure, user?.id!);
	const companyId = companyResponse?.data?.company.company_id!;
	const {
		data: allCompanyReviews,
		isLoading: isCompanyReviewsLoading,
		isError: isCompanyReviewsError,
	} = useGetCompanyReviews(axiosSecure, companyId);
	const {
		data: allCompanyTrips,
		isLoading: isCompanyTripsLoading,
		isError: isCompanyTripsError,
	} = useGetCompanyTripsReviews(axiosSecure, companyId);
	const {
		data: allCompanyAverage,
		isLoading: isCompanyAverageLoading,
		isError: isCompanyAverageError,
	} = useGetCompanyAverages(axiosSecure, companyId);
	if (
		isCompanyReviewsLoading ||
		isCompanyLoading ||
		isCompanyTripsLoading ||
		isCompanyAverageLoading
	) {
		return <LoadingPage></LoadingPage>;
	}
	if (
		isCompanyReviewsError ||
		!allCompanyReviews ||
		isCompanyTripsError ||
		isCompanyError ||
		isCompanyAverageError ||
		!allCompanyAverage
	) {
		return <Title>An error has occured</Title>;
	}
	const companyAverages = allCompanyAverage.data![0];
	const companyTripsReviewsData = allCompanyTrips.data;
	const companyTripsReviewsCards = companyTripsReviewsData?.map((review) => {
		return (
			<Card shadow="xl" withBorder radius={'lg'}>
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
	const companyReviewsData = allCompanyReviews.data;
	const myCompanyReviewsCards = companyReviewsData?.map((review) => {
		return (
			<Card shadow="xl" withBorder radius={'lg'}>
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
						My Company's Reviews
					</Tabs.Tab>
					<Tabs.Tab icon={<IconPlane size="1rem" />} value="trip">
						My Company's Trip Reviews
					</Tabs.Tab>
				</Tabs.List>

				<Tabs.Panel value="company" pt="md">
					<Flex direction={'column'} gap={'md'}>
						<Title>My Company's Past Reviews</Title>
						<Flex direction={'column'} gap={'sm'}>
							<Text>
								Average Cleanliness Rating: {companyAverages.avgClean}
							</Text>
							<Text>
								Average Punctuality Rating: {companyAverages.avgPunct}
							</Text>
							<Text>
								Average Customer Service Rating:{' '}
								{companyAverages.avgCustSer}
							</Text>
						</Flex>
						<Card shadow="xl" withBorder radius={'lg'}>
							{myCompanyReviewsCards?.length === 0 ? (
								<ItemsNotFoundPage />
							) : (
								myCompanyReviewsCards
							)}
						</Card>
					</Flex>
				</Tabs.Panel>

				<Tabs.Panel value="trip" pt="md">
					<Flex direction={'column'} gap={'sm'}>
						<Title>My Company's Past Trip Reviews</Title>
						<Card shadow="xl" withBorder radius={'lg'}>
							{companyTripsReviewsCards?.length === 0 ? (
								<ItemsNotFoundPage />
							) : (
								companyTripsReviewsCards
							)}
						</Card>
					</Flex>
				</Tabs.Panel>
			</Tabs>
		</Center>
	);
};

export default CompanyReviewsPage;
