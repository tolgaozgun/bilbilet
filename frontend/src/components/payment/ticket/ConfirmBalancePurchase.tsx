import { Button, Card, Center, Container, Loader, Modal, Stack } from '@mantine/core';
import { useDisclosure } from '@mantine/hooks';
import MoneyNumberInput from '../../common/inputs/MoneyNumberInput';
import UploadToBalanceWithCCForm from '../transaction/UploadToBalanceWithCCForm';

interface BuyTicketFormProps {
	price: number;
	pricePostfix: string;
	pricePrefix?: string;
}

const ConfirmBalancePurchase = ({
	price,
	pricePostfix = 'TL',
	pricePrefix,
}: BuyTicketFormProps) => {
	const [opened, { open, close }] = useDisclosure(false);

	// TODO: Fetch user balance from backend, create hook useBalance
	const onBalancePurchase = () => {};
	return (
		<>
			<Center>
				<Card maw={400} withBorder>
					<Stack>
						{Number.isNaN(price) ? (
							<Loader />
						) : (
							<MoneyNumberInput
								amount={price}
								disabled={true}
								prefix="TL"
							/>
						)}

						<Button size="lg">
							Pay {pricePrefix} {price} {pricePostfix} From Balance
						</Button>
						<Button onClick={open} size="lg" variant="outline">
							Transfer Money to Your Balance
						</Button>
					</Stack>
				</Card>
			</Center>
			<Modal opened={opened} onClose={close}>
				<Container p={18}>
					<UploadToBalanceWithCCForm />
				</Container>
			</Modal>
		</>
	);
};

export default ConfirmBalancePurchase;
