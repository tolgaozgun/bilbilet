import { Select } from '@mantine/core';
const sortOptions = [
	'By Date (Latest First)',
	'By Date (Earliest First)',
	'By Price (Lowest First)',
	'By Price (Highest First)',
	'By Company Name (A-Z)',
	'By Company Name (Z-A)',
];
const SortPastTicketsBar = () => {
	return <Select label={'Sort By'} w={'25%'} data={sortOptions}></Select>;
};

export default SortPastTicketsBar;
