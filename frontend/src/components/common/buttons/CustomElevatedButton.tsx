import { Button } from '@mantine/core';
import { primaryButtonColor } from '../../../constants/colors';

interface CustomElevatedButtonProps {
	text: string;
	leftIcon?: React.ReactNode | undefined;
	onClick?: React.MouseEventHandler<HTMLButtonElement> | undefined;
}
const CustomElevatedButton = ({ text, leftIcon, onClick }: CustomElevatedButtonProps) => {
	return (
		<Button leftIcon={leftIcon} onClick={onClick} bg={primaryButtonColor}>
			{text}
		</Button>
	);
};
export default CustomElevatedButton;
