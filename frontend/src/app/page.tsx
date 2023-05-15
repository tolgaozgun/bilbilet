"use client";
import CarRentSearchBar from "@/src/components/CarRentSearchBar";
import LoginForm from "@/src/components/LoginForm";
import RegisterForm from "@/src/components/RegisterForm";
import { Group } from "@mantine/core";
import Link from "next/link";
import styles from "./page.module.css";

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
