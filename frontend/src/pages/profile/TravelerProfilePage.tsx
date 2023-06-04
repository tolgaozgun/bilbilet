import {
	Button,
	Card,
	Center,
	Divider,
	Modal,
	Stack,
	Text,
	TextInput,
	Title,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import { useDisclosure } from '@mantine/hooks';
import { notifications } from '@mantine/notifications';
import UploadToBalanceWithCCForm from '../../components/payment/transaction/UploadToBalanceWithCCForm';
import { useUser } from '../../hooks/auth';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import useTraveler from '../../hooks/users/useTraveler';
import { TravelerInfo } from '../../types';
import { isErrorResponse } from '../../utils/utils';
import LoadingPage from '../LoadingPage';

const TravelerProfilePage = () => {
	const [opened, { open, close }] = useDisclosure(false);
	const axiosSecure = useAxiosSecure();
	const user = useUser();
	const {
		isLoading,
		isError,
		data: travelerResponse,
	} = useTraveler(axiosSecure, user?.id!);
	const traveler = travelerResponse?.data;

	if (isLoading) {
		return <LoadingPage />;
	}

	if (isError) {
		if (!travelerResponse) {
			notifications.show({
				message:
					"We couldn't receive a response from the servers. Please try again.",
			});
		} else if (isErrorResponse<TravelerInfo>(travelerResponse)) {
			notifications.show({
				message: travelerResponse.msg,
			});
		}
		return <div></div>; // TODO: error page
	}

	return (
		<>
			<Center>
				<Card>
					<Title order={3}>Profile details</Title>
					<Stack spacing="lg">
						<Text>
							<Text fw={700}> Full Name: </Text>
							{`${traveler?.user.name} ${traveler?.user.surname}`}
						</Text>
						<Text>
							<Text fw={700}> Email: </Text>
							{traveler?.user.email}
						</Text>
						<Text>
							<Text fw={700}> Phone: </Text>
							{traveler?.user.telephone}
						</Text>
						<Divider />
						<Title order={3}>Balance Details</Title>
						<Card>
							<Stack>
								<Text>
									<Text fw={700}> Balance: </Text>
									{traveler?.traveler.balance} TL
								</Text>
								<Button onClick={open}>
									Upload money to your balance
								</Button>
							</Stack>
						</Card>
					</Stack>
				</Card>
			</Center>
			<Modal opened={opened} onClose={close}>
				<UploadToBalanceWithCCForm />
			</Modal>
		</>
	);
};

export default TravelerProfilePage;
