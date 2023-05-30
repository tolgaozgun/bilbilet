import { Group, Rating, Text } from '@mantine/core';

interface RatingBarProps {
	label: string | undefined;
	editable: boolean;
	emptySymbol: React.ReactNode;
	fullSymbol: React.ReactNode;
	value: number;
	setValue: React.Dispatch<React.SetStateAction<number>>;
}
const RatingBar = ({
	label,
	editable,
	emptySymbol,
	fullSymbol,
	value,
	setValue,
}: RatingBarProps) => {
	return (
		<Group>
			<Text fw={500}>{label}</Text>
			<Rating
				readOnly={!editable}
				onChange={setValue}
				value={value}
				fractions={2}
				emptySymbol={emptySymbol}
				fullSymbol={fullSymbol}
			/>
		</Group>
	);
};

export default RatingBar;
