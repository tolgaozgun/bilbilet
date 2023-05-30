import { useUser } from '../../../hooks/auth';
import { UserType } from '../../../types';
import AdminMenu from './AdminMenu';
import CompanyMenu from './CompanyMenu';
import TravelerMenu from './TravelerMenu';

const ProfileMenu = () => {
	const user = useUser();
	console.log(user?.userType);
	if (!user) {
		return null;
	}

	if (user.userType === UserType.Admin) {
		return <AdminMenu />;
	} else if (user.userType === UserType.Traveler) {
		return <TravelerMenu />;
	} else if (user.userType === UserType.Company) {
		return <CompanyMenu />;
	}
	return null;
};

export default ProfileMenu;
