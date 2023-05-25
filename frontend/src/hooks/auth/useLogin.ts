import Cookies from 'js-cookie';
import { login as loginFn } from '../../services/auth';
import { isErrorResponse } from '../../utils/utils';

export const useLogin = () => {
	const login = async (email: string, password: string) => {
		const res = await loginFn(email, password);

		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}

		// Set the cookies and return the user
		Cookies.set('currentUser', JSON.stringify(res.data));
		return res;
	};

	return { login };
};
