import {
	Button,
	Card,
	Flex,
	PasswordInput,
	Stack,
	TextInput,
	Title,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { useNavigate } from 'react-router-dom';
import { primaryButtonColor } from '../../constants/colors';
import { useLogin } from '../../hooks/auth';
import { isErrorResponse } from '../../utils/utils';
import SubtleLinkButton from '../common/buttons/SubtleLinkButton';

const LoginForm = () => {
	const { login } = useLogin();
	const navigate = useNavigate();
	const form = useForm({
		initialValues: {
			email: '',
			password: '',
		},

		validate: {
			email: (value) => (/^\S+@\S+$/.test(value) ? null : 'Invalid email.'),
			password: (value) =>
				value === '' ? "Can't leave password field empty" : null,
		},
	});

	const onLogin = async () => {
		// Validate input fields
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}

		const { email, password } = form.values;
		const res = await login(email, password);
		if (isErrorResponse(res)) {
			notifications.show({
				id: 'login-fail',
				title: 'Login failed!',
				message: res.msg,
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
			});
			return;
		}

		notifications.show({
			id: 'login-success',
			title: 'Login successful!',
			message: 'You have successfully logged in!',
			autoClose: 5000,
			withCloseButton: true,
			style: { backgroundColor: 'green' },
		});
		navigate('/search-fare');
	};

	return (
		<Card withBorder radius="xl" shadow="xl" p={48} sx={{ minWidth: 350 }} mx="auto">
			<Stack spacing={'md'}>
				<Title size="28px" align="center">
					Log in to your account
				</Title>
				<SubtleLinkButton to="/register">
					Don't have an account? Register
				</SubtleLinkButton>
				<form>
					<Flex direction={'column'} gap={'xs'}>
						<TextInput
							label="Email"
							placeholder="your@email.com"
							{...form.getInputProps('email')}
						/>
						<PasswordInput
							label="Password"
							placeholder="Your password"
							{...form.getInputProps('password')}
						/>
						<Button
							bg={primaryButtonColor}
							loaderPosition="left"
							onClick={onLogin}
						>
							Login
						</Button>
						<Flex
							direction={'row'}
							justify={'space-between'}
							align={'center'}
						>
							<SubtleLinkButton to="/forgot-password" size="sm">
								Forgot Password?
							</SubtleLinkButton>
						</Flex>
					</Flex>
				</form>
			</Stack>
		</Card>
	);
};

export default LoginForm;
