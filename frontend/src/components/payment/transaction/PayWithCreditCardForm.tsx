import { Button, Flex, Group, NumberInput, Stack, TextInput } from '@mantine/core';
import { MonthPickerInput } from '@mantine/dates';
import { useForm } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { IconArrowLeft } from '@tabler/icons-react';
import { useMutation } from '@tanstack/react-query';
import { useNavigate } from 'react-router-dom';
import { useUser } from '../../../hooks/auth';
import useAxiosSecure from '../../../hooks/auth/useAxiosSecure';
import { payTicketPriceWithCC } from '../../../services/payment';
import { PaymentWithCCRequest } from '../../../types/PaymentTypes';

interface PayWithCreditCardFormProps {
	price: number;
	ticketId: number;
}

const PayWithCreditCardForm = ({ price, ticketId }: PayWithCreditCardFormProps) => {
	const axiosSecure = useAxiosSecure();
	const user = useUser();
	const navigate = useNavigate();
	const form = useForm({
		initialValues: {
			cardNumber: null,
			expiryDate: null,
			CVC: 100,
			cardHolder: `${user?.name} ${user?.surname}`,
		},
		validate: {
			cardNumber: (value) =>
				Number.isNaN(value) ? 'This field cannot be left empty' : null,
			expiryDate: (value) => (!value ? 'This field cannot be left empty' : null),
			CVC: (value) =>
				Number.isNaN(value) ? 'This field cannot be left empty' : null,
			cardHolder: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
		},
	});

	const {
		isLoading: isPaymentLoading,
		isError: isPaymentError,
		mutate: payTicketPrice,
	} = useMutation({
		mutationKey: ['payWithCC'],
		mutationFn: (paymentDetails: PaymentWithCCRequest) => {
			return payTicketPriceWithCC(axiosSecure, paymentDetails);
		},
		onSuccess: () => {
			notifications.show({
				color: 'green',
				message: 'Ticket purchase successful!',
			});
			navigate('/purchase-successful');
		},
	});

	const onTransfer = () => {
		const validate = form.validate();
		if (validate.hasErrors) {
			return;
		}
		const paymentDetails: PaymentWithCCRequest = {
			ticketId: ticketId,
			amount: price,
			creditCard: {
				cardNumber: form.values.cardNumber || 0,
				cardHolderName: form.values.cardHolder,
				expirationMonth: (form.values.expiryDate! as Date)?.getMonth() || 1,
				expirationYear: (form.values.expiryDate! as Date)?.getFullYear() || 1,
				cvv: form.values.CVC,
			},
			travelerId: user?.id!,
		};
		payTicketPrice(paymentDetails);
	};

	if (isPaymentError) {
		navigate('/purchase-failed');
	}

	return (
		<Group>
			<form>
				<Stack>
					<TextInput
						label="Card holder"
						placeholder="John Doe"
						{...form.getInputProps('cardHolder')}
					/>
					<TextInput
						label="Card number"
						placeholder="XXXX XXXX XXXX XXXX"
						{...form.getInputProps('cardNumber')}
					/>
					<Flex direction="column">
						<MonthPickerInput
							label="Expiry date"
							placeholder="Pick a date"
							{...form.getInputProps('expiryDate')}
						/>
						<NumberInput label="CVC" placeholder="CVC" min={100} max={9999} />
					</Flex>
					<Button loading={isPaymentLoading} onClick={onTransfer}>
						Pay {price}
					</Button>
				</Stack>
			</form>
		</Group>
	);
};

export default PayWithCreditCardForm;
