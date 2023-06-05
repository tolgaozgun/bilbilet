import { Card, Flex, Textarea, Title } from '@mantine/core';
import RatingBar from '../common/RatingBar';
import { useState } from 'react';
import { IconClock, IconWashMachine, IconMoodSmile } from '@tabler/icons-react';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';
import { notifications } from '@mantine/notifications';
import { useMutation } from '@tanstack/react-query';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import { useUser } from '../../hooks/auth';
import { addCompanyReview, addTripReview } from '../../services/review';
import { AddCompanyReview, AddTripReview } from '../../types/ReviewTypes';

interface AddReviewCardProps {
	isCompany: boolean;
	id: string;
}
const AddReviewCard = ({ isCompany, id }: AddReviewCardProps) => {
	const axiosSecure = useAxiosSecure();
	const user = useUser();

	const [cleanRating, setCleanRating] = useState(0);
	const [puncRating, setPuncRating] = useState(0);
	const [custServRating, setCustServRating] = useState(0);
	const [comment, setComment] = useState('');
	const companyReviewDetails: AddCompanyReview = {
		comment: comment,
		punctuality: puncRating,
		cleanliness: cleanRating,
		customerService: custServRating,
		userId: user?.id!,
		companyId: parseInt(id.substring(1)),
	};
	const tripReviewDetails: AddTripReview = {
		comment: comment,
		punctuality: puncRating,
		cleanliness: cleanRating,
		customerService: custServRating,
		userId: user?.id!,
		ticketId: parseInt(id),
	};
	const { mutate: submitCompanyReview, isLoading: isCompanyReviewLoading } =
		useMutation({
			mutationKey: ['addReviewCompany'],
			mutationFn: () => addCompanyReview(axiosSecure, companyReviewDetails),
			onSuccess: () => {
				notifications.show({
					id: 'add-success',
					title: 'Review Add Successful!',
					message: 'You have successfully added a new review!',
					autoClose: 5000,
					withCloseButton: true,
					style: { backgroundColor: 'green' },
					styles: (theme) => ({
						title: { color: theme.white },
						description: { color: theme.white }
					})
				});
			},
			onError: () =>
				notifications.show({
					id: 'add-fail',
					title: 'Review Add failed!',
					message: 'Hmmmmmmm',
					autoClose: 5000,
					withCloseButton: true,
					style: { backgroundColor: 'red' },
					styles: (theme) => ({
						title: { color: theme.white },
						description: { color: theme.white }
					})
				}),
		});

	const { mutate: submitTripReview, isLoading: isTripReviewLoading } = useMutation({
		mutationKey: ['addReviewTrip'],
		mutationFn: () => addTripReview(axiosSecure, tripReviewDetails),
		onSuccess: () => {
			notifications.show({
				id: 'add-success',
				title: 'Review Add Successful!',
				message: 'You have successfully added a new review!',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'green' },
				styles: (theme) => ({
					title: { color: theme.white },
					description: { color: theme.white }
				})
			});
		},
		onError: () =>
			notifications.show({
				id: 'add-fail',
				title: 'Review Add failed!',
				message: 'Hmmmmmmm',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
				styles: (theme) => ({
					title: { color: theme.white },
					description: { color: theme.white }
				})
			}),
	});

	const handleReviewSubmit = () => {
		if (isCompany) {
			submitCompanyReview();
		} else {
			submitTripReview();
		}
	};
	return (
		<Card shadow="xl" radius={'lg'}>
			<Flex direction={'column'} gap={'sm'}>
				<Title order={3}>Ratings</Title>
				<Flex direction={'column'} gap={'sm'}>
					<RatingBar
						label={'Cleanliness:'}
						editable={true}
						emptySymbol={<IconWashMachine></IconWashMachine>}
						fullSymbol={<IconWashMachine color="blue"></IconWashMachine>}
						value={cleanRating}
						setValue={setCleanRating}
					></RatingBar>
					<RatingBar
						label={'Punctuality:'}
						editable={true}
						emptySymbol={<IconClock></IconClock>}
						fullSymbol={<IconClock color="blue"></IconClock>}
						value={puncRating}
						setValue={setPuncRating}
					></RatingBar>
					<RatingBar
						label={'Customer Service:'}
						editable={true}
						emptySymbol={<IconMoodSmile></IconMoodSmile>}
						fullSymbol={<IconMoodSmile color="blue"></IconMoodSmile>}
						value={custServRating}
						setValue={setCustServRating}
					></RatingBar>
				</Flex>
				<Title order={3}>Comments</Title>
				<Textarea
					placeholder="Type your comments"
					value={comment}
					onChange={(event) => {
						setComment(event.currentTarget.value);
					}}
				></Textarea>
				<CustomElevatedButton
					onClick={handleReviewSubmit}
					isLoading={isTripReviewLoading || isCompanyReviewLoading}
					text={'Submit Review'}
				></CustomElevatedButton>
			</Flex>
		</Card>
	);
};

export default AddReviewCard;
