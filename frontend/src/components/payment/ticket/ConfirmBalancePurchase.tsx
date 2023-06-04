import { Button, Card, Center, Container, Loader, Modal, Stack } from '@mantine/core';
import { useDisclosure } from '@mantine/hooks';
import { IconCheck } from '@tabler/icons-react';
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
				<Card miw={400} withBorder>
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

						<Button leftIcon={<IconCheck />} color="green" size="lg">
							Confirm Paying {pricePrefix} {price} {pricePostfix} From
							Balance
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
