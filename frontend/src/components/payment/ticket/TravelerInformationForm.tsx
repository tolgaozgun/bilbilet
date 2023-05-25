import { Card, Stack, TextInput } from '@mantine/core';
import { useForm } from '@mantine/form';
import { useUser } from '../../../hooks/useUser';

const TravelerInformationForm = () => {
	const user = useUser();

	const form = useForm({
		initialValues: {
			name: user?.name || '',
			lastName: user?.surname || '',
			TCK: user?.TCK || '',
			email: user?.email || '',
			phone: user?.phone || '',
		},
		validate: {
			name: (value) => (value === '' ? 'This field cannot be left empty' : null),
			lastName: (value) =>
				value === '' ? 'This field cannot be left empty' : null,
			TCK: (value) => (value === '' ? 'This field cannot be left empty' : null),
			email: (value) => (value === '' ? 'This field cannot be left empty' : null),
			phone: (value) => (value === '' ? 'This field cannot be left empty' : null),
		},
	});

	return (
		<Card withBorder>
			<form>
				<Stack spacing="md">
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
					<TextInput
						label="National ID (TCK) / Passport number"
						placeholder="12345678910"
						{...form.getInputProps('TCK')}
					/>
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
