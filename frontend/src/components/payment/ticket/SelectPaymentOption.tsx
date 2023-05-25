import { Button, Card, Flex, Loader, Stack } from '@mantine/core';
import { IconBrandCashapp, IconCreditCard } from '@tabler/icons-react';
import { PaymentType } from '../../../types/PaymentTypes';
import MoneyNumberInput from '../../common/inputs/MoneyNumberInput';

interface SelectPaymentOptionProps {
	price: number;
	setPaymentOption: (option: PaymentType) => void;
	nextStep: () => void;
	pricePostfix?: string;
	pricePrefix?: string;
}

const SelectPaymentOption = ({
	price,
	setPaymentOption,
	nextStep,
	pricePostfix = 'TL',
	pricePrefix,
}: SelectPaymentOptionProps) => {
	// TODO: Fetch user balance from backend, create hook useBalance

	const onSelectBalance = () => {
		setPaymentOption('balance');
		nextStep();
	};

	const onSelectCreditCard = () => {
		setPaymentOption('credit-card');
		nextStep();
	};

	const amount = 5;
	return (
		<Card withBorder>
			<Flex align="center" justify="space-evenly">
				<Stack>
					{Number.isNaN(amount) ? (
						<Loader />
					) : (
						<MoneyNumberInput amount={amount} disabled={true} prefix="TL" />
					)}

					<Button onClick={onSelectBalance} leftIcon={<IconBrandCashapp />}>
						Pay {pricePrefix} {price} {pricePostfix} From Balance
					</Button>
				</Stack>
				<Button
					onClick={onSelectCreditCard}
					leftIcon={<IconCreditCard />}
					variant="light"
				>
					Pay {pricePrefix} {price} {pricePostfix} with Credit Card
				</Button>
			</Flex>
		</Card>
	);
};

export default SelectPaymentOption;
