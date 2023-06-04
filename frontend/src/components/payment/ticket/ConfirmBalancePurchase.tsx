import { Button, Card, Center, Container, Loader, Modal, Stack } from '@mantine/core';
import { useDisclosure } from '@mantine/hooks';
import { notifications } from '@mantine/notifications';
import { IconCheck } from '@tabler/icons-react';
import { useMutation } from '@tanstack/react-query';
import useAxiosSecure from '../../../hooks/auth/useAxiosSecure';
import { payTicketWithBalance } from '../../../services/payment';
import { PaymentWithBalanceRequest } from '../../../types/PaymentTypes';
import { isErrorResponse } from '../../../utils/utils';
import MoneyNumberInput from '../../common/inputs/MoneyNumberInput';
import UploadToBalanceWithCCForm from '../transaction/UploadToBalanceWithCCForm';

interface BuyTicketFormProps {
	price: number;
	pricePostfix: string;
	pricePrefix?: string;
	ticketId: number;
	travelerId: number;
}

const ConfirmBalancePurchase = ({
	price,
	pricePostfix = 'TL',
	pricePrefix,
	ticketId,
	travelerId,
}: BuyTicketFormProps) => {
	const [opened, { open, close }] = useDisclosure(false);
	const axiosSecure = useAxiosSecure();

	const { isLoading, mutateAsync: payWithBalance } = useMutation({
		mutationFn: (paymentDetails: PaymentWithBalanceRequest) =>
			payTicketWithBalance(axiosSecure, paymentDetails),
	});
	const onBalancePurchase = async () => {
		const paymentDetails: PaymentWithBalanceRequest = {
			amount: price,
			ticketId: ticketId,
			travelerId: travelerId,
		};
		const result = await payWithBalance(paymentDetails);
		if (isErrorResponse(result)) {
			notifications.show({
				message: result.msg,
				color: 'red',
			});
		}
	};
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

						<Button
							onClick={onBalancePurchase}
							leftIcon={<IconCheck />}
							color="green"
							size="lg"
						>
							Confirm Paying {pricePrefix} {price} {pricePostfix} From
							Balance
						</Button>
						<Button
							loading={isLoading}
							onClick={open}
							size="lg"
							variant="outline"
						>
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
