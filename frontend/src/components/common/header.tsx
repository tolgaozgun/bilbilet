import { Button, Container, Flex, Group, Header, Title } from '@mantine/core';
import UserButton from './buttons/UserButton';
import UserMenu from './menus/UserMenu';

const AppHeader = () => {
	return (
		<Header w="100%" height={100} p="lg">
			<Flex align="center" justify="space-between">
				<Title color="blue" ml={20}>
					BilBilet
				</Title>
				<UserMenu />
			</Flex>
		</Header>
	);
};

export default AppHeader;
