import { Flex, Button, Select } from "@mantine/core";
import { DatePickerInput, TimeInput } from "@mantine/dates";
import { useState } from "react";
import { primaryButtonColor } from "../../constants/colors";

const CarRentSearchBar = () => {
  const [searchValue, onSearchChange] = useState("");
  const [value, setValue] = useState<[Date | null, Date | null]>([null, null]);
  return (
    <Flex direction={"row"} gap={"xs"} align={"end"}>
      <Select
        label="Pick-up Location"
        onSearchChange={onSearchChange}
        searchValue={searchValue}
        searchable
        nothingFound="No location found"
        data={["Ankara", "Antalya", "İzmir"]}
        allowDeselect
        clearable
      ></Select>
      <DatePickerInput
        type="range"
        label="Pick-up Dates"
        placeholder="Pick-up Dates"
        value={value}
        onChange={setValue}
        mx="auto"
        maw={400}
      />
      <TimeInput label="Pick-up time" maw={400} mx="auto" />
      <TimeInput label="Drop-off time" maw={400} mx="auto" />
      <Button bg={primaryButtonColor}>Search</Button>
    </Flex>
  );
};
export default CarRentSearchBar;
