import Cookies from 'js-cookie';
import { logout as logoutFn } from '../../services/auth';

export const useLogout = () => {
	const logout = async () => {
		logoutFn();
		Cookies.remove('currentUser');
	};

	return { logout };
};
