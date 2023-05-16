import { Center } from "@mantine/core";
import RegisterForm from "../components/login/RegisterForm";

const RegisterPage = () => {
  return (
    <>
      <Center >
        <RegisterForm isUser={false} />
      </Center>
    </>
  );
};

export default RegisterPage;
