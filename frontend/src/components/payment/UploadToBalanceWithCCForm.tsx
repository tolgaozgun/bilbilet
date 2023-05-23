import { Button, Flex, NumberInput, Stack, TextInput, Title } from '@mantine/core';
import { MonthPickerInput } from '@mantine/dates';
import { useForm } from '@mantine/form';

const UploadToBalanceWithCCForm = () => {
	const form = useForm({
		initialValues: {
			cardNumber: '',
			expiryDate: null,
			CVC: '',
			cardHolder: '',
			amount: 0,
		},
		validate: {
			cardNumber: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
			expiryDate: (value) => (!value ? 'This field cannot be left empty' : null),
			CVC: (value) => (value === '' ? 'This field cannot be left empty' : null),
			cardHolder: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
			amount: (value) =>
				value <= 0 ? 'A negative amount is not possible to upload.' : null,
		},
	});

	const onTransfer = () => {
		// TODO: Upload money to the balance of the user
	};

	return (
		<form>
			<Stack spacing="xl">
				<Title>Transfer money to your balance</Title>
				<Stack spacing="md">
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
						<NumberInput
							label="CVC"
							placeholder="CVC"
							parser={(value) => value.replace(/\$\s?|(,*)/g, '')}
							min={100}
							max={9999}
						/>
					</Flex>
					<NumberInput
						label="Amount"
						parser={(value) => value.replace(/\$\s?|(,*)/g, '')}
						formatter={(value) =>
							!Number.isNaN(parseFloat(value))
								? `TL ${value}`.replace(
										/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,
										',',
								  )
								: 'TL '
						}
					/>
					<Button onClick={onTransfer}>Transfer to your balance</Button>
				</Stack>
			</Stack>
		</form>
	);
};

export default UploadToBalanceWithCCForm;
