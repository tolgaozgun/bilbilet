import { Center, Tabs } from '@mantine/core';
import { IconBus, IconUser } from '@tabler/icons-react';
import RegisterCompanyForm from '../../components/login/RegisterCompanyForm';
import RegisterUserForm from '../../components/login/RegisterUserForm';

const RegisterPage = () => {
	return (
		<Center miw={400}>
			<Tabs defaultValue="user">
				<Tabs.List>
					<Tabs.Tab icon={<IconUser size="1rem" />} value="user">
						User
					</Tabs.Tab>
					<Tabs.Tab icon={<IconBus size="1rem" />} value="company">
						Company
					</Tabs.Tab>
				</Tabs.List>

				<Tabs.Panel value="user" pt="md">
					<RegisterUserForm />
				</Tabs.Panel>

				<Tabs.Panel value="company" pt="md">
					<RegisterCompanyForm />
				</Tabs.Panel>
			</Tabs>
		</Center>
	);
};

export default RegisterPage;
