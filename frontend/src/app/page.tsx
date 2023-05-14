"use client";
import Image from "next/image";
import styles from "./page.module.css";
import {
  Flex,
  TextInput,
  Select,
  PasswordInput,
  NumberInput,
  Button,
  Group,
  Card,
  Center,
  Stack,
  Title,
} from "@mantine/core";
import RegisterForm from "@/components/RegisterForm";

export default function Home() {
  return (
    <main className={styles.main}>
      <Center sx={{ height: "87vh" }}>
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
              Register
            </Title>
            <RegisterForm name={"Name"} isUser={false}></RegisterForm>
          </Stack>
        </Card>
      </Center>
    </main>
  );
}
