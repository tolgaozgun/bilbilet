import { Button, Card, Divider, Flex, Loader, Stack, Title } from '@mantine/core';
import { notifications } from '@mantine/notifications';
import { IconBrandCashapp, IconCreditCard } from '@tabler/icons-react';
import { useUser } from '../../../hooks/auth';
import useAxiosSecure from '../../../hooks/auth/useAxiosSecure';
import useTraveler from '../../../hooks/users/useTraveler';
import LoadingPage from '../../../pages/LoadingPage';
import { TravelerInfo } from '../../../types';
import { PaymentType } from '../../../types/PaymentTypes';
import { isErrorResponse } from '../../../utils/utils';
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
	const axiosSecure = useAxiosSecure();
	const user = useUser();
	const {
		isLoading,
		isError,
		data: travelerResponse,
	} = useTraveler(axiosSecure, user?.id!);

	const onSelectBalance = () => {
		setPaymentOption('balance');
		nextStep();
	};

	const onSelectCreditCard = () => {
		setPaymentOption('credit-card');
		nextStep();
	};

	if (isLoading) {
		return <LoadingPage />;
	}

	if (isError) {
		if (isErrorResponse<TravelerInfo>(travelerResponse)) {
			notifications.show({
				message: travelerResponse.msg,
			});
		}
		return <div></div>; // TODO: error page
	}
	const travelerData = travelerResponse.data;

	return (
		<Card withBorder>
			<Flex align="center" justify="space-evenly">
				<Stack>
					{isLoading ? (
						<Loader />
					) : (
						<MoneyNumberInput
							amount={travelerData?.traveler.balance || 0}
							disabled={true}
							prefix="TL"
						/>
					)}
					<Button
						size="lg"
						onClick={onSelectBalance}
						leftIcon={<IconBrandCashapp />}
					>
						Pay {pricePrefix} {price} {pricePostfix} From Balance
					</Button>
				</Stack>
				<Divider orientation="vertical" />
				<Button
					onClick={onSelectCreditCard}
					leftIcon={<IconCreditCard />}
					variant="light"
					size="lg"
				>
					Pay {pricePrefix} {price} {pricePostfix} with Credit Card
				</Button>
			</Flex>
		</Card>
	);
};

export default SelectPaymentOption;
