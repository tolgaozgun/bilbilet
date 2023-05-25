import Cookies from 'js-cookie';
import { registerUser } from '../../services/auth';
import { RegisterUser } from '../../types';
import { isErrorResponse } from '../../utils/utils';

export const useRegisterUser = () => {
	const register = async (userDetails: RegisterUser) => {
		const res = await registerUser(userDetails);
		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}
		// Set the cookies and return the user
		Cookies.set('currentUser', JSON.stringify(res.data));
		return res;
	};

	return { register };
};
