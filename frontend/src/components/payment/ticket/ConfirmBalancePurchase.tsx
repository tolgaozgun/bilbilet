import { Button, Card, Loader, Stack } from '@mantine/core';
import MoneyNumberInput from '../../common/inputs/MoneyNumberInput';

interface BuyTicketFormProps {
	price: number;
	pricePrefix?: string;
}

const ConfirmBalancePurchase = ({ price, pricePrefix = 'TL' }: BuyTicketFormProps) => {
	// TODO: Fetch user balance from backend, create hook useBalance
	const amount = 5;
	return (
		<Card withBorder>
			<Stack>
				{amount ? (
					<Loader />
				) : (
					<MoneyNumberInput amount={amount} disabled={true} prefix="TL" />
				)}

				<Button>
					Pay {pricePrefix} {price} From Balance
				</Button>
				<Button variant="outline">Transfer Money to Your Balance</Button>
			</Stack>
		</Card>
	);
};

export default ConfirmBalancePurchase;
