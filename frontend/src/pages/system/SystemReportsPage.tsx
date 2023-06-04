import { Center, Flex, Title, Table, Stack, Button, Tabs, Card } from '@mantine/core';
import { IconBuilding, IconCar, IconLocation, IconMoneybag, IconPigMoney, IconPlane, IconTicket } from '@tabler/icons-react';

const SystemReportsPage = () => {	
	return (
		<Center miw={400}>
			<Tabs defaultValue="mostRentedCars">
				<Tabs.List>
					<Tabs.Tab icon={<IconCar size="1rem" />} value="mostRentedCars">
						Most Rented Cars
					</Tabs.Tab>
					<Tabs.Tab icon={<IconTicket size="1rem" />} value="companyWithMostPurchasedTickets">
						Companies With Most Purchased Tickets
					</Tabs.Tab>
					<Tabs.Tab icon={<IconLocation size="1rem" />} value="mostPurchasedArrival">
						Most Purchased Arrival
					</Tabs.Tab>
					<Tabs.Tab icon={<IconLocation size="1rem" />} value="mostPurchasedDestination">
						Most Purchased Destination
					</Tabs.Tab>
					<Tabs.Tab icon={<IconMoneybag size="1rem" />} value="mostExpensiveTicketOfCompany">
						Most Expensive Ticket Of Companies
					</Tabs.Tab>
					<Tabs.Tab icon={<IconPigMoney size="1rem" />} value="cheapestTicketOfCompany">
						Cheapest Ticket Of Companies
					</Tabs.Tab>
				</Tabs.List>

				<Tabs.Panel value="mostRentedCars" pt="md">
					<Flex direction="column" gap="md">
						<Title>Most Rented Cars</Title>
					</Flex>
				</Tabs.Panel>

				<Tabs.Panel value="companyWithMostPurchasedTickets" pt="md">
					<Flex direction="column" gap="md">
						<Title>Companies With Most Purchased Tickets</Title>
					</Flex>
				</Tabs.Panel>

				<Tabs.Panel value="mostPurchasedArrival" pt="md">
					<Flex direction="column" gap="md">
						<Title>Most Purchased Arrival</Title>
					</Flex>
				</Tabs.Panel>

				<Tabs.Panel value="mostPurchasedDestination" pt="md">
					<Flex direction="column" gap="md">
						<Title>Most Purchased Destination</Title>
					</Flex>
				</Tabs.Panel>

				<Tabs.Panel value="mostExpensiveTicketOfCompany" pt="md">
					<Flex direction="column" gap="md">
						<Title>Most Expensive Ticket Of Companies</Title>
					</Flex>
				</Tabs.Panel>

				<Tabs.Panel value="cheapestTicketOfCompany" pt="md">
					<Flex direction="column" gap="md">
						<Title>Cheapest Ticket Of Companies</Title>
					</Flex>
				</Tabs.Panel>
			</Tabs>
		</Center>
	);
};

export default SystemReportsPage;
