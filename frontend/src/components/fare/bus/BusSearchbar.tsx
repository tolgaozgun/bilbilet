import {
	Button,
	Flex,
	Group,
	NumberInput,
	Select,
	SelectItem,
	Text,
} from '@mantine/core';
import { DatePickerInput } from '@mantine/dates';
import { IconBus, IconPlane } from '@tabler/icons-react';
import { forwardRef, useState } from 'react';
import { primaryButtonColor } from '../../../constants/colors';
import { StationType } from '../../../types/LocationTypes';

interface ItemProps extends React.ComponentPropsWithoutRef<'div'> {
	stationType: StationType;
	label: string;
	description: string;
}
const CustomSelectItem = forwardRef<HTMLDivElement, ItemProps>(
	({ stationType, label, description, ...others }: ItemProps, ref) => (
		<div ref={ref} {...others}>
			<Group noWrap>
				{stationType == 'AIRPORT' ? <IconPlane /> : <IconBus />}
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
interface BusSearchBarProps {
	stationList: Array<SelectItem>;
}
const BusSearchBar = ({ stationList }: BusSearchBarProps) => {
	const [searchValue, onSearchChange] = useState('');
	return (
		<Flex direction={'row'} gap={'xs'} align={'end'}>
			<Select
				placeholder="Select Departure Location"
				label="Departure"
				onSearchChange={onSearchChange}
				searchValue={searchValue}
				searchable
				itemComponent={CustomSelectItem}
				nothingFound="No location found"
				data={stationList}
				allowDeselect
				clearable
			></Select>
			<DatePickerInput
				type="default"
				label="Departure Date"
				placeholder="Departure Date"
				mx="auto"
				maw={400}
			/>
			<Select
				label="Arrival"
				placeholder="Select Arrival Location"
				onSearchChange={onSearchChange}
				searchValue={searchValue}
				itemComponent={CustomSelectItem}
				searchable
				nothingFound="No location found"
				data={stationList}
				allowDeselect
				clearable
			></Select>
			<NumberInput label="Passenger" defaultValue={1} min={1}></NumberInput>
			<Button bg={primaryButtonColor}>Search</Button>
		</Flex>
	);
};
export default BusSearchBar;
