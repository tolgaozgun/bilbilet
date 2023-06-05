import {
	Button,
	Card,
	Center,
	Container,
	Flex,
	Stack,
	Stepper,
	Title,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import {
	IconSquareRoundedArrowLeftFilled,
	IconSquareRoundedArrowRightFilled,
} from '@tabler/icons-react';
import { useQuery } from '@tanstack/react-query';
import { useState } from 'react';
import { useParams } from 'react-router-dom';
import ItemsNotFoundPage from '../../components/common/feedback/ItemsNotFoundPage';
import ConfirmBalancePurchase from '../../components/payment/ticket/ConfirmBalancePurchase';
import SelectPaymentOption from '../../components/payment/ticket/SelectPaymentOption';
import TicketInformation from '../../components/payment/ticket/TicketInformation';
import TravelerInformationForm from '../../components/payment/ticket/TravelerInformationForm';
import PayWithCreditCardForm from '../../components/payment/transaction/PayWithCreditCardForm';
import { useUser } from '../../hooks/auth';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import { getTicketById } from '../../services/payment/TicketService';
import { PaymentType } from '../../types/PaymentTypes';
import LoadingPage from '../LoadingPage';
import useTicketById from '../../hooks/tickets/useTickets';
import { convertDateToTime, formatDate, getTimeDifference } from '../../utils/utils';
import TicketInformationLimited from '../../components/payment/ticket/TicketInformationLimited';
import useDetailedTicketById from '../../hooks/tickets/useTicketsById';

const PurchaseTicketPage = () => {
	const { ticketId } = useParams();
	const axiosSecure = useAxiosSecure();
	const user = useUser();

	// Stepper
	const [active, setActive] = useState(0);
	const [selectedPaymentOption, setSelectedPaymentOption] = useState<PaymentType>(null);
	const nextStep = () => setActive((current) => (current < 3 ? current + 1 : current));
	const prevStep = () => setActive((current) => (current > 0 ? current - 1 : current));

	const {
		isLoading: isTicketLoading,
		isError: isTicketError,
		data: ticket,
	} = useQuery({
		queryKey: ['getTicket'],
		queryFn: () => getTicketById(axiosSecure, Number(ticketId)),
	});

	// Traveler information form
	const travelerInformationForm = useForm({
		initialValues: {
			name: user?.name || '',
			lastName: user?.surname || '',
			passportNumber: '',
			TCK: user?.TCK || '',
			email: user?.email || '',
			phone: user?.telephone || '',
		},
		validate: {
			name: (value) => (value === '' ? 'This field cannot be left empty' : null),
			lastName: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
			TCK: (value, values) =>
				value === '' && values.passportNumber === ''
					? 'This field cannot be left empty'
					: null,
			passportNumber: (value, values) =>
				value === '' && values.TCK === ''
					? 'This field cannot be left empty'
					: null,
			email: (value) => (value === '' ? 'This field cannot be left empty' : null),
			phone: (value) => (value === '' ? 'This field cannot be left empty' : null),
		},
	});

	const {
		isLoading: isTicketsLoading,
		isError: isTicketsError,
		data: ticketsData,
	} = useDetailedTicketById(axiosSecure, Number(ticketId));

	if (isTicketsLoading || !ticketsData || isTicketsError) {
		return <LoadingPage />;
	}

	if (isTicketLoading) {
		return <LoadingPage />;
	}

	if (isTicketError) {
		return <ItemsNotFoundPage />;
	}

	const depTimeDateObj = new Date(ticketsData?.data!.departureTime);
	const arrTimeDateObj = new Date(ticketsData?.data!.arrivalTime);

	const depTimeD = convertDateToTime(depTimeDateObj);
	const arrTimeD = convertDateToTime(arrTimeDateObj);
	const depDateD = formatDate(depTimeDateObj);
	const arrDateD = formatDate(arrTimeDateObj);
	const durationD = getTimeDifference(
		depTimeDateObj,
		arrTimeDateObj,
	);

	const price = ticket?.data!.totalPrice;

	const PaymentForm = () => {
		switch (selectedPaymentOption) {
			case 'balance':
				return (
					<>
						<Title order={2}>Confirm payment from balance</Title>
						<ConfirmBalancePurchase
							ticketId={ticket?.data!.ticketId}
							travelerId={user?.id!}
							price={price}
							pricePostfix="TL"
						/>
					</>
				);
			case 'credit-card':
				return (
					<Card withBorder shadow="md" p={24}>
						<Title order={2}>Enter credit card information</Title>
						<Flex align="center" justify="space-evenly">
							<PayWithCreditCardForm
								ticketId={ticket?.data!.ticketId}
								price={price}
							/>
						</Flex>
					</Card>
				);
			default:
				return null;
		}
	};

	const onConfirmInformations = () => {
		const validation = travelerInformationForm.validate();
		if (validation.hasErrors) {
			return;
		}

		nextStep();
	};

	// <TicketInformation 
	// 	ticket={ticket?.data!}
	// 	depTime = { depTimeD }
	// 	arrTime = { arrTimeD }
	// 	depDate = { depDateD }
	// 	arrDate = { arrDateD }
	// 	duration = { durationD }
	// />

	// <TicketInformationLimited ticket = {ticket?.data!}></TicketInformationLimited>

	return (
		<Center>
			<Stepper active={active} breakpoint="sm" p={18}>
				<Stepper.Step
					label="Confirm Information"
					description="Confirm the following info to continue"
				>
					<Stack spacing={36}>
						<Flex align="center" justify="space-evenly">
							<TicketInformation 
							 	ticket={ ticket?.data! }
							 	depTime = { depTimeD }
							 	arrTime = { arrTimeD }
							 	depDate = { depDateD }
							 	arrDate = { arrDateD }
							 	duration = { durationD }
							/>
							<TravelerInformationForm form={travelerInformationForm} />
						</Flex>
						<Container>
							<Button
								onClick={onConfirmInformations}
								color="green"
								size="lg"
								rightIcon={<IconSquareRoundedArrowRightFilled />}
							>
								Confirm Informations
							</Button>
						</Container>
					</Stack>
				</Stepper.Step>
				<Stepper.Step
					label="Payment option"
					description="Choose your payment option"
				>
					<Card shadow="lg" p={24}>
						<Stack spacing="lg">
							<Stack align="flex-start" spacing="lg">
								<Button
									variant="subtle"
									size="md"
									leftIcon={<IconSquareRoundedArrowLeftFilled />}
									onClick={prevStep}
								>
									Back
								</Button>
							</Stack>
							<Title order={2}>Select payment option</Title>
							<SelectPaymentOption
								setPaymentOption={setSelectedPaymentOption}
								nextStep={nextStep}
								price={price}
							/>
						</Stack>
					</Card>
				</Stepper.Step>
				<Stepper.Step
					label="Complete payment"
					description="Complete the payment process"
				>
					<Card p={24}>
						<Stack spacing="lg">
							<Stack align="flex-start" spacing="lg">
								<Button
									variant="subtle"
									size="md"
									leftIcon={<IconSquareRoundedArrowLeftFilled />}
									onClick={prevStep}
								>
									Back
								</Button>
							</Stack>
							<PaymentForm />
						</Stack>
					</Card>
				</Stepper.Step>
			</Stepper>
		</Center>
	);
};

export default PurchaseTicketPage;
