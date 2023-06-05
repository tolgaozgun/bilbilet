import { Group, Menu } from '@mantine/core';
import {
	IconBackpack,
	IconBuilding,
	IconCar,
	IconGps,
	IconListDetails,
	IconLogout,
	IconMapPin,
	IconPencil,
	IconReport,
	IconSearch,
	IconTicket,
	IconUserCircle,
} from '@tabler/icons-react';
import { useLogout, useUser } from '../../../hooks/auth';
import SubtleLinkButton from '../buttons/SubtleLinkButton';
import UserButton from '../buttons/UserButton';

const AdminMenu = () => {
	const user = useUser();

	if (!user) {
		return null;
	}

	const { logout } = useLogout();
	const onLogout = () => {
		logout();
	};

	return (
		<Group position="center">
			<Menu withArrow>
				<Menu.Target>
					<UserButton email={user.email} name={user.name} />
				</Menu.Target>
				<Menu.Dropdown>
					<Menu.Item>
						<SubtleLinkButton to="/add-car" size="sm" leftIcon={<IconCar />}>
							Add Car
						</SubtleLinkButton>
					</Menu.Item>
					<Menu.Item>
						<SubtleLinkButton
							to="/add-hotel"
							size="sm"
							leftIcon={<IconBuilding />}
						>
							Add Hotel
						</SubtleLinkButton>
					</Menu.Item>
					<Menu.Item>
						<SubtleLinkButton
							to="/add-address"
							size="sm"
							leftIcon={<IconGps />}
						>
							Add Address
						</SubtleLinkButton>
					</Menu.Item>
					<Menu.Item>
						<SubtleLinkButton
							to="/add-station"
							size="sm"
							leftIcon={<IconMapPin />}
						>
							Add Station
						</SubtleLinkButton>
					</Menu.Item>
					<Menu.Item>
						<SubtleLinkButton
							to="/system-reports"
							size="sm"
							leftIcon={<IconReport />}
						>
							System Reports
						</SubtleLinkButton>
					</Menu.Item>
					<Menu.Item>
						<SubtleLinkButton
							onClick={onLogout}
							size="sm"
							leftIcon={<IconLogout />}
							color="red"
							to="/login"
						>
							Log out
						</SubtleLinkButton>
					</Menu.Item>
				</Menu.Dropdown>
			</Menu>
		</Group>
	);
};

export default AdminMenu;
