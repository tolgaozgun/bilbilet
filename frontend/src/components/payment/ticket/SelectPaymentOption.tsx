import { Button, Card, Divider, Flex, Loader, NumberInput, Stack } from '@mantine/core';
import { IconBrandCashapp, IconCreditCard } from '@tabler/icons-react';
import MoneyNumberInput from '../../common/inputs/MoneyNumberInput';

interface BuyTicketFormProps {
	price: number;
	pricePostfix?: string;
	pricePrefix?: string;
}

const SelectPaymentOption = ({
	price,
	pricePostfix = 'TL',
	pricePrefix,
}: BuyTicketFormProps) => {
	// TODO: Fetch user balance from backend, create hook useBalance
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

					<Button leftIcon={<IconBrandCashapp />}>
						Pay {pricePrefix} {price} {pricePostfix} From Balance
					</Button>
				</Stack>
				<Button leftIcon={<IconCreditCard />} variant="light">
					Pay {pricePrefix} {price} {pricePostfix} with Credit Card
				</Button>
			</Flex>
		</Card>
	);
};

export default SelectPaymentOption;
