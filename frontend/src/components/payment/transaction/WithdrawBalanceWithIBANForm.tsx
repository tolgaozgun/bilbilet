import { Button, Group, NumberInput, Stack, TextInput, Title } from '@mantine/core';
import { MonthPickerInput } from '@mantine/dates';
import { useForm } from '@mantine/form';
import { useState } from 'react';
import MoneyNumberInput from '../../common/inputs/MoneyNumberInput';

const WithdrawBalanceWithIBANForm = () => {
	const [amount, setAmount] = useState(0);
	const form = useForm({
		initialValues: {
			ibanNumber: '',
		},
		validate: {
			ibanNumber: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
		},
	});

	const onTransfer = () => {
		// TODO: Send endpoint request
	};

	return (
		<form>
			<Stack spacing="xl">
				<Title order={2}>Withdraw Money</Title>
				<Stack spacing="md">
					<TextInput
						label="IBAN Number"
						placeholder="TRXX XXXX XXXX XXXX XXXX XXXX XX"
						{...form.getInputProps('ibanNumber')}
					/>
					<MoneyNumberInput amount={amount} setAmount={setAmount} />
					<Button onClick={onTransfer}>Withdraw money from your balance</Button>
				</Stack>
			</Stack>
		</form>
	);
};

export default WithdrawBalanceWithIBANForm;
