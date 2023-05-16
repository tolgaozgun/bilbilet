import { Center, Flex, Title } from "@mantine/core";
import CarFilter from "../components/car-rental/CarFilter";
import CarInfoCard from "../components/car-rental/CarInfoCard";
import CarRentSearchBar from "../components/car-rental/CarSearchBar";

const SearchRentCarPage = () => {
  return (
    <Flex direction={"column"} align={"start"} gap={"xl"}>
      <Title>Rent A Car</Title>
      <CarRentSearchBar></CarRentSearchBar>
      <Flex direction={"row"} gap={"xl"}>
        <CarFilter></CarFilter>
        <Flex direction={"column"} gap={"xl"}>
          <CarInfoCard
            carName={"Hyundai Kona"}
            seat={5}
            fuel={"Gas"}
            gear={"Automatic"}
            dailyPrice={1200}
            totalPrice={3600}
          />
          <CarInfoCard
            carName={"Hyundai Kona"}
            seat={5}
            fuel={"Gas"}
            gear={"Automatic"}
            dailyPrice={1200}
            totalPrice={3600}
          />
          <CarInfoCard
            carName={"Hyundai Kona"}
            seat={5}
            fuel={"Gas"}
            gear={"Automatic"}
            dailyPrice={1200}
            totalPrice={3600}
          />
        </Flex>
      </Flex>
    </Flex>
  );
};

export default SearchRentCarPage;
