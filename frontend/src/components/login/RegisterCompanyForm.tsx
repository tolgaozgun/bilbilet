import {
	Button,
	Card,
	Flex,
	PasswordInput,
	Select,
	Stack,
	TextInput,
	Title,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import { useNavigate } from 'react-router-dom';
import { primaryButtonColor } from '../../constants/colors';
import { useRegisterCompany } from '../../hooks/auth';
import {
	CompanyModel,
	CompanyType,
	RegisterCompany,
	UserModel,
	UserType,
} from '../../types';
import { isErrorResponse } from '../../utils/utils';
import SubtleLinkButton from '../common/buttons/SubtleLinkButton';
import { notifications } from '@mantine/notifications';

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
	const { register } = useRegisterCompany();

	const onRegister = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}

		const user: UserModel = {
			userId: 0,
			name: form.values.companyName,
			surname: '',
			email: form.values.companyEmail,
			password: form.values.password,
			telephone: form.values.telephone,
			userType: UserType.Company,
		};
		const company: CompanyModel = {
			company_id: 0,
			company_title: form.values.companyName,
			address: form.values.address,
			contact_information: form.values.contactInformation,
			business_registration: form.values.businessRegistration,
			type: form.values.companyType,
			balance: 0,
			user_id: 0,
		};
		const registerInfo: RegisterCompany = {
			user,
			company,
		};
		const res = await register(registerInfo);
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
		navigate('/login');
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
