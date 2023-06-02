import { Button, Card, Stack, Text, TextInput } from '@mantine/core';
import { useForm } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import UploadToBalanceWithCCForm from '../../components/payment/transaction/UploadToBalanceWithCCForm';
import { useUser } from '../../hooks/auth';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import useCompany from '../../hooks/users/useCompany';
import { CompanyInfo } from '../../types';
import { isErrorResponse } from '../../utils/utils';
import LoadingPage from '../LoadingPage';

const CompanyProfilePage = () => {
	const axiosSecure = useAxiosSecure();
	const user = useUser();
	const {
		isLoading,
		isError,
		data: companyResponse,
	} = useCompany(axiosSecure, user?.id!);

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

	const company = companyResponse.data;
	const form = useForm({
		initialValues: {
			companyTitle: company?.company.company_title,
			telephone: company?.user.telephone,
			email: company?.user.email,
			address: company?.company.address,
			type: company?.company.type,
			contactInformation: company?.company.contact_information,
			businessRegistration: company?.company.business_registration,
		},
		validate: {
			companyTitle: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
			telephone: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
			email: (value) => (value === '' ? 'This field cannot be left empty' : null),
			address: (value) => (value === '' ? 'This field cannot be left empty' : null),
			type: (value) =>
				value === undefined ? 'This field cannot be left empty' : null,
			contactInformation: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
			businessRegistration: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
		},
	});
	return (
		<Card>
			<Stack spacing={10}>
				<TextInput
					label="Company Title"
					{...form.getInputProps('companyTitle')}
				/>
				<TextInput label="Telephone" {...form.getInputProps('telephone')} />
				<TextInput label="Email" {...form.getInputProps('email')} />
				<TextInput label="Address" {...form.getInputProps('address')} />
				<TextInput label="Type" {...form.getInputProps('type')} />
				<TextInput
					label="Contact Information"
					{...form.getInputProps('contactInformation')}
				/>
				<TextInput
					label="Business Registration"
					{...form.getInputProps('businessRegistration')}
				/>

				<Button>Update</Button>
				<Card>
					<Text>
						<Text fw={700}> Balance: </Text>
						{company?.company.balance}
					</Text>
					<UploadToBalanceWithCCForm />
				</Card>
			</Stack>
		</Card>
	);
};

export default CompanyProfilePage;
