import { Button, DefaultMantineColor } from '@mantine/core';
import { primaryButtonColor } from '../../../constants/colors';

interface CustomElevatedButtonProps {
	text: string;
	leftIcon?: React.ReactNode | undefined;
	onClick?: React.MouseEventHandler<HTMLButtonElement> | undefined;
	isLoading?: boolean | undefined;
	color?: DefaultMantineColor | undefined;
}
const CustomElevatedButton = ({
	text,
	leftIcon,
	onClick,
	isLoading,
	color,
}: CustomElevatedButtonProps) => {
	return (
		<Button
			loading={isLoading}
			leftIcon={leftIcon}
			onClick={onClick}
			bg={color ? color : primaryButtonColor}
		>
			{text}
		</Button>
	);
};
export default CustomElevatedButton;
