import { Flex, Select } from '@mantine/core';
import { useState } from 'react';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';

const HotelSearchBar = () => {
	const [searchValue, onSearchChange] = useState('');

	return (
		<Flex direction={'row'} gap={'xs'} align={'end'}>
			<Select
				placeholder="Select Location"
				label="Location"
				onSearchChange={onSearchChange}
				searchValue={searchValue}
				searchable
				nothingFound="No location found"
				data={['Ankara', 'Antalya', 'İzmir']}
				allowDeselect
				clearable
			></Select>
			<CustomElevatedButton text={'Search'}></CustomElevatedButton>
		</Flex>
	);
};
export default HotelSearchBar;
