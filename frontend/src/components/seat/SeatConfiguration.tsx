import {
	Badge,
	Button,
	Card,
	Center,
	Flex,
	Grid,
	Group,
	Stack,
	Title,
} from '@mantine/core';
import { useState } from 'react';
import { VehicleSeatConfig } from '../../types/SeatTypes';
import { convertFlightColumnToAlphabetic } from '../../utils/utils';

interface SeatConfigurationProps {
	seatConfig: VehicleSeatConfig;
}

const SeatConfiguration = ({ seatConfig }: SeatConfigurationProps) => {
	const [selectedSeats, setSelectedSeats] = useState<string[]>([]);
	const noOfSeatsPerColumn = seatConfig.seatingArrangement.split(',');

	const noOfSeatsBeforeDivision = (divIndex: number) => {
		let total = 0;
		for (let i = 0; i < noOfSeatsPerColumn.length; i++) {
			if (i == divIndex) {
				return total;
			}
			total += Number(noOfSeatsPerColumn[i]);
		}

		return total;
	};

	const getSeatDisplayString = (
		rowIndex: number,
		colDivisionIndex: number,
		columnIndex: number,
	) => {
		const col = noOfSeatsBeforeDivision(colDivisionIndex) + columnIndex + 1;
		const colToAlphabet = convertFlightColumnToAlphabetic(col);
		return `${rowIndex + 1}${colToAlphabet}`;
	};

	const getButtonColor = (rowIndex: number) => {
		let color = '';
		if (rowIndex < seatConfig.firstClassAfter) {
			color = 'yellow';
		} else if (rowIndex < seatConfig.businessClassAfter) {
			color = 'violet';
		} else if (rowIndex < seatConfig.premiumEconomyClassAfter) {
			color = 'orange';
		} else {
			color = 'gray ';
		}

		console.log(color);
		return color;
	};

	console.log(noOfSeatsPerColumn);
	return (
		<Center>
			<Stack>
				<Title>{seatConfig.configName}</Title>
				<Group>
					<Badge color="yellow">First Class</Badge>
					<Badge color="violet">Business Class</Badge>
					<Badge color="orange">Premium Economy Class</Badge>
					<Badge color="gray">Economy Class</Badge>
				</Group>
				<Flex direction="column">
					{Array.from(
						{ length: Number(seatConfig.configTotalRows) },
						(_, rowIndex) => (
							<Grid gutter={6} gutterXs="md" gutterMd="xl" gutterXl={50}>
								{noOfSeatsPerColumn.map(
									(columnSize, colDivisionIndex) => {
										return (
											<Card miw={250}>
												<Grid.Col span={Number(columnSize)}>
													<Flex>
														{Array.from(
															{
																length: Number(
																	columnSize,
																),
															},
															(_, columnIndex) => (
																<Button
																	variant="filled"
																	color={getButtonColor(
																		rowIndex,
																	)}
																	key={getSeatDisplayString(
																		rowIndex,
																		colDivisionIndex,
																		columnIndex,
																	)}
																>
																	{getSeatDisplayString(
																		rowIndex,
																		colDivisionIndex,
																		columnIndex,
																	)}
																</Button>
															),
														)}
													</Flex>
												</Grid.Col>
											</Card>
										);
									},
								)}
							</Grid>
						),
					)}
				</Flex>
			</Stack>
		</Center>
	);
};

export default SeatConfiguration;
