import {
    Button,
    Card,
    Checkbox,
    Flex,
    Group,
    PasswordInput,
    Stack,
    TextInput,
    Title,
  } from "@mantine/core";
  
  const LoginForm = () => {
    return (
      <Card
        withBorder
        radius="xl"
        shadow="xl"
        p={48}
        sx={{ minWidth: 350 }}
        mx="auto"
      >
        <Stack spacing={"xl"}>
          <Title color="black" size="36px" align="center">
            Login
          </Title>
  
          <form>
            <Flex direction={"column"} gap={"xs"}>
              <TextInput label="Email" placeholder="Your Email" />
              <PasswordInput
                label="Password"
                placeholder="Your password"
              ></PasswordInput>
              <Flex direction={"row"} justify={"space-between"} align={"center"}>
                <Checkbox
                  labelPosition="left"
                  label="Remember me"
                  radius="xs"
                  size="xs"
                />
                <Button variant="subtle" color="dark" radius="xs" compact>
                  Forgot Password
                </Button>
              </Flex>
              <Button
                styles={(theme) => ({
                  root: {
                    backgroundColor: "#5D5FEF",
                  },
                })}
                loaderPosition="left"
              >
                Login
              </Button>
              <Button
                styles={(theme) => ({
                  root: {
                    backgroundColor: "#5D5FEF",
                  },
                })}
              >
                Register
              </Button>
              <Group position="center">{}</Group>
            </Flex>
          </form>
        </Stack>
      </Card>
    );
  };
  
  export default LoginForm;