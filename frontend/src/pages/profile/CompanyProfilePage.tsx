import { Button, Card, Center, Modal, Stack, Text, TextInput, Title } from '@mantine/core';
import { useForm } from '@mantine/form';
import { useDisclosure } from '@mantine/hooks';
import { notifications } from '@mantine/notifications';
import UploadToBalanceWithCCForm from '../../components/payment/transaction/UploadToBalanceWithCCForm';
import { useUser } from '../../hooks/auth';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import useCompany from '../../hooks/users/useCompany';
import { CompanyInfo } from '../../types';
import { isErrorResponse } from '../../utils/utils';
import LoadingPage from '../LoadingPage';
import WithdrawBalanceWithIBANForm from '../../components/payment/transaction/WithdrawBalanceWithIBANForm';

const CompanyProfilePage = () => {
	// const [opened, { open, close }] = useDisclosure(false);
	// const axiosSecure = useAxiosSecure();
	// const user = useUser();
	// const {
	// 	isLoading,
	// 	isError,
	// 	data: travelerResponse,
	// } = useTraveler(axiosSecure, user?.id!);
	// const traveler = travelerResponse?.data;

	// if (isLoading) {
	// 	return <LoadingPage />;
	// }
	const [opened, { open, close }] = useDisclosure(false);
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
	const companyData = companyResponse.data;


	return (
		<>
			<Center>
				<Card>
					<Title order={3}>Profile details</Title>
					<Stack spacing="lg">
					<Text>
						<Text fw={700}> Company title</Text>
						{companyData?.company.company_title}
					</Text>
					<Text>
						<Text fw={700}> Telephone</Text>
						{companyData?.user.telephone}
					</Text>
					<Text>
						<Text fw={700}> Email</Text>
						{companyData?.user.email}
					</Text>
					<Text>
						<Text fw={700}> Address</Text>
						{companyData?.company.address}
					</Text>
					<Text>
						<Text fw={700}> Type</Text>
						{companyData?.company.type}
					</Text>
					<Text>
						<Text fw={700}> Contact Information</Text>
						{companyData?.company.contact_information}
					</Text>
					<Text>
						<Text fw={700}> Business Registration</Text>
						{companyData?.company.business_registration}
					</Text>
					<Card>
						<Stack>
							<Text>
								<Text fw={700}> Balance: </Text>
								{companyData?.company.balance} TL
							</Text>
							<Button onClick={open}>Withdraw Money</Button>
						</Stack>
					</Card>
				</Stack>
				</Card>
			</Center>
			<Modal opened={opened} onClose={close}>
				<WithdrawBalanceWithIBANForm />
			</Modal>
		</>
	);
};

export default CompanyProfilePage;
