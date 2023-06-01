import { Button } from '@mantine/core';
import { primaryButtonColor } from '../../../constants/colors';

interface CustomElevatedButtonProps {
	text: string;
	leftIcon?: React.ReactNode | undefined;
	onClick?: React.MouseEventHandler<HTMLButtonElement> | undefined;
	isLoading?: boolean | undefined;
}
const CustomElevatedButton = ({
	text,
	leftIcon,
	onClick,
	isLoading,
}: CustomElevatedButtonProps) => {
	return (
		<Button
			loading={isLoading}
			leftIcon={leftIcon}
			onClick={onClick}
			bg={primaryButtonColor}
		>
			{text}
		</Button>
	);
};
export default CustomElevatedButton;
