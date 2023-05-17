import {
  Button,
  Card,
  Flex,
  Group,
  NumberInput,
  PasswordInput,
  Select,
  Stack,
  TextInput,
  Title,
} from "@mantine/core";
import SubtleLinkButton from "../common/buttons/SubtleLinkButton";
import { primaryButtonColor } from "../../constants/colors";

const RegisterCompanyForm = () => {
  return (
    <Card
      withBorder
      radius="xl"
      shadow="xl"
      p={48}
      sx={{ minWidth: 400 }}
      mx="auto"
    >
      <Stack spacing={"xl"}>
        <Title size="32px" align="center">
          Register
        </Title>
        <form>
          <Flex direction={"column"} gap={"xs"}>
            <Flex direction={"row"} gap={"xs"}>
              <TextInput label="Company Name" />
              <Select
                label="Company Type"
                data={["Transportation", "Car Rental", "Hotel"]}
              />
            </Flex>
            <TextInput label="Company Email" />
            <PasswordInput label="Password" />
            <PasswordInput label="Confirm Password" />
            <NumberInput label="Telephone" hideControls />
            <TextInput label="Adress" />
            <TextInput label="Contact Information" />
            <TextInput label="Business Registration" />
            <Button bg={primaryButtonColor}>Register</Button>
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

export default RegisterCompanyForm;
