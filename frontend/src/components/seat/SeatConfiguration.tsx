import {
	Badge,
	Button,
	Card,
	Center,
	Flex,
	Grid,
	Group,
	Stack,
	Text,
	Title,
} from '@mantine/core';
import { useState } from 'react';
import { SeatLocation, SeatTicket, VehicleSeatConfig } from '../../types/SeatTypes';
import { convertFlightColumnToAlphabetic } from '../../utils/utils';

interface SeatConfigurationProps {
	seatConfig: VehicleSeatConfig;
	seatTickets: SeatTicket[];
}

const SeatConfiguration = ({ seatConfig, seatTickets }: SeatConfigurationProps) => {
	const [selectedSeats, setSelectedSeats] = useState<[number, number][]>([]);
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

	const getColNumber = (colDivisionIndex: number, columnIndex: number) => {
		return noOfSeatsBeforeDivision(colDivisionIndex) + columnIndex + 1;
	};

	const getRowNumber = (rowIndex: number) => {
		return rowIndex + 1;
	};

	const isSeatSelected = (
		rowIndex: number,
		colDivisionIndex: number,
		colIndex: number,
	) => {
		const rowNumber = getRowNumber(rowIndex);
		const colNumber = getColNumber(colDivisionIndex, colIndex);
		for (const [index, selectedSeat] of selectedSeats.entries()) {
			if (selectedSeat[0] === rowNumber && selectedSeat[1] === colNumber) {
				return true;
			}
		}
	};

	const getSeatDisplayString = (
		rowIndex: number,
		colDivisionIndex: number,
		columnIndex: number,
	) => {
		const col = getColNumber(colDivisionIndex, columnIndex);
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

		return color;
	};

	const onSeatSelection = (
		rowIndex: number,
		colDivisionIndex: number,
		colIndex: number,
	) => {
		const rowNumber = getRowNumber(rowIndex);
		const colNumber = getColNumber(colDivisionIndex, colIndex);
		const seatLocation: SeatLocation = [rowNumber, colNumber];
		setSelectedSeats([seatLocation]);
	};

	// TODO: Add continue with payment button if a seat is selected, get the seat using the seatTIckets info

	return (
		<Center>
			<Stack>
				<Title>{seatConfig.configName}</Title>
				<Group>
					<Badge color="yellow">First Class</Badge>
					<Badge color="violet">Business Class</Badge>
					<Badge color="orange">Premium Economy Class</Badge>
					<Badge color="gray">Economy Class</Badge>
					<Badge color="red">Selected</Badge>
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
																	color={
																		isSeatSelected(
																			rowIndex,
																			colDivisionIndex,
																			columnIndex,
																		)
																			? 'red'
																			: getButtonColor(
																					rowIndex,
																			  )
																	}
																	key={getSeatDisplayString(
																		rowIndex,
																		colDivisionIndex,
																		columnIndex,
																	)}
																	onClick={() =>
																		onSeatSelection(
																			rowIndex,
																			colDivisionIndex,
																			columnIndex,
																		)
																	}
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
