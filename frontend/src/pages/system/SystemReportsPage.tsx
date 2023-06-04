import { Center, Flex, Title, Table, Stack, Button } from '@mantine/core';

const SystemReportsPage = () => {	
	return (
		<Center>
			<Flex direction={'column'} gap={'sm'}>
				<Title>System Reports</Title>
				<Stack h={700} sx={(theme) => ({ backgroundColor: theme.colorScheme === 'dark' ? theme.colors.dark[8] : theme.colors.gray[0] })}>
					<Flex direction={'row'} gap={'sm'}>
						<>Hi</>
						<>There</>
					</Flex>
					<Button variant="outline">2</Button>
					<Button variant="outline">3</Button>
				</Stack>
			</Flex>
		</Center>
	);
};

export default SystemReportsPage;
