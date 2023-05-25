import { Button, Flex, NumberInput, Stack, TextInput } from '@mantine/core';
import { MonthPickerInput } from '@mantine/dates';
import { useForm } from '@mantine/form';

interface PayWithCreditCardFormProps {
	price: number;
}

const PayWithCreditCardForm = ({ price }: PayWithCreditCardFormProps) => {
	const form = useForm({
		initialValues: {
			cardNumber: '',
			expiryDate: null,
			CVC: '',
			cardHolder: '',
		},
		validate: {
			cardNumber: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
			expiryDate: (value) => (!value ? 'This field cannot be left empty' : null),
			CVC: (value) => (value === '' ? 'This field cannot be left empty' : null),
			cardHolder: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
		},
	});
	const amount = 5;

	const onTransfer = () => {
		// TODO: Pay the amount, make the request using fareId to ensure safety
	};

	return (
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
				<Button onClick={onTransfer}>Pay {amount}</Button>
			</Stack>
		</form>
	);
};

export default PayWithCreditCardForm;
