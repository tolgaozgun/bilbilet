import { Card, Image, Flex, Title, SimpleGrid, Text, Center } from "@mantine/core";
import CustomElevatedButton from "./custom-elevated-button";
import {
  IconGasStation,
  IconManualGearbox,
  IconUser,
} from "@tabler/icons-react";
interface CarInfoCardProps {
  carName: String;
  seat: number;
  fuel: String;
  gear: String;
  dailyPrice: number;
  totalPrice: number;
}
const CarInfoCard = ({
  carName,
  seat,
  fuel,
  gear,
  dailyPrice,
  totalPrice,
}: CarInfoCardProps) => {
  return (
    <Card shadow="xl" withBorder radius={"lg"}>
      <Center>
        <Title>{carName}</Title>
      </Center>
      <SimpleGrid cols={3} spacing={"lg"}>
        <Image
          height={160}
          src="https://images.unsplash.com/photo-1527004013197-933c4bb611b3?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=720&q=80"
          alt="Norway"
        />
        <SimpleGrid cols={2} spacing="lg" verticalSpacing={"xs"}>
          <Flex direction={"row"} gap={"xs"}>
            <IconUser></IconUser>
            <Text> {seat} seats</Text>
          </Flex>
          <Flex direction={"row"} gap={"xs"}>
            <IconGasStation></IconGasStation>
            <Text>{fuel}</Text>
          </Flex>
          <Flex direction={"row"} gap={"xs"}>
            <IconManualGearbox></IconManualGearbox>
            <Text>{gear}</Text>
          </Flex>
        </SimpleGrid>
        <Flex direction={"column"} gap={"sm"}>
          <Text color="red">TRY {dailyPrice} per day</Text>
          <Text color="red">TRY {totalPrice} total</Text>
          <CustomElevatedButton text={"Get Deal"}></CustomElevatedButton>
        </Flex>
      </SimpleGrid>
    </Card>
  );
};
export default CarInfoCard;
