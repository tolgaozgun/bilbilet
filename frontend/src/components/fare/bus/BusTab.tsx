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
import { FareDetailsView, FareSearchParams } from '../../../types';
import {
	convertDateToTime,
	formatDate,
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
	const axiosSecure = useAxiosSecure();
	const [depSearchValue, setDepSearchValue] = useState('');
	const [arrSearchValue, setArrSearchValue] = useState('');
	const [direction, setDirection] = useState('one-way');

	const [depValue, setDepValue] = useState<string | null>(null);
	const [arrValue, setArrValue] = useState<string | null>(null);
	const [deptDate, setDeptDate] = useState<Date | null>(null);
	const [returnDate, setReturnDate] = useState<Date | null>(null);

	const [searchParams, setSearchParams] = useState<FareSearchParams | {}>({});

	const {
		isLoading: isFareLoading,
		isError: isFareFetchError,
		data: busResponse,
		error,
	} = useBusFares(axiosSecure, searchParams);

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

	const stations = stationData.filter(
		(station) => station.stationType === 'BUS_TERMINAL',
	);

	return (
		<Card withBorder radius="xl" shadow="xl" p={48} sx={{ minWidth: 400 }} mx="auto">
			<Flex direction={'column'} align={'start'} gap={'xl'}>
				{/* DON'T YOU DARE REMOVE THIS OR ELSE THIS COMPONENT FAILS*/}
				<Title>Buy Bus Ticket</Title>
				<Stack>
					<Text>Select direction</Text>{' '}
					{/* <Radio.Group
						value={direction}
						onChange={setDirection}
						name="flightDirection"
					>
						<Group>
							<Radio value="one-way" label="One way" />
							<Radio value="round-trip" label="Round trip" />
						</Group>
					</Radio.Group> */}
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
							data={stations}
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
							data={stations}
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
				<Flex direction={'column'} gap={'xl'}>
					{busResponse?.data?.map((bus) => {
						const depTimeDateObj = new Date(bus.depTime);
						const arrTimeDateObj = new Date(bus.arrTime);

						const depTime = convertDateToTime(depTimeDateObj);
						const arrTime = convertDateToTime(arrTimeDateObj);
						const depDate = formatDate(depTimeDateObj);
						const arrDate = formatDate(arrTimeDateObj);
						const duration = getTimeDifference(
							depTimeDateObj,
							arrTimeDateObj,
						);
						return (
							<FareInfoCard
								companyName={bus.companyName}
								arrDate={arrDate}
								depDate={depDate}
								departureTime={depTime}
								arrivalTime={arrTime}
								departureLocation={bus.depStationTitle}
								arrivalLocation={bus.arrStationTitle}
								departureABB={bus.depStationAbbr}
								arrivalABB={bus.arrStationAbbr}
								duration={duration}
								price={bus.basePrice}
								fareId={bus.fareId}
								key={bus.fareId}
								type={'bus'}
							/>
						);
					})}
				</Flex>
			</Flex>
		</Card>
	);
};

export default BusTab;
