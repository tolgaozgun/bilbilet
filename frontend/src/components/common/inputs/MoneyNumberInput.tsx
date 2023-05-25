import { NumberInput } from '@mantine/core';

interface MoneyNumberInputProps {
	amount: number;
	setAmount?: (amount: number) => void;
	prefix?: string;
	disabled?: boolean;
}

const MoneyNumberInput = ({
	amount,
	setAmount,
	prefix,
	disabled,
}: MoneyNumberInputProps) => {
	const valuePrefix = prefix || 'TL';

	const formatter = (value: string) => {
		if (!Number.isNaN(parseFloat(value))) {
			return `${valuePrefix} ${value}`.replace(
				/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,
				',',
			);
		} else {
			return `${valuePrefix} `;
		}
	};

	return (
		<NumberInput
			label="Amount"
			disabled={disabled || false}
			value={amount}
			onChange={setAmount}
			parser={(value) => value.replace(/\$\s?|(,*)/g, '')}
			formatter={formatter}
		/>
	);
};

export default MoneyNumberInput;
