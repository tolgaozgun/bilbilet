import {
	Accordion,
	ActionIcon,
	Box,
	Button,
	Flex,
	Group,
	Loader,
	Modal,
	MultiSelect,
	Radio,
	RangeSlider,
	Select,
	SelectItem,
	Stack,
	Text,
	TextInput,
} from '@mantine/core';
import { useDisclosure } from '@mantine/hooks';
import { notifications } from '@mantine/notifications';
import {
	IconAdjustments,
	IconMapPin,
	IconSearch,
	IconX,
	IconZoomIn,
} from '@tabler/icons-react';
import { forwardRef, useState } from 'react';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import useAddresses from '../../hooks/location/useAddresses';
import {
	HotelFilterParams,
	HotelSortBy,
	HotelSortByOptions,
	HotelSortDirections,
	hotelSortByOptions,
	hotelSortByOptionsToRealValues,
} from '../../types';
import { isErrorResponse } from '../../utils/utils';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';
import CustomAccordionItem from '../common/other/CustomAccordionItem';

const marks = [
	{ value: 0, label: '0 TRY' },
	{ value: 10000, label: '10000 TRY' },
];
const ratingMarks = [
	{ value: 1, label: '1' },
	{ value: 5, label: '5' },
];

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

interface HotelSearchBarProps {
	onFilter: (filterParams: HotelFilterParams | {}) => void;
}

const HotelFilter = ({ onFilter }: HotelSearchBarProps) => {
	// Filters
	const [searchValue, setSearchChange] = useState('');
	const [selectedValue, setSelectedValue] = useState<string | null>('');
	const [priceRange, setPriceRange] = useState<[number, number]>([0, 10000]);
	const [hotelName, setHotelName] = useState('');

	// Sort
	const [hotelSortBy, setHotelSortBy] = useState<HotelSortBy | null>({
		sortBy: '',
		sortDirection: '',
	});
	const [selectedSortDirection, setSelectedSortDirection] =
		useState<HotelSortDirections>('');
	const [selectedSortOption, setSelectedSortOption] = useState<HotelSortByOptions>('');
	const [opened, { open, close }] = useDisclosure(false);

	const axiosSecure = useAxiosSecure();
	const { isLoading, isError, data: addresses } = useAddresses(axiosSecure);
	if (isLoading) {
		return <Loader />;
	}
	if (isError) {
		if (!addresses) {
			notifications.show({
				message: 'Something went wrong',
			});
		} else if (isErrorResponse(addresses)) {
			notifications.show({
				message: addresses.msg,
			});
		}
		return <div>Couldn't fetch addresses...</div>; // TODO: Error page
	}

	const addressList: Array<SelectItem> = addresses.data!.map((address) => ({
		value: address.addressId.toString(),
		label: address.city,
		description: address.country,
	}));

	const handleOnFilter = () => {
		let filterParams: HotelFilterParams;
		if (hotelSortBy?.sortBy && hotelSortBy?.sortDirection) {
			filterParams = {
				address_id: selectedValue ? parseInt(selectedValue) : '',
				name: hotelName,
				avg_price: priceRange,
				order_by: [hotelSortBy.sortBy, hotelSortBy.sortDirection],
			};
		} else {
			filterParams = {
				address_id: selectedValue ? parseInt(selectedValue) : '',
				name: hotelName,
				avg_price: priceRange,
			};
		}

		onFilter(filterParams);
	};

	const handleSetSortBy = () => {
		if (!selectedSortDirection || !selectedSortOption) {
			notifications.show({
				message: 'Please select both sort direction and sort option',
				color: 'red',
			});
			return;
		}

		setHotelSortBy({
			sortBy: selectedSortOption,
			sortDirection: selectedSortDirection,
		});
		close();
	};

	const handleResetSortBy = () => {
		setHotelSortBy({
			sortBy: '',
			sortDirection: '',
		});
		close();
	};

	console.log(hotelSortBy);

	return (
		<>
			<Modal opened={opened} onClose={close} title="Select sort options">
				<Stack spacing="lg">
					<Select
						placeholder="Select a sort parameter"
						data={hotelSortByOptions}
						label="Sort Parameter"
						required
						value={selectedSortOption}
						onChange={(value) =>
							setSelectedSortOption(value as HotelSortByOptions)
						}
					></Select>
					<Radio.Group
						value={selectedSortDirection?.toString()}
						onChange={(event) =>
							setSelectedSortDirection(event as HotelSortDirections)
						}
						label="Sort Direction"
						description="Select sort direction"
						required
					>
						<Group>
							<Radio label="Ascending" value="ASC"></Radio>
							<Radio label="Descending" value="DESC"></Radio>
						</Group>
					</Radio.Group>
					<Flex justify="space-evenly" align="center">
						<CustomElevatedButton
							text={'Apply'}
							leftIcon={<IconAdjustments />}
							onClick={handleSetSortBy}
						/>
						<Button
							leftIcon={<IconX />}
							onClick={handleResetSortBy}
							color="red"
						>
							Reset
						</Button>
					</Flex>
				</Stack>
			</Modal>
			<Box miw={'20vw'}>
				<Stack spacing="lg">
					<Button
						leftIcon={<IconAdjustments size="1.125rem" />}
						variant="outline"
						onClick={open}
					>
						Sorting By:{' '}
						{hotelSortBy?.sortBy === ''
							? 'None'
							: hotelSortByOptionsToRealValues[hotelSortBy?.sortBy!]}
					</Button>
					<Accordion multiple variant="contained" radius="xs" bg={'#B5B4E8'}>
						<CustomAccordionItem value="Location">
							<Select
								placeholder="Select Location"
								label="Location"
								itemComponent={CustomSelectItem}
								onSearchChange={setSearchChange}
								searchValue={searchValue}
								searchable
								value={selectedValue}
								onChange={(value) => setSelectedValue(value)}
								nothingFound="No location found"
								data={addressList}
								allowDeselect
								clearable
							/>
						</CustomAccordionItem>
						<CustomAccordionItem value={'Average Price'}>
							<RangeSlider
								value={priceRange}
								onChange={setPriceRange}
								marks={marks}
								min={0}
								max={10000}
							/>
						</CustomAccordionItem>
						<CustomAccordionItem value={'Hotel Name'}>
							<TextInput
								label="Hotel Name"
								value={hotelName}
								onChange={(e) => setHotelName(e.currentTarget.value)}
								placeholder="Hotel Name"
							/>
						</CustomAccordionItem>
					</Accordion>
					<CustomElevatedButton
						text={'Search'}
						leftIcon={<IconSearch />}
						isLoading={isLoading}
						onClick={() => handleOnFilter()}
					/>
				</Stack>
			</Box>
		</>
	);
};
export default HotelFilter;
