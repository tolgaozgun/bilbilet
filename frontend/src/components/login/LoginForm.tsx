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
import { useNavigate } from "react-router-dom";
  
  const LoginForm = () => {
    const navigate = useNavigate()
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
          <Title size="36px" align="center">
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
                onClick={()=>{
                  
                }}
              >
                Login
              </Button>
              <Button
                styles={(theme) => ({
                  root: {
                    backgroundColor: "#5D5FEF",
                  },
                })}
                onClick={()=>{
                  navigate('/register')
                }}
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