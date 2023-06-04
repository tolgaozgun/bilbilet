import { Button } from '@mantine/core';
import { primaryButtonColor } from '../../../constants/colors';

interface CustomElevatedButtonProps {
	text: string;
	leftIcon?: React.ReactNode | undefined;
	onClick?: React.MouseEventHandler<HTMLButtonElement> | undefined;
	isLoading?: boolean | undefined;
	size?: 'xs' | 'sm' | 'md' | 'lg' | 'xl' | undefined;
}
const CustomElevatedButton = ({
	text,
	leftIcon,
	onClick,
	isLoading,
	size
}: CustomElevatedButtonProps) => {
	return (
		<Button
			loading={isLoading}
			leftIcon={leftIcon}
			onClick={onClick}
			size={size}
			bg={primaryButtonColor}
		>
			{text}
		</Button>
	);
};
export default CustomElevatedButton;
