import { Flex, Header, Title } from '@mantine/core';
import ProfileMenu from './menus/ProfileMenu';

const AppHeader = () => {
	return (
		<Header w="100%" height={100} p="lg">
			<Flex align="center" justify="space-between">
				<Title color="blue" ml={20}>
					BilBilet
				</Title>
				<ProfileMenu />
			</Flex>
		</Header>
	);
};

export default AppHeader;
