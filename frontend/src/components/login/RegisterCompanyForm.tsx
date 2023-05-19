import {
	Button,
	Card,
	Flex,
	NumberInput,
	PasswordInput,
	Select,
	Stack,
	TextInput,
	Title,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { useNavigate } from 'react-router-dom';
import { primaryButtonColor } from '../../constants/colors';
import { registerCompany } from '../../services/auth';
import { CompanyType, RegisterCompany } from '../../types';
import SubtleLinkButton from '../common/buttons/SubtleLinkButton';

const RegisterCompanyForm = () => {
	const form = useForm({
		initialValues: {
			companyName: '',
			companyType: '',
			companyEmail: '',
			password: '',
			confirmPassword: '',
			telephone: '',
			address: '',
			contactInformation: '',
			businessRegistration: '',
		},
		validate: {
			companyName: (value) =>
				value === '' ? 'Company name cannot be left empty.' : null,
			companyType: (value) =>
				value === '' ? 'Company type cannot be left empty.' : null,
			companyEmail: (value) => (/^\S+@\S+$/.test(value) ? null : 'Invalid email.'),
			password: (value) => (value === '' ? 'Password cannot be left empty.' : null),
			confirmPassword: (value, values) =>
				value !== values.password ? 'Passwords did not match' : null,
			telephone: (value) =>
				/^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/.test(value)
					? null
					: 'Invalid phone number',
			address: (value) => (value === '' ? 'Address cannot be left empty.' : null),
			contactInformation: (value) =>
				value === '' ? 'Contact information cannot be left empty.' : null,
			businessRegistration: (value) =>
				value === '' ? 'Business registration cannot be left empty.' : null,
		},
	});
	const navigate = useNavigate();

	const onRegister = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}

		// Send registration request
		const res = await registerCompany(form.values as RegisterCompany);

		// Registration unsuccessful
		// TODO

		// Registration successful

		// Automatically login

		// Redirect to search page
		navigate('/search-fare');
	};

	return (
		<Card withBorder radius="xl" shadow="xl" p={48} sx={{ minWidth: 400 }} mx="auto">
			<Stack spacing={'xl'}>
				<Title size="32px" align="center">
					Register
				</Title>
				<form>
					<Flex direction={'column'} gap={'xs'}>
						<Flex direction={'row'} gap={'xs'}>
							<TextInput
								label="Company Name"
								{...form.getInputProps('companyName')}
							/>
							<Select
								clearable
								label="Company Type"
								data={Object.values(CompanyType)}
								{...form.getInputProps('companyType')}
							/>
						</Flex>
						<TextInput
							label="Company Email"
							{...form.getInputProps('companyEmail')}
						/>
						<PasswordInput
							label="Password"
							{...form.getInputProps('password')}
						/>
						<PasswordInput
							label="Confirm Password"
							{...form.getInputProps('confirmPassword')}
						/>
						<TextInput
							label="Telephone"
							{...form.getInputProps('telephone')}
						/>
						<TextInput label="Adress" {...form.getInputProps('address')} />
						<TextInput
							label="Contact Information"
							{...form.getInputProps('contactInformation')}
						/>
						<TextInput
							label="Business Registration"
							{...form.getInputProps('businessRegistration')}
						/>
						<Button onClick={onRegister} bg={primaryButtonColor}>
							Register
						</Button>
						<SubtleLinkButton to="/login" size="sm">
							Already have an account? Login
						</SubtleLinkButton>
					</Flex>
				</form>
			</Stack>
		</Card>
	);
};

export default RegisterCompanyForm;
