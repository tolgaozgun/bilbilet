import { isErrorResponse } from '../../utils/utils';
import { addFare as addFareFn } from '../../services/fare';
import { AddFare } from '../../types/FareTypes';
export const useAddFare = () => {
	const addFare = async (fareDetails: AddFare) => {
		const res = await addFareFn(fareDetails);

		return res;
	};

	return { addFare };
};
