import { Accordion, Box, MultiSelect, RangeSlider } from '@mantine/core';
import { IconZoomIn } from '@tabler/icons-react';
import CustomElevatedButton from '../../common/buttons/CustomElevatedButton';
import CustomAccordionItem from '../../common/other/CustomAccordionItem';

const marks = [
	{ value: 0, label: '0 TRY' },
	{ value: 10000, label: '10000 TRY' },
];

const BusFilter = () => {
	return (
		<Box miw={'10vw'}>
			<Accordion multiple variant="contained" radius="xs" bg={'#B5B4E8'}>
				<CustomAccordionItem value={'Price Per Passenger Range'}>
					<RangeSlider defaultValue={[0, 10000]} marks={marks}></RangeSlider>
				</CustomAccordionItem>
				<CustomAccordionItem value={'Departure Time'}>
					<RangeSlider
						min={0}
						max={23}
						label={(value) => `${value}:00`}
						step={1}
						labelAlwaysOn
					></RangeSlider>
				</CustomAccordionItem>
				<CustomAccordionItem value={'Arrival Time'}>
					<RangeSlider
						min={0}
						max={23}
						label={(value) => `${value}:00`}
						step={1}
						labelAlwaysOn
					></RangeSlider>
				</CustomAccordionItem>
				<CustomAccordionItem value={'Company'}>
					<MultiSelect
						searchable
						data={[
							{ value: 'kamilkoc', label: 'Kamilkoç' },
							{ value: 'pamukkale', label: 'Pamukkale' },
							{ value: 'aliosman', label: 'Ali Osman Ulusoy' },
						]}
						placeholder="Select Companies"
					/>
				</CustomAccordionItem>
			</Accordion>
			<CustomElevatedButton
				text={'Filter'}
				leftIcon={<IconZoomIn></IconZoomIn>}
			></CustomElevatedButton>
		</Box>
	);
};
export default BusFilter;
