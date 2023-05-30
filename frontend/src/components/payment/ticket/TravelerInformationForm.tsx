import { Card, Stack, Switch, TextInput } from '@mantine/core';
import { UseFormReturnType } from '@mantine/form';
import { useState } from 'react';

interface TravelerInformationFormProps {
	form: UseFormReturnType<
		{
			name: string;
			lastName: string;
			passportNumber: string;
			TCK: string;
			email: string;
			phone: string;
		},
		(values: {
			name: string;
			lastName: string;
			passportNumber: string;
			TCK: string;
			email: string;
			phone: string;
		}) => {
			name: string;
			lastName: string;
			passportNumber: string;
			TCK: string;
			email: string;
			phone: string;
		}
	>;
}

const TravelerInformationForm = ({ form }: TravelerInformationFormProps) => {
	const [isForeigner, setIsForeigner] = useState(false);
	const onNationalityChange = () => {
		if (isForeigner) {
			form.setValues({ passportNumber: '' });
		} else {
			form.setValues({ TCK: '' });
		}
		setIsForeigner((prev) => !prev);
	};

	return (
		<Card miw={300} withBorder p={24} shadow="lg">
			<form>
				<Stack spacing="lg">
					<TextInput
						label="Name"
						placeholder="John"
						{...form.getInputProps('name')}
					/>
					<TextInput
						label="Last name"
						placeholder="Doe"
						{...form.getInputProps('lastName')}
					/>
					<Stack spacing="xs">
						{isForeigner ? (
							<TextInput
								label="Passport number"
								placeholder="12345678910"
								{...form.getInputProps('passportNumber')}
							/>
						) : (
							<TextInput
								label="National ID (TCK)"
								placeholder="12345678910"
								{...form.getInputProps('TCK')}
							/>
						)}
						<Switch
							label="I am a foreigner"
							size="xs"
							checked={isForeigner}
							onChange={onNationalityChange}
						/>
					</Stack>
					<TextInput
						label="Email"
						placeholder="example@gmail.com"
						{...form.getInputProps('email')}
					/>
					<TextInput
						label="Phone number"
						placeholder="5555555555"
						{...form.getInputProps('phone')}
					/>
				</Stack>
			</form>
		</Card>
	);
};

export default TravelerInformationForm;
