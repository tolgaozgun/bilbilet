import { Center, Flex, Title } from "@mantine/core";
import CarRentSearchBar from "../components/car-rental/car-search-bar";
import CarInfoCard from "../components/car-rental/car-info-card";
import CarFilter from "../components/car-rental/car-filter";

const SearchRentCarPage = () => {
  return (
    <>
      <Center>
        <Flex direction={"column"} align={"start"} gap={"xl"}>
          <Title>Rent A Car</Title>
          <CarRentSearchBar></CarRentSearchBar>
          <Flex direction={"row"} gap={"xl"}>
            <CarFilter></CarFilter>
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
      </Center>
    </>
  );
};

export default SearchRentCarPage;
