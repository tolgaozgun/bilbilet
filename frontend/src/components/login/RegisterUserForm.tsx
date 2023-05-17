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
import SubtleLinkButton from "../common/buttons/SubtleLinkButton";

const RegisterUserForm = () => {
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
              <TextInput label="Name" />
              <TextInput label="Surname" />
            </Flex>
            <TextInput label="Email" />
            <PasswordInput label="Password" />
            <PasswordInput label="Confirm Password" />
            <NumberInput label="Telephone" hideControls />
            <Button bg={"#5D5FEF"}>Register</Button>
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
