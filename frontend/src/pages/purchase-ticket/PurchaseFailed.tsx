import { Button, Center, Container, Image, Stack, Text, Title } from '@mantine/core';
import { IconArrowLeft } from '@tabler/icons-react';
import SubtleLinkButton from '../../components/common/buttons/SubtleLinkButton';

interface PurchaseFailedProps {
	message?: string;
	ticketId?: number;
}

const PurchaseFailed = ({ message, ticketId }: PurchaseFailedProps) => {
	return (
		<Center h="90%">
			<Stack align="center" maw={450} spacing="xl">
				<Stack spacing="xs">
					<Image
						maw={256}
						mx="auto"
						withPlaceholder
						fit="contain"
						src="./img/payment-failed.png"
					/>
					<Title color="red">Payment failed!</Title>
				</Stack>
				<Text>
					Something went wrong during the payment process. Please try again and
					make sure you have the right amount of money.
				</Text>
				<SubtleLinkButton to="/fare/:id">
					<IconArrowLeft /> Go back to purchasing page
				</SubtleLinkButton>
			</Stack>
		</Center>
	);
};

export default PurchaseFailed;
