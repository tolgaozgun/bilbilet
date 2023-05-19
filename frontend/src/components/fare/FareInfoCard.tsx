import { Card, Flex, Title, Text } from "@mantine/core";
import { IconArrowRight, IconPlane } from "@tabler/icons-react";
import CustomElevatedButton from "../common/buttons/CustomElevatedButton";
interface FareInfoCardProps {
  companyName: string;
  departureTime: string;
  arrivalTime: string;
  departureLocation: string;
  arrivalLocation: string;
  departureABB: string;
  arrivalABB: string;
  duration: string;
  price: number;
}
const FareInfoCard = ({
  companyName,
  departureTime,
  arrivalTime,
  departureLocation,
  arrivalLocation,
  departureABB,
  arrivalABB,
  duration,
  price,
}: FareInfoCardProps) => {
  return (
    <Card shadow="xl" withBorder radius={"lg"}>
      <Flex direction={"column"} gap={"xs"} align={"start"}>
        <Flex direction={"row"} gap={"sm"} align={"center"}>
          <IconPlane></IconPlane>
          <Title order={3}>{companyName}</Title>
        </Flex>
        <Flex direction={"row"} gap={"sm"}>
          <Flex direction={"column"} gap={"xs"} align={"center"}>
            <Text fw={700}>{departureTime}</Text>
            <Text>{departureABB}</Text>
            <Text>{departureLocation}</Text>
          </Flex>

          <Flex
            direction={"column"}
            gap={0}
            align={"center"}
            justify={"center"}
          >
            <Text>{duration}</Text>
            <IconArrowRight size={40}></IconArrowRight>
          </Flex>

          <Flex direction={"column"} gap={"xs"} align={"center"}>
            <Text fw={700}>{arrivalTime}</Text>
            <Text>{arrivalABB}</Text>
            <Text>{arrivalLocation}</Text>
          </Flex>

          <CustomElevatedButton text={"Buy Ticket"}></CustomElevatedButton>
        </Flex>
      </Flex>
    </Card>
  );
};
export default FareInfoCard;
