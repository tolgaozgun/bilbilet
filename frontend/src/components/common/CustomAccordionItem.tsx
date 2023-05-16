import { Accordion, Flex, Checkbox } from "@mantine/core";

interface CustomAccordionItemProps {
  value: string;
  children?: React.ReactNode;
}
const CustomAccordionItem = ({ value, children }: CustomAccordionItemProps) => {
  return (
    <Accordion.Item bg={"#B5B4E8"} value={value}>
      <Accordion.Control bg={"#B5B4E8"} c="black">
        {value}
      </Accordion.Control>
      <Accordion.Panel bg={"#B5B4E8"} c="black">
        {children}
      </Accordion.Panel>
    </Accordion.Item>
  );
};
export default CustomAccordionItem;
