import {
	Button,
	Card,
	Flex,
	Group,
	PasswordInput,
	Stack,
	TextInput,
	Title,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { useNavigate } from 'react-router-dom';
import { primaryButtonColor } from '../../constants/colors';
import { useRegisterUser } from '../../hooks/auth';
import { RegisterUser } from '../../types';
import { isErrorResponse } from '../../utils/utils';
import SubtleLinkButton from '../common/buttons/SubtleLinkButton';

const RegisterUserForm = () => {
	const form = useForm({
		initialValues: {
			name: '',
			surname: '',
			email: '',
			password: '',
			confirmPassword: '',
			telephone: '',
			TCK: '',
		},
		validate: {
			name: (value) => (value === '' ? 'Name ame cannot be left empty.' : null),
			surname: (value) => (value === '' ? 'Surname cannot be left empty.' : null),
			email: (value) => (/^\S+@\S+$/.test(value) ? null : 'Invalid email.'),
			password: (value) => (value === '' ? 'Password cannot be left empty.' : null),
			TCK: (value) => (value === '' ? 'National ID cannot be left empty.' : null),
			confirmPassword: (value, values) =>
				value !== values.password ? 'Passwords did not match' : null,
			telephone: (value) =>
				/^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/.test(value)
					? null
					: 'Invalid phone number',
		},
	});
	const navigate = useNavigate();
	const { register } = useRegisterUser();

	const onRegister = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}

		const res = await register(form.values as RegisterUser);
		if (isErrorResponse(res)) {
			notifications.show({
				id: 'registration-fail',
				title: 'Registration failed!',
				message: res.msg,
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
			});
			return;
		}

		notifications.show({
			id: 'registration-success',
			title: 'Registration successful!',
			message:
				'You have successfully registered! We are redirecting you to the main page...',
			autoClose: 5000,
			withCloseButton: true,
			style: { backgroundColor: 'green' },
		});
		navigate('/search-fare');
	};

	return (
		<Card withBorder radius="xl" shadow="xl" p={48} sx={{ minWidth: 400 }} mx="auto">
			<Stack spacing="xl">
				<Title size="32px" align="center">
					Register
				</Title>
				<form>
					<Flex direction="column" gap="xs">
						<Flex direction="row" gap="xs">
							<TextInput label="Name" {...form.getInputProps('name')} />
							<TextInput
								label="Surname"
								{...form.getInputProps('surname')}
							/>
						</Flex>
						<TextInput label="Email" {...form.getInputProps('email')} />
						<PasswordInput
							label="Password"
							{...form.getInputProps('password')}
						/>
						<PasswordInput
							label="Confirm Password"
							{...form.getInputProps('confirmPassword')}
						/>
						<TextInput label="National ID" {...form.getInputProps('TCK')} />
						<TextInput
							label="Telephone"
							{...form.getInputProps('telephone')}
						/>
						<Button bg={primaryButtonColor} onClick={onRegister}>
							Register
						</Button>
						<SubtleLinkButton to="/login" size="sm">
							Already have an account? Login
						</SubtleLinkButton>
						<Group position="center">{}</Group>
					</Flex>
				</form>
			</Stack>
		</Card>
	);
};

export default RegisterUserForm;
