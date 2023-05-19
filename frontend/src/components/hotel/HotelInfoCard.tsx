import { Card, Flex, Title, Image, Text, Rating } from "@mantine/core";
import { IconBuilding, IconPhone } from "@tabler/icons-react";
import CustomElevatedButton from "../common/buttons/CustomElevatedButton";
interface HotelInfoCardProps {
  hotelName: string;
  avgPrice: number;
  rating: number;
  websiteUrl: string;
  telephone: string;
}
const HotelInfoCard = ({
  hotelName,
  avgPrice,
  rating,
  telephone,
  websiteUrl,
}: HotelInfoCardProps) => {
  return (
    <Card shadow="xl" withBorder radius={"lg"}>
      <Flex direction={"column"} gap={"xs"} align={"start"}>
        <Flex direction={"row"} gap={"sm"} align={"center"}>
          <IconBuilding></IconBuilding>
          <Title order={3}>{hotelName}</Title>
        </Flex>
        <Flex direction={"row"} gap={"md"} align={"center"}>
          <Image
            height={160}
            src="https://images.unsplash.com/photo-1527004013197-933c4bb611b3?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=720&q=80"
            alt="Norway"
          />
          <Flex direction={"column"} gap={"sm"} align={"center"}>
            <Text>{avgPrice} TRY per night</Text>
            <Rating
              readOnly={true}
              value={rating}
              fractions={2}
              emptySymbol={<IconBuilding color="gray" />}
              fullSymbol={<IconBuilding color="blue" />}
            />
            <Flex direction={"row"} gap={"xs"}>
              <IconPhone></IconPhone>
              <Text>{telephone}</Text>
            </Flex>
          </Flex>
          <CustomElevatedButton
            text={"Go Hotel Website"}
          ></CustomElevatedButton>
        </Flex>
      </Flex>
    </Card>
  );
};
export default HotelInfoCard;
