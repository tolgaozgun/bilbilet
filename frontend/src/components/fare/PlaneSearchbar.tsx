import { Flex, Button, Select, NumberInput } from "@mantine/core";
import { DatePickerInput } from "@mantine/dates";
import { useState } from "react";
import { primaryButtonColor } from "../../constants/colors";

const PlaneSearchBar = () => {
  const [searchValue, onSearchChange] = useState("");
  return (
    <Flex direction={"row"} gap={"xs"} align={"end"}>
      <Select
        placeholder="Select Departure Location"
        label="Departure"
        onSearchChange={onSearchChange}
        searchValue={searchValue}
        searchable
        nothingFound="No location found"
        data={["Ankara", "Antalya", "İzmir"]}
        allowDeselect
        clearable
      ></Select>
      <DatePickerInput
        type="default"
        label="Departure Date"
        placeholder="Departure Date"
        mx="auto"
        maw={400}
      />
      <Select
        label="Arrival"
        placeholder="Select Arrival Location"
        onSearchChange={onSearchChange}
        searchValue={searchValue}
        searchable
        nothingFound="No location found"
        data={["Ankara", "Antalya", "İzmir"]}
        allowDeselect
        clearable
      ></Select>
      <DatePickerInput
        type="default"
        label="Arrival Date"
        placeholder="Arrival Date"
        mx="auto"
        maw={400}
      />
      <NumberInput label="Passenger" defaultValue={1} min={1}></NumberInput>
      <Button bg={primaryButtonColor}>Search</Button>
    </Flex>
  );
};
export default PlaneSearchBar;
