import React from 'react';
import ReactDOM from 'react-dom/client';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import ProviderWrapper from './components/common/ProviderWrapper';
import './index.css';
import Layout from './layout';
import SearchFarePage from './pages/fare/SearchFarePage';
import LoginPage from './pages/login/LoginPage';
import RegisterPage from './pages/login/RegisterPage';
import PurchaseFailed from './pages/purchase-ticket/PurchaseFailed';
import PurchaseSucceeded from './pages/purchase-ticket/PurchaseSucceeded';
import PurchaseTicketPage from './pages/purchase-ticket/PurchaseTicketPage';
import AddCarPage from './pages/rent-car/AddCarPage';
import SearchRentCarPage from './pages/rent-car/SearchRentCarPage';
import PastTicketsPage from './pages/tickets/past-tickets/PastTicketsPage';
import AddHotelPage from './pages/hotel/AddHotelPage';
import ReviewPage from './pages/review/ReviewPage';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import AddStationPage from './pages/location/AddStationPage';

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
						path: '/purchase-ticket',
						element: <PurchaseTicketPage />,
					},
					{
						path: '/purchase-succeeded',
						element: <PurchaseSucceeded />,
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
