import { Flex, Button, Select, Group, Text, SelectItem } from '@mantine/core';
import { DatePickerInput } from '@mantine/dates';
import { forwardRef, useState } from 'react';
import { primaryButtonColor } from '../../constants/colors';
import { IconMapPin } from '@tabler/icons-react';
import { notifications } from '@mantine/notifications';

interface ItemProps extends React.ComponentPropsWithoutRef<'div'> {
	label: string;
	description: string;
	value: string;
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
interface CarSearchBarProps {
	addressList: Array<SelectItem>;
	search: (filterParams: URLSearchParams) => void;
}
const CarRentSearchBar = ({ addressList, search }: CarSearchBarProps) => {
	const [selectedCity, setCityValue] = useState<string | null>('');
	const [searchValue, onSearchChange] = useState('');
	const [value, setValue] = useState<[Date | null, Date | null]>([null, null]);
	const handleFilter = () => {
		const city = selectedCity ? selectedCity : '';
		if (value[0] === null || value[1] === null) {
			notifications.show({
				id: 'search-fail',
				title: 'Cannot search cars',
				message: 'Please select pick-up and drop dates',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
			});
		} else if (city === '') {
			notifications.show({
				id: 'search-fail',
				title: 'Cannot search cars',
				message: 'Please select pick-up location',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
			});
		} else {
			const startDate: string = value[0]!.toISOString().split('T')[0];
			const endDate: string = value[1]!.toISOString().split('T')[0];
			const filterParams = new URLSearchParams([
				['startDate', startDate],
				['endDate', endDate],
				//['city', city],
			]);
			search(filterParams);
		}
	};

	return (
		<Flex direction={'row'} gap={'xs'} align={'end'}>
			<Select
				label="Pick-up Location"
				onSearchChange={onSearchChange}
				searchValue={searchValue}
				searchable
				itemComponent={CustomSelectItem}
				nothingFound="No location found"
				data={addressList}
				onChange={(value) => setCityValue(value)}
				allowDeselect
				clearable
			></Select>
			<DatePickerInput
				type="range"
				valueFormat="YYYY-MM-DD"
				label="Pick-up Dates"
				placeholder="Pick-up Dates"
				value={value}
				onChange={setValue}
				mx="auto"
				maw={400}
			/>
			<Button
				bg={primaryButtonColor}
				onClick={() => {
					handleFilter();
				}}
			>
				Search
			</Button>
		</Flex>
	);
};
export default CarRentSearchBar;
