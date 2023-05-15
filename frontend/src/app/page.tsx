"use client";
import CarRentSearchBar from "@/components/CarRentSearchBar";
import LoginForm from "@/components/LoginForm";
import RegisterForm from "@/components/RegisterForm";
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
