import {
	Accordion,
	Box,
	Group,
	Loader,
	MultiSelect,
	RangeSlider,
	Select,
	SelectItem,
	Stack,
	Text,
	TextInput,
} from '@mantine/core';
import { notifications } from '@mantine/notifications';
import { IconMapPin, IconZoomIn } from '@tabler/icons-react';
import { forwardRef, useState } from 'react';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import useAddresses from '../../hooks/location/useAddresses';
import { HotelFilterParams } from '../../types';
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
	const [searchValue, onSearchChange] = useState('');
	const [priceRange, setPriceRange] = useState<[number, number]>([0, 10000]);
	const [hotelName, setHotelName] = useState('');
	const [ratingRange, setRatingRange] = useState<[number, number]>([0, 5]);

	const axiosSecure = useAxiosSecure();
	const { isLoading, isError, data: addresses } = useAddresses(axiosSecure);
	if (isLoading) {
		return <Loader />;
	}
	if (isError) {
		if (isErrorResponse(addresses)) {
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
		const filterParams: HotelFilterParams = {
			addressId: Number(searchValue),
			name: hotelName,
			minPrice: priceRange[0],
			maxPrice: priceRange[1],
			minRating: ratingRange[0],
			maxRating: ratingRange[1],
		};
		onFilter(filterParams);
	};

	return (
		<Box miw={'10vw'}>
			<Stack>
				<Accordion multiple variant="contained" radius="xs" bg={'#B5B4E8'}>
					<CustomAccordionItem value="Location">
						<Select
							placeholder="Select Location"
							label="Location"
							itemComponent={CustomSelectItem}
							onSearchChange={onSearchChange}
							searchValue={searchValue}
							searchable
							nothingFound="No location found"
							data={addressList}
							allowDeselect
							clearable
						/>
					</CustomAccordionItem>
					<CustomAccordionItem value={'Avg Price'}>
						<RangeSlider
							value={priceRange}
							onChange={setPriceRange}
							marks={marks}
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
					<CustomAccordionItem value={'Rating'}>
						<RangeSlider
							value={ratingRange}
							onChange={setRatingRange}
							min={0}
							max={5}
							step={0.5}
						/>
					</CustomAccordionItem>
				</Accordion>
				<CustomElevatedButton
					text={'Filter'}
					leftIcon={<IconZoomIn />}
					onClick={() => handleOnFilter()}
				/>
			</Stack>
		</Box>
	);
};
export default HotelFilter;
