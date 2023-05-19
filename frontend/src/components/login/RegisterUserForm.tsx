import {
  Button,
  Card,
  Flex,
  Group,
  NumberInput,
  PasswordInput,
  Stack,
  TextInput,
  Title,
} from "@mantine/core";
import { useForm } from "@mantine/form";
import { primaryButtonColor } from "../../constants/colors";
import SubtleLinkButton from "../common/buttons/SubtleLinkButton";

const RegisterUserForm = () => {
  const form = useForm({
    initialValues: {
      name: "",
      surname: "",
      email: "",
      password: "",
      confirmPassword: "",
      telephone: "",
    },
    validate: {
      name: (value) => (value === "" ? "Name ame cannot be left empty." : null),
      surname: (value) =>
        value === "" ? "Surname cannot be left empty." : null,
      email: (value) => (/^\S+@\S+$/.test(value) ? null : "Invalid email."),
      password: (value) =>
        value === "" ? "Password cannot be left empty." : null,
      confirmPassword: (value, values) =>
        value !== values.password ? "Passwords did not match" : null,
      telephone: (value) =>
        /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/.test(value)
          ? null
          : "Invalid phone number",
    },
  });

  const onRegister = () => {
    const validation = form.validate();
    if (validation.hasErrors) {
      return;
    }
    // TODO: Send registration request, show error message or confirm message,
    // automatically login and redirect to search page
  };

  return (
    <Card
      withBorder
      radius="xl"
      shadow="xl"
      p={48}
      sx={{ minWidth: 400 }}
      mx="auto"
    >
      <Stack spacing="xl">
        <Title size="32px" align="center">
          Register
        </Title>
        <form>
          <Flex direction="column" gap="xs">
            <Flex direction="row" gap="xs">
              <TextInput label="Name" {...form.getInputProps("name")} />
              <TextInput label="Surname" {...form.getInputProps("surname")} />
            </Flex>
            <TextInput label="Email" {...form.getInputProps("email")} />
            <PasswordInput
              label="Password"
              {...form.getInputProps("password")}
            />
            <PasswordInput
              label="Confirm Password"
              {...form.getInputProps("confirmPassword")}
            />
            <TextInput label="Telephone" {...form.getInputProps("telephone")} />
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
