import { Center } from '@mantine/core';
import AddReviewCard from '../../components/review/AddReviewCard';
import { useParams } from 'react-router-dom';
interface ReviewPageProps {
	isCompany: boolean;
}
const ReviewPage = ({ isCompany }: ReviewPageProps) => {
	const { id } = useParams();

	return (
		<Center>
			<AddReviewCard isCompany={isCompany} id={id ?? '0'}></AddReviewCard>
		</Center>
	);
};

export default ReviewPage;
