import { Group, Menu } from '@mantine/core';
import { useUser } from '../../../hooks/auth';
import UserButton from '../buttons/UserButton';

const TravelerMenu = () => {
	const user = useUser();

	if (!user) {
		return null;
	}

	return (
		<Group position="center">
			<Menu withArrow>
				<Menu.Target>
					<UserButton email="cs.selim.guler@gmail.com" name="Selim Can" />
				</Menu.Target>
				<Menu.Dropdown>
					<Menu.Item>Profile</Menu.Item>
					<Menu.Item>My Tickets</Menu.Item>
					<Menu.Item>Travel List</Menu.Item>
				</Menu.Dropdown>
			</Menu>
		</Group>
	);
};

export default TravelerMenu;
