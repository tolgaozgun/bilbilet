import { Button, Group, NumberInput, Stack, TextInput, Title } from '@mantine/core';
import { MonthPickerInput } from '@mantine/dates';
import { useForm } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import { useState } from 'react';
import { useUser } from '../../../hooks/auth';
import useAxiosSecure from '../../../hooks/auth/useAxiosSecure';
import { uploadMoneyToBalance } from '../../../services/payment';
import { PaymentWithCCRequest } from '../../../types/PaymentTypes';
import MoneyNumberInput from '../../common/inputs/MoneyNumberInput';

const UploadToBalanceWithCCForm = () => {
	const axiosSecure = useAxiosSecure();
	const user = useUser();
	const queryClient = useQueryClient();
	const [amount, setAmount] = useState(0); // Amount to upload
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

	// Test CC Number = 5425233430109903

	const {
		isLoading: isLoadingBalance,
		isError,
		mutate: uploadToBalance,
		data: transaction,
	} = useMutation({
		mutationKey: ['uploadToBalance'],
		mutationFn: (uploadDetails: PaymentWithCCRequest) => {
			return uploadMoneyToBalance(axiosSecure, uploadDetails);
		},
		onSuccess: () => {
			notifications.show({
				message: 'Successfully uploaded money to your balance',
			});
		},
	});

	const onTransfer = () => {
		const validate = form.validate();
		if (validate.hasErrors) {
			return;
		}

		const uploadDetails: PaymentWithCCRequest = {
			amount: amount,
			creditCard: {
				cardNumber: form.values.cardNumber || 0,
				cardHolderName: form.values.cardHolder,
				expirationMonth: (form.values.expiryDate! as Date)?.getMonth() || 1,
				expirationYear: (form.values.expiryDate! as Date)?.getFullYear() || 1,
				cvv: form.values.CVC,
			},
			travelerId: user?.id!,
		};
		console.log(uploadDetails);
		uploadToBalance(uploadDetails);
		queryClient.invalidateQueries(['getTravelerInfo']);
		form.reset();
	};

	return (
		<form>
			<Stack spacing="xl">
				<Title order={2}>Transfer money to your balance</Title>
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
					<Group>
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
							{...form.getInputProps('CVC')}
						/>
					</Group>
					<MoneyNumberInput amount={amount} setAmount={setAmount} />
					<Button loading={isLoadingBalance} onClick={onTransfer}>
						Transfer to your balance
					</Button>
				</Stack>
			</Stack>
		</form>
	);
};

export default UploadToBalanceWithCCForm;
