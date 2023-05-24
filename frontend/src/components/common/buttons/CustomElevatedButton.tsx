import { Button } from '@mantine/core';
import { primaryButtonColor } from '../../../constants/colors';

interface CustomElevatedButtonProps {
	text: string;
	leftIcon: React.ReactNode;
}
const CustomElevatedButton = ({ text, leftIcon }: CustomElevatedButtonProps) => {
	return (
		<Button leftIcon={leftIcon} bg={primaryButtonColor}>
			{text}
		</Button>
	);
};
export default CustomElevatedButton;
