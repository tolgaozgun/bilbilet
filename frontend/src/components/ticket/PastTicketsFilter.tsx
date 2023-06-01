import { Accordion, Box, Checkbox, Flex, MultiSelect } from '@mantine/core';
import { IconZoomCheck, IconZoomFilled, IconZoomIn } from '@tabler/icons-react';
import CustomElevatedButton from '../common/buttons/CustomElevatedButton';
import CustomAccordionItem from '../common/other/CustomAccordionItem';

const PastTicketsFilter = () => {
	return (
		<Box w={'20vw'}>
			<Flex direction={'column'} gap={'xs'}>
				<Accordion multiple variant="contained" radius="xs" bg={'#B5B4E8'}>
					<CustomAccordionItem value={'Company'}>
						<MultiSelect
							searchable
							data={[
								{ value: 'kamilkoc', label: 'KamilKoç' },
								{ value: 'pegasus', label: 'Pegasus' },
								{ value: 'thy', label: 'THY' },
							]}
							placeholder="Pick all that you like"
						/>
					</CustomAccordionItem>

					<CustomAccordionItem value={'Transportation Type'}>
						<Flex direction={'column'} gap={'xs'}>
							<Checkbox label="Flight" />
							<Checkbox label="Bus" />
						</Flex>
					</CustomAccordionItem>

					<CustomAccordionItem value={'Status'}>
						<Flex direction={'column'} gap={'xs'}>
							<Checkbox label="PURCHASED" />
							<Checkbox label="EXPIRED" />
							<Checkbox label="USED" />
							<Checkbox label="ON HOLD" />
							<Checkbox label="RESERVED" />
							<Checkbox label="OTHER" />
						</Flex>
					</CustomAccordionItem>
				</Accordion>
				<CustomElevatedButton
					text={'Filter'}
					leftIcon={<IconZoomIn></IconZoomIn>}
				></CustomElevatedButton>
			</Flex>
		</Box>
	);
};
export default PastTicketsFilter;
