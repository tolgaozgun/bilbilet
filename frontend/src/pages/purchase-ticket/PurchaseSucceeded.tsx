import {
	Button,
	Center,
	Container,
	Image,
	Stack,
	Text,
	ThemeIcon,
	Title,
} from '@mantine/core';
import { IconArrowLeft, IconDownload } from '@tabler/icons-react';
import SubtleLinkButton from '../../components/common/buttons/SubtleLinkButton';

interface PurchaseSucceededProps {
	message?: string;
	ticketId?: number;
	ticketDetails: null;
}

const PurchaseSucceeded = ({
	message,
	ticketId,
	ticketDetails,
}: PurchaseSucceededProps) => {
	return (
		<Center h="90%">
			<Stack align="center" maw={450} spacing="xl">
				<Stack spacing="xs">
					<Image
						maw={256}
						mx="auto"
						withPlaceholder
						fit="contain"
						src="./img/payment-succeeded.png"
					/>
					<Container>
						<Title color="green">Payment successfully completed!</Title>
					</Container>
				</Stack>
				<Text>
					We have sent to your email and phone number a copy of the ticket, but
					you can still download the ticket if you want.
				</Text>
				<Button leftIcon={<IconDownload />}>Download Ticket</Button>
				<SubtleLinkButton to="/travel-list">
					<IconArrowLeft /> Go to your travel list
				</SubtleLinkButton>
			</Stack>
		</Center>
	);
};

export default PurchaseSucceeded;
