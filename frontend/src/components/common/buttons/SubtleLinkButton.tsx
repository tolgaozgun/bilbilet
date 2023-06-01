import { Button } from '@mantine/core';
import { Link } from 'react-router-dom';
import { primaryButtonColor } from '../../../constants/colors';

interface SubtleLinkButtonProps {
	to: string;
	size?: string;
	leftIcon?: React.ReactNode;
	color?: string;
	children: React.ReactNode;
}

const SubtleLinkButton = ({
	to,
	size,
	children,
	leftIcon,
	color,
}: SubtleLinkButtonProps) => {
	return (
		<Link to={to}>
			<Button
				size={size || 'lg'}
				variant="subtle"
				color={color || primaryButtonColor}
				radius="xs"
				leftIcon={leftIcon || null}
			>
				{children}
			</Button>
		</Link>
	);
};

export default SubtleLinkButton;
