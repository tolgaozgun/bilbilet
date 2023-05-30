import Cookies from 'js-cookie';
import { registerTraveler } from '../../services/auth';
import { RegisterTraveler } from '../../types';
import { isErrorResponse } from '../../utils/utils';

export const useRegisterUser = () => {
	const register = async (userDetails: RegisterTraveler) => {
		const res = await registerTraveler(userDetails);
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
