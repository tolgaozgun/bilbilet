import Cookies from 'js-cookie';
import { registerCompany } from '../../services/auth';
import { RegisterCompany } from '../../types';
import { isErrorResponse } from '../../utils/utils';

export const useRegisterCompany = () => {
	const register = async (companyDetails: RegisterCompany) => {
		const res = await registerCompany(companyDetails);
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
