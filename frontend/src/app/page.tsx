"use client";
import styles from "./page.module.css";
import { Group } from "@mantine/core";
import RegisterForm from "@/components/RegisterForm";
import LoginForm from "@/components/LoginForm";
import CarRentSearchBar from "@/components/CarRentSearchBar";

export default function Home() {
  return (
    <main className={styles.main}>
      <Group>
        <CarRentSearchBar></CarRentSearchBar>
        <Group>
          <RegisterForm name={"Company Name"} isUser={false}></RegisterForm>
          <RegisterForm name={"Name"} isUser={true}></RegisterForm>
          <LoginForm></LoginForm>
        </Group>
      </Group>
    </main>
  );
}
