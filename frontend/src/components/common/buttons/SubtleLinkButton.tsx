import { Button } from '@mantine/core';
import { Link } from 'react-router-dom';
import { primaryButtonColor } from '../../../constants/colors';

interface SubtleLinkButtonProps {
	to: string;
	size?: string;
	leftIcon?: React.ReactNode;
	children: React.ReactNode;
}

const SubtleLinkButton = ({ to, size, children, leftIcon }: SubtleLinkButtonProps) => {
	return (
		<Link to={to}>
			<Button
				size={size || 'lg'}
				variant="subtle"
				color={primaryButtonColor}
				radius="xs"
				leftIcon={leftIcon || null}
			>
				{children}
			</Button>
		</Link>
	);
};

export default SubtleLinkButton;
