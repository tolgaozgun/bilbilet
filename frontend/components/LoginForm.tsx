import {
    Button,
    Checkbox,
    Flex,
    Group,
    PasswordInput,
    Stack,
    TextInput,
  } from "@mantine/core";
  
  const LoginForm = () => {
    return (
      <form>
        <Flex direction={"column"} gap={"xs"}>
          <TextInput label="Email" placeholder="Your Email" />
          <PasswordInput
            label="Password"
            placeholder="Your password"
          ></PasswordInput>
          <Flex direction={"row"} justify={"space-between"}>
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
          <Button styles={(theme)=>({
              root:{
                  backgroundColor:"#5D5FEF"
              }
          })} loaderPosition="left">Login</Button>
          <Button styles={(theme)=>({
              root:{
                  backgroundColor:"#5D5FEF"
              }
          })} >Register</Button>
          <Group position="center">{}</Group>
        </Flex>
      </form>
    );
  };
  
  export default LoginForm;
  