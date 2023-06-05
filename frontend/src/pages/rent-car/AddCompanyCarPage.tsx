import { Center } from '@mantine/core';
import { useForm } from '@mantine/form';
import { CarCategoryType, FuelType, GearType } from '../../types/CarTypes';
import AddCompanyCarForm from '../../components/car-rental/AddCompanyCarForm';
import useCompany from '../../hooks/users/useCompany';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import { useUser } from '../../hooks/auth';
import LoadingPage from '../LoadingPage';
import { isErrorResponse } from '../../utils/utils';
import { CompanyInfo } from '../../types';
import { notifications } from '@mantine/notifications';

const AddCompanyCarPage = () => {
	const axiosSecure = useAxiosSecure();
	const user = useUser();
	const {
		isLoading,
		isError,
		data: companyResponse,
	} = useCompany(axiosSecure, user?.id!);
	const addCompanyCarForm = useForm({
		initialValues: {
			carId: '',
			pricePerDay: 0,
			city: '',
			country: '',
		},
		validate: {
			city: (value) => (value === '' ? 'This field cannot be left empty' : null),
			country: (value) => (value === '' ? 'This field cannot be left empty' : null),
		},
	});

	if (isLoading) {
		return <LoadingPage />;
	}

	if (isError) {
		if (isErrorResponse<CompanyInfo>(companyResponse)) {
			notifications.show({
				message: companyResponse.msg,
			});
		}
		return <div></div>; // TODO: error page
	}
	const companyData = companyResponse.data;

	return (
		<Center>
			<AddCompanyCarForm
				form={addCompanyCarForm}
				companyId={companyData!.company.company_id}
			></AddCompanyCarForm>
		</Center>
	);
};

export default AddCompanyCarPage;
