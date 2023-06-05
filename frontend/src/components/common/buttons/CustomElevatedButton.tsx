import { Button, DefaultMantineColor } from '@mantine/core';
import { primaryButtonColor } from '../../../constants/colors';

interface CustomElevatedButtonProps {
	text: string;
	leftIcon?: React.ReactNode | undefined;
	onClick?: React.MouseEventHandler<HTMLButtonElement> | undefined;
	isLoading?: boolean | undefined;
	color?: DefaultMantineColor | undefined;
	size?: 'xs' | 'sm' | 'md' | 'lg' | 'xl' | undefined;
}
const CustomElevatedButton = ({
	text,
	leftIcon,
	onClick,
	isLoading,
	color,
	size,
}: CustomElevatedButtonProps) => {
	return (
		<Button
			loading={isLoading}
			leftIcon={leftIcon}
			onClick={onClick}
			bg={color ? color : primaryButtonColor}
			size={size}
		>
			{text}
		</Button>
	);
};
export default CustomElevatedButton;
