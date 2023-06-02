import { Flex, Button, Select, Group, Text, SelectItem } from '@mantine/core';
import { DatePickerInput } from '@mantine/dates';
import { forwardRef, useState } from 'react';
import { primaryButtonColor } from '../../constants/colors';
import { IconMapPin } from '@tabler/icons-react';

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
interface CarSearchBarProps {
	addressList: Array<SelectItem>;
}
const CarRentSearchBar = ({ addressList }: CarSearchBarProps) => {
	const [searchValue, onSearchChange] = useState('');
	const [value, setValue] = useState<[Date | null, Date | null]>([null, null]);
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
				allowDeselect
				clearable
			></Select>
			<DatePickerInput
				type="range"
				label="Pick-up Dates"
				placeholder="Pick-up Dates"
				value={value}
				onChange={setValue}
				mx="auto"
				maw={400}
			/>
			<Button bg={primaryButtonColor}>Search</Button>
		</Flex>
	);
};
export default CarRentSearchBar;
