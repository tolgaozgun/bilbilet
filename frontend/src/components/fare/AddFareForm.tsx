import {
  Card,
  Title,
  Flex,
  TextInput,
  Radio,
  Group,
  Select,
  NumberInput,
} from "@mantine/core";
import CustomElevatedButton from "../common/buttons/CustomElevatedButton";
import { primaryAccordionColor } from "../../constants/colors";
const fuelList = ["Gas", "Electric", "Hybrid", "Diesel"];
const carCategoryList = ["Small", "Medium", "Large", "SUV"];

const AddFareForm = () => {
  return (
    <Card
      padding={36}
      bg={primaryAccordionColor}
      withBorder
      radius="xl"
      shadow="xl"
    >
      <Title>Add A New Fare</Title>
      <Flex direction={"column"} gap={"xs"}>
        <form>
          <Flex direction={"column"} gap={"xs"}>
            <TextInput withAsterisk label="Brand" />
            <TextInput withAsterisk label="Model" />
            <TextInput withAsterisk label="Year" />
            <Radio.Group name="Gear" label="Gear" withAsterisk>
              <Group mt="xs">
                <Radio value="automatic" label="Automatic" />
                <Radio value="manual" label="Manual" />
              </Group>
            </Radio.Group>
            <Select withAsterisk label="Fuel" clearable data={fuelList} />
            <Select
              withAsterisk
              label="Car Category"
              clearable
              data={carCategoryList}
            />
            <TextInput withAsterisk label="Image URL"></TextInput>
            <NumberInput
              withAsterisk
              hideControls
              label="Price Per Day"
            ></NumberInput>
          </Flex>
        </form>
        <CustomElevatedButton text={"Add Car For Rent"}></CustomElevatedButton>
      </Flex>
    </Card>
  );
};

export default AddFareForm;
