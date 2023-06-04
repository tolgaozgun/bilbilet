import {
	Button,
	Card,
	Flex,
	Group,
	Radio,
	Select,
	SelectItem,
	Stack,
	Text,
	Title,
} from '@mantine/core';
import { DatePickerInput } from '@mantine/dates';
import { notifications } from '@mantine/notifications';
import { IconMapPin } from '@tabler/icons-react';
import { forwardRef, useState } from 'react';
import { primaryButtonColor } from '../../../constants/colors';
import useAxiosSecure from '../../../hooks/auth/useAxiosSecure';
import useBusFares from '../../../hooks/fare/useBusFares';
import LoadingPage from '../../../pages/LoadingPage';
import { FareSearchParams } from '../../../types';
import {
	convertDateToTime,
	getTimeDifference,
	isErrorResponse,
} from '../../../utils/utils';
import ItemsNotFoundPage from '../../common/feedback/ItemsNotFoundPage';
import FareInfoCard from '../FareInfoCard';

interface BusTabProps {
	stationData: Array<SelectItem>;
}

interface ItemProps extends React.ComponentPropsWithoutRef<'div'> {
	label: string;
	description: string;
}
const CustomSelectItem = forwardRef<HTMLDivElement, ItemProps>(
	({ label, description, ...others }: ItemProps, ref) => (
		<div ref={ref} {...others}>
			<Group noWrap>
				<IconMapPin />
				<div>
					<Text size="sm">{label}</Text>
					<Text size="xs" opacity={0.65}>
						{description}
					</Text>
				</div>
			</Group>
		</div>
	),
);

const BusTab = ({ stationData }: BusTabProps) => {
	const [depSearchValue, setDepSearchValue] = useState('');
	const [arrSearchValue, setArrSearchValue] = useState('');
	const [direction, setDirection] = useState('one-way');

	const [depValue, setDepValue] = useState<string | null>(null);
	const [arrValue, setArrValue] = useState<string | null>(null);
	const [deptDate, setDeptDate] = useState<Date | null>(null);
	const [returnDate, setReturnDate] = useState<Date | null>(null);

	const axiosSecure = useAxiosSecure();
	const [searchParams, setSearchParams] = useState<FareSearchParams | {}>({});
	const {
		isLoading: isFareLoading,
		isError: isFareFetchError,
		data: busResponse,
	} = useBusFares(axiosSecure, searchParams);

	if (isFareLoading) {
		return <LoadingPage />;
	}
	if (isFareFetchError) {
		if (!busResponse) {
			notifications.show({
				message: 'Something went wrong',
			});
		} else if (isErrorResponse(busResponse)) {
			notifications.show({
				message: busResponse.msg,
			});
		}
		return <ItemsNotFoundPage />;
	}

	const onSearch = () => {
		if (direction === 'one-way' && depValue && arrValue && deptDate) {
			const newSearchParams: FareSearchParams = {
				arrive_stat_id: Number(arrValue),
				dep_stat_id: Number(depValue),
				departure_time: deptDate,
			};
			setSearchParams(newSearchParams);
			return;
		} else if (
			direction === 'round-trip' &&
			depValue &&
			arrValue &&
			deptDate &&
			returnDate
		) {
			const newSearchParams: FareSearchParams = {
				arrive_stat_id: Number(arrValue),
				dep_stat_id: Number(depValue),
				departure_time: deptDate,
				return_date: returnDate,
			};
			setSearchParams(newSearchParams);
			return;
		}

		notifications.show({
			message: 'Please fill in all the fields.',
			color: 'red',
		});
		return;
	};

	return (
		<Card withBorder radius="xl" shadow="xl" p={48} sx={{ minWidth: 400 }} mx="auto">
			<Flex direction={'column'} align={'start'} gap={'xl'}>
				<Title>Buy Bus Ticket</Title>
				<Stack>
					<Radio.Group
						value={direction}
						onChange={setDirection}
						name="flightDirection"
					>
						<Group>
							<Radio value="one-way" label="One way" />
							<Radio value="round-trip" label="Round trip" />
						</Group>
					</Radio.Group>
					<Flex direction={'row'} gap={'xs'} align={'end'}>
						<Select
							placeholder="Select Departure Location"
							label="Departure"
							onSearchChange={setDepSearchValue}
							searchValue={depSearchValue}
							value={depValue}
							onChange={(value) => setDepValue(value)}
							searchable
							nothingFound="No location found"
							itemComponent={CustomSelectItem}
							data={stationData}
							allowDeselect
							clearable
						/>
						<Select
							label="Arrival"
							placeholder="Select Arrival Location"
							onSearchChange={setArrSearchValue}
							searchValue={arrSearchValue}
							value={arrValue}
							onChange={setArrValue}
							searchable
							itemComponent={CustomSelectItem}
							nothingFound="No location found"
							data={stationData}
							allowDeselect
							clearable
						/>
						<DatePickerInput
							type="default"
							label="Departure Date"
							placeholder="Departure date"
							mx="auto"
							maw={400}
							value={deptDate}
							onChange={setDeptDate}
						/>
						{direction === 'round-trip' && (
							<DatePickerInput
								type="default"
								label="Return Date"
								placeholder="Return date"
								mx="auto"
								maw={400}
								value={returnDate}
								onChange={setReturnDate}
							/>
						)}
						<Button bg={primaryButtonColor} onClick={onSearch}>
							Search
						</Button>
					</Flex>
				</Stack>
				<Flex direction={'row'} gap={'xl'}>
					{/* <BusFilter></BusFilter> */}
					{busResponse.data?.map((bus) => {
						const depTime = convertDateToTime(bus.depTime);
						const arrTime = convertDateToTime(bus.arrTime);
						const duration = getTimeDifference(bus.depTime, bus.arrTime);
						return (
							<FareInfoCard
								companyName={'name'}
								departureTime={depTime}
								arrivalTime={arrTime}
								departureLocation={bus.depStationTitle}
								arrivalLocation={bus.arrStationTitle}
								departureABB={bus.depStationAbbr}
								arrivalABB={bus.arrStationAbbr}
								duration={duration}
								price={bus.basePrice}
								fareId={bus.fareId}
							/>
						);
					})}
				</Flex>
			</Flex>
		</Card>
	);
};

export default BusTab;
