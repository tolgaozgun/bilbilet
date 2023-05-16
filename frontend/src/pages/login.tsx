import { Center } from "@mantine/core";
import LoginForm from "../components/login/login-form";

const LoginPage = () => {
  return (
    <>
      <Center sx={{ height: "87vh" }}>
        <LoginForm />
      </Center>
    </>
  );
};

export default LoginPage;
