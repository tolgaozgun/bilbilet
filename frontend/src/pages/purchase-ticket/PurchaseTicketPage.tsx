import {
	Button,
	Card,
	Center,
	Container,
	Flex,
	Group,
	Stack,
	Stepper,
	Title,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import {
	IconArrowLeft,
	IconArrowLeftSquare,
	IconSquareRoundedArrowLeftFilled,
	IconSquareRoundedArrowRightFilled,
} from '@tabler/icons-react';
import { useState } from 'react';
import ConfirmBalancePurchase from '../../components/payment/ticket/ConfirmBalancePurchase';
import SelectPaymentOption from '../../components/payment/ticket/SelectPaymentOption';
import TicketInformation from '../../components/payment/ticket/TicketInformation';
import TravelerInformationForm from '../../components/payment/ticket/TravelerInformationForm';
import PayWithCreditCardForm from '../../components/payment/transaction/PayWithCreditCardForm';
// import { useUser } from '../../hooks/useUser';
import { PaymentType } from '../../types/PaymentTypes';
import { useUser } from '../../hooks/auth';

const PurchaseTicketPage = () => {
	// Stepper
	const [active, setActive] = useState(0);
	const [selectedPaymentOption, setSelectedPaymentOption] = useState<PaymentType>(null);
	const nextStep = () => setActive((current) => (current < 3 ? current + 1 : current));
	const prevStep = () => setActive((current) => (current > 0 ? current - 1 : current));

	// Traveler information form
	const user = useUser();
	const travelerInformationForm = useForm({
		initialValues: {
			name: user?.name || '',
			lastName: user?.surname || '',
			passportNumber: '',
			TCK: user?.TCK || '',
			email: user?.email || '',
			phone: user?.phone || '',
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

	// TODO: Fetch ticket price using ticket id from backend, create hook
	// useTicket that will fetch ticket info

	const PaymentForm = () => {
		switch (selectedPaymentOption) {
			case 'balance':
				return <ConfirmBalancePurchase price={100} pricePostfix="TL" />;
			case 'credit-card':
				return (
					<Card withBorder shadow="md" p={24}>
						<Flex align="center" justify="space-evenly">
							<PayWithCreditCardForm price={100} />
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

	const price = 100;
	return (
		<Center>
			<Stepper active={active} breakpoint="sm" p={18}>
				<Stepper.Step
					label="Confirm Information"
					description="Confirm the following info to continue"
				>
					<Stack spacing={36}>
						<Flex align="center" justify="space-evenly">
							<TicketInformation />
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
							<Title order={2}>Enter credit card information</Title>
							<PaymentForm />
						</Stack>
					</Card>
				</Stepper.Step>
			</Stepper>
		</Center>
	);
};

export default PurchaseTicketPage;
