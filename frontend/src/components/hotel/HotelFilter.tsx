import {
  Accordion,
  Box,
  MultiSelect,
  RangeSlider,
  TextInput,
} from "@mantine/core";
import CustomAccordionItem from "../common/CustomAccordionItem";
const marks = [
  { value: 0, label: "0 TRY" },
  { value: 10000, label: "10000 TRY" },
];
const ratingMarks = [
  { value: 1, label: "1" },
  { value: 5, label: "5" },
];

const HotelFilter = () => {
  return (
    <Box miw={"10vw"}>
      <Accordion multiple variant="contained" radius="xs" bg={"#B5B4E8"}>
        <CustomAccordionItem value={"Avg Price"}>
          <RangeSlider defaultValue={[0, 10000]} marks={marks}></RangeSlider>
        </CustomAccordionItem>

        <CustomAccordionItem value={"Hotel Name"}>
          <TextInput label="Hotel Name" placeholder="Hotel Name"></TextInput>
        </CustomAccordionItem>
        {/* <CustomAccordionItem value={"Rating"}>
          <RangeSlider min={1} max={5} step={0.5}></RangeSlider>
        </CustomAccordionItem> */}
      </Accordion>
    </Box>
  );
};
export default HotelFilter;
