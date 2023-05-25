import { Button, Card, Center, Group, Stack, Stepper, Title } from '@mantine/core';
import { useState } from 'react';
import ConfirmBalancePurchase from '../../components/payment/ticket/ConfirmBalancePurchase';
import {
	default as BuyTicketForm,
	default as SelectPaymentOption,
} from '../../components/payment/ticket/SelectPaymentOption';
import TicketInformation from '../../components/payment/ticket/TicketInformation';
import TravelerInformationForm from '../../components/payment/ticket/TravelerInformationForm';
import PayWithCreditCardForm from '../../components/payment/transaction/PayWithCreditCardForm';
import { PaymentType } from '../../types/PaymentTypes';

const PurchaseTicketPage = () => {
	const [active, setActive] = useState(0);
	const [selectedPaymentOption, setSelectedPaymentOption] = useState<PaymentType>(null);
	const nextStep = () => setActive((current) => (current < 3 ? current + 1 : current));
	const prevStep = () => setActive((current) => (current > 0 ? current - 1 : current));

	// TODO: Fetch ticket price using ticket id from backend, create hook
	// useTicket that will fetch ticket info

	const PaymentForm = () => {
		switch (selectedPaymentOption) {
			case 'balance':
				return <ConfirmBalancePurchase price={100} />;
			case 'credit-card':
				return <PayWithCreditCardForm price={100} />;
			default:
				return null;
		}
	};

	const price = 100;
	return (
		<Center>
			<Stepper active={active} onStepClick={setActive} breakpoint="sm">
				<Stepper.Step
					label="Information"
					description="Confirm the info to continue to next step"
				>
					<Stack>
						<Group>
							<TicketInformation />
							<TravelerInformationForm />
						</Group>
						<Button onClick={nextStep} color="green">
							Confirm
						</Button>
					</Stack>
				</Stepper.Step>
				<Stepper.Step
					label="Payment option"
					description="Choose your payment option"
				>
					<Card>
						<Stack>
							<Title order={2}>Select payment option</Title>
							<SelectPaymentOption price={price} />
						</Stack>
					</Card>
				</Stepper.Step>
				<Stepper.Step
					label="Complete payment"
					description="Complete the payment process"
				>
					<PaymentForm />
				</Stepper.Step>
			</Stepper>
		</Center>
	);
};

export default PurchaseTicketPage;
