import { Flex, Button, Select } from "@mantine/core";
import { useState } from "react";
import { primaryButtonColor } from "../../constants/colors";

const HotelSearchBar = () => {
  const [searchValue, onSearchChange] = useState("");
  return (
    <Flex direction={"row"} gap={"xs"} align={"end"}>
      <Select
        placeholder="Select Location"
        label="Location"
        onSearchChange={onSearchChange}
        searchValue={searchValue}
        searchable
        nothingFound="No location found"
        data={["Ankara", "Antalya", "İzmir"]}
        allowDeselect
        clearable
      ></Select>
      <Button bg={primaryButtonColor}>Search</Button>
    </Flex>
  );
};
export default HotelSearchBar;
