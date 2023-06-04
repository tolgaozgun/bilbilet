import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import React from 'react';
import ReactDOM from 'react-dom/client';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import ProviderWrapper from './components/common/other/ProviderWrapper';
import JourneyList from './components/journey/JourneyList';
import './index.css';
import Layout from './layout';
import SearchFarePage from './pages/fare/SearchFarePage';

import SeatSelectionPage from './pages/fare/SeatSelectionPage';
import AddHotelPage from './pages/hotel/AddHotelPage';
import JourneyPlansPage from './pages/journey/JourneyPlansPage';
import AddAddressPage from './pages/location/AddAddressPage';
import AddStationPage from './pages/location/AddStationPage';
import LoginPage from './pages/login/LoginPage';
import RegisterPage from './pages/login/RegisterPage';
import TravelerProfilePage from './pages/profile/TravelerProfilePage';
import PurchaseFailed from './pages/purchase-ticket/PurchaseFailed';
import PurchaseSucceeded from './pages/purchase-ticket/PurchaseSucceeded';
import PurchaseTicketPage from './pages/purchase-ticket/PurchaseTicketPage';
import AddCarPage from './pages/rent-car/AddCarPage';
import SearchRentCarPage from './pages/rent-car/SearchRentCarPage';
import ReviewPage from './pages/review/ReviewPage';
import PastTicketsPage from './pages/tickets/past-tickets/PastTicketsPage';

const router = createBrowserRouter([
	{
		children: [
			{
				path: '/',
				element: <Layout />,
				children: [
					{
						path: '/login',
						element: <LoginPage />,
					},
					{
						path: '/register',
						element: <RegisterPage />,
					},
					{
						path: '/search-car-rent',
						element: <SearchRentCarPage />,
					},
					{
						path: '/add-car',
						element: <AddCarPage />,
					},
					{
						path: '/search-fare',
						element: <SearchFarePage />,
					},
					{
						path: '/purchase-ticket/:ticketId',
						element: <PurchaseTicketPage />,
					},
					{
						path: '/purchase-succeeded',
						element: <PurchaseSucceeded ticketDetails={null} />,
					},
					{
						path: '/purchase-failed',
						element: <PurchaseFailed />,
					},
					{
						path: '/past-tickets',
						element: <PastTicketsPage />,
					},
					{
						path: '/add-hotel',
						element: <AddHotelPage />,
					},
					{
						path: '/add-review/company/:id',
						element: <ReviewPage isCompany={true} />,
					},
					{
						path: '/add-review/trip/:id',
						element: <ReviewPage isCompany={false} />,
					},
					{
						path: '/add-station',
						element: <AddStationPage />,
					},
					{
						path: '/add-address',
						element: <AddAddressPage />,
					},
					{
						path: '/traveler/journey-plans',
						element: <JourneyPlansPage />,
					},
					{
						path: '/traveler/journey-plans/:journeyPlanId',
						element: <JourneyList />,
					},
					{
						path: '/traveler/profile',
						element: <TravelerProfilePage />,
					},
					{
						path: '/fare/:fareId/select-seats',
						element: <SeatSelectionPage />,
					},
				],
			},
		],
	},
]);
const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
	<React.StrictMode>
		<QueryClientProvider client={queryClient}>
			<ProviderWrapper>
				<RouterProvider router={router} />
			</ProviderWrapper>
		</QueryClientProvider>
	</React.StrictMode>,
);
