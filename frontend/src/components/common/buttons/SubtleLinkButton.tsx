import { Button } from '@mantine/core';
import { Link } from 'react-router-dom';
import { primaryButtonColor } from '../../../constants/colors';

interface SubtleLinkButtonProps {
	to: string;
	size?: string;
	leftIcon?: React.ReactNode;
	color?: string;
	children: React.ReactNode;
	onClick?: () => void;
}

const SubtleLinkButton = ({
	to,
	size,
	children,
	leftIcon,
	color,
	onClick,
}: SubtleLinkButtonProps) => {
	return (
		<Link to={to}>
			<Button
				size={size || 'lg'}
				variant="filled"
				// gradient={{ from: '#3B5BDB', to: '#22B8CF', deg: 360 }}
				color={color || primaryButtonColor}
				radius="xs"
				leftIcon={leftIcon || null}
				onClick={onClick}
				style={{ borderRadius: '8px' }}
			>
				{children}
			</Button>
		</Link>
	);
};

export default SubtleLinkButton;
