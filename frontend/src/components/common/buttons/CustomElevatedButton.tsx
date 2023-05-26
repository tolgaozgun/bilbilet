import { Button } from '@mantine/core';
import { primaryButtonColor } from '../../../constants/colors';

interface CustomElevatedButtonProps {
	text: string;
	onClick?: React.MouseEventHandler<HTMLButtonElement> | undefined;
}
const CustomElevatedButton = ({ text, onClick }: CustomElevatedButtonProps) => {
	return (
		<Button onClick={onClick} bg={primaryButtonColor}>
			{text}
		</Button>
	);
};
export default CustomElevatedButton;
