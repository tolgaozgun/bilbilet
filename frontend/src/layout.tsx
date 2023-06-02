import { AppShell } from '@mantine/core';
import { Outlet } from 'react-router-dom';
import AppHeader from './components/common/other/AppHeader';

const Layout = () => {
	return (
		<main className="App">
			<AppShell
				padding="md"
				header={<AppHeader />}
				styles={(theme) => ({
					main: {
						backgroundColor:
							theme.colorScheme === 'dark'
								? theme.colors.dark[8]
								: theme.colors.gray[0],
					},
				})}
			>
				<Outlet />
			</AppShell>
		</main>
	);
};

export default Layout;
