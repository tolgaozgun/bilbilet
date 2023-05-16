import { Flex, RangeSlider } from "@mantine/core";
const marks = [
    { value: 0, label: '0%' },
    { value: 100, label: '100%' },
  ];
  
const CarFilter = () => {
  return (
    <Flex direction={"column"} gap={"xs"} >
      <RangeSlider defaultValue={[0, 100]} marks={marks}></RangeSlider>
    </Flex>
  );
};
export default CarFilter;
