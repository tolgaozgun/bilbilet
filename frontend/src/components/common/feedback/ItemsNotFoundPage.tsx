import { Center, Stack, Title } from '@mantine/core';
import { IconExclamationCircle } from '@tabler/icons-react';

const ItemsNotFoundPage = () => {
	return (
		<Center miw={350}>
			<Stack align="center">
				<Title color="red">No items found</Title>
				<IconExclamationCircle color="red" size={'3rem'} />
			</Stack>
		</Center>
	);
};

export default ItemsNotFoundPage;
