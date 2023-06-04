import { Center, Stack, Title } from '@mantine/core';
import { IconArrowLeft } from '@tabler/icons-react';
import SubtleLinkButton from '../components/common/buttons/SubtleLinkButton';

const PageNotFound = () => {
	return (
		<Center h="60vh">
			<Stack align="center" spacing="lg">
				<Title>Page not found!</Title>
				<SubtleLinkButton leftIcon={<IconArrowLeft />} to="/search-fare">
					Back to home
				</SubtleLinkButton>
			</Stack>
		</Center>
	);
};

export default PageNotFound;
