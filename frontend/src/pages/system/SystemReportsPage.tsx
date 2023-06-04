import { Center, Flex, Title, Table, Stack, Button, Tabs, Card } from '@mantine/core';
import { IconBuilding, IconCar, IconLocation, IconMoneybag, IconPigMoney, IconPlane, IconTicket } from '@tabler/icons-react';

const SystemReportsPage = () => {	
	return (
		<Center miw={400}>
			<Tabs defaultValue="mostRentedCars">
				<Tabs.List>
					<Tabs.Tab icon={<IconCar size="1rem" />} value="mostRentedCars">
						Most Popular Rental Cars
					</Tabs.Tab>
					<Tabs.Tab icon={<IconTicket size="1rem" />} value="companyWithMostPurchasedTickets">
						Most Popular Companies
					</Tabs.Tab>
					<Tabs.Tab icon={<IconLocation size="1rem" />} value="mostPurchasedArrival">
						Most Popular Arrivals
					</Tabs.Tab>
					<Tabs.Tab icon={<IconLocation size="1rem" />} value="mostPurchasedDestination">
						Most Popular Destinations
					</Tabs.Tab>
					<Tabs.Tab icon={<IconMoneybag size="1rem" />} value="mostExpensiveTicketOfCompany">
						Most Expensive Tickets
					</Tabs.Tab>
					<Tabs.Tab icon={<IconPigMoney size="1rem" />} value="cheapestTicketOfCompany">
						Cheapest Tickets
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
						<Title>Most Purchased Arrivals</Title>
					</Flex>
				</Tabs.Panel>

				<Tabs.Panel value="mostPurchasedDestination" pt="md">
					<Flex direction="column" gap="md">
						<Title>Most Purchased Destinations</Title>
					</Flex>
				</Tabs.Panel>

				<Tabs.Panel value="mostExpensiveTicketOfCompany" pt="md">
					<Flex direction="column" gap="md">
						<Title>Most Expensive Tickets Of Companies</Title>
					</Flex>
				</Tabs.Panel>

				<Tabs.Panel value="cheapestTicketOfCompany" pt="md">
					<Flex direction="column" gap="md">
						<Title>Cheapest Tickets Of Companies</Title>
					</Flex>
				</Tabs.Panel>
			</Tabs>
		</Center>
	);
};

export default SystemReportsPage;
