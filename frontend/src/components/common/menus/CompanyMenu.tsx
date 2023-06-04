import { Group, Menu } from '@mantine/core';
import {
	IconBackpack,
	IconCar,
	IconListDetails,
	IconLogout,
	IconMapPin,
	IconPencil,
	IconSearch,
	IconTicket,
	IconUserCircle,
} from '@tabler/icons-react';
import { useLogout, useUser } from '../../../hooks/auth';
import SubtleLinkButton from '../buttons/SubtleLinkButton';
import UserButton from '../buttons/UserButton';

const CompanyMenu = () => {
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
						<SubtleLinkButton
							to="/company/profile"
							size="sm"
							leftIcon={<IconUserCircle />}
						>
							Profile
						</SubtleLinkButton>
					</Menu.Item>
					<Menu.Item>
						<SubtleLinkButton
							to="/add-fare"
							size="sm"
							leftIcon={<IconMapPin />}
						>
							Add Fare
						</SubtleLinkButton>
					</Menu.Item>
					<Menu.Item>
						<SubtleLinkButton
							to="/add-vehicle"
							size="sm"
							leftIcon={<IconCar />}
						>
							Add Vehicle
						</SubtleLinkButton>
					</Menu.Item>
					<Menu.Item>
						<SubtleLinkButton
							to="/sold-tickets"
							size="sm"
							leftIcon={<IconTicket />}
						>
							View Sold Tickets
						</SubtleLinkButton>
					</Menu.Item>
					<Menu.Item>
						<SubtleLinkButton
							to="/my-companys-reviews"
							size="sm"
							leftIcon={<IconPencil />}
						>
							My Reviews
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

export default CompanyMenu;
