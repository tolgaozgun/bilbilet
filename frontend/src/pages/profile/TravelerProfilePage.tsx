import { Button, Card, Stack, Text, TextInput } from '@mantine/core';
import { useForm } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import UploadToBalanceWithCCForm from '../../components/payment/transaction/UploadToBalanceWithCCForm';
import { useUser } from '../../hooks/auth';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import useTraveler from '../../hooks/users/useTraveler';
import { TravelerInfo } from '../../types';
import { isErrorResponse } from '../../utils/utils';
import LoadingPage from '../LoadingPage';

const TravelerProfilePage = () => {
	const axiosSecure = useAxiosSecure();
	const user = useUser();
	const {
		isLoading,
		isError,
		data: travelerResponse,
	} = useTraveler(axiosSecure, user?.id!);

	if (isLoading) {
		return <LoadingPage />;
	}

	if (isError) {
		if (isErrorResponse<TravelerInfo>(travelerResponse)) {
			notifications.show({
				message: travelerResponse.msg,
			});
		}
		return <div></div>; // TODO: error page
	}

	const traveler = travelerResponse.data;
	const form = useForm({
		initialValues: {
			fullName: `${traveler?.user.name} + ${traveler?.user.surname}`,
			email: traveler?.user.email,
			telephone: traveler?.user.telephone,
			userType: traveler?.userType,
		},
		validate: {
			fullName: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
			email: (value) => (value === '' ? 'This field cannot be left empty' : null),
			telephone: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
			userType: (value) =>
				value === undefined ? 'This field cannot be left empty' : null,
		},
	});

	return (
		<Card>
			<Stack spacing={10}>
				<TextInput label="Full Name" {...form.getInputProps('fullName')} />
				<TextInput label="Email" {...form.getInputProps('email')} />
				<TextInput label="Phone" {...form.getInputProps('telephone')} />
				<TextInput label="User Type" {...form.getInputProps('userTypes')} />
				<Button>Update</Button>
				<Card>
					<Text>
						<Text fw={700}> Balance: </Text>
						{traveler?.traveler.balance}
					</Text>
					<UploadToBalanceWithCCForm />
				</Card>
			</Stack>
		</Card>
	);
};

export default TravelerProfilePage;
