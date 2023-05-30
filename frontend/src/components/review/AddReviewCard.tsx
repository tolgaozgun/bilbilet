import { Card, Flex, Textarea, Title } from '@mantine/core';
import RatingBar from '../common/RatingBar';
import { useState } from 'react';
import { IconClock, IconWashMachine, IconMoodSmile } from '@tabler/icons-react';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';

interface AddReviewCardProps {
	isCompany: boolean;
	id: string | undefined;
}
const AddReviewCard = ({ isCompany, id }: AddReviewCardProps) => {
	const [cleanRating, setCleanRating] = useState(0);
	const [puncRating, setPuncRating] = useState(0);
	const [custServRating, setCustServRating] = useState(0);
	const [comment, setComment] = useState('');
	const handleReviewSubmit = () => {
		console.log(isCompany);
		console.log(id);
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
					text={'Submit Review'}
				></CustomElevatedButton>
			</Flex>
		</Card>
	);
};

export default AddReviewCard;
