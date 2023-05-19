import { Accordion } from "@mantine/core";
import { primaryAccordionColor } from "../../constants/colors";

interface CustomAccordionItemProps {
  value: string;
  children?: React.ReactNode;
}
const CustomAccordionItem = ({ value, children }: CustomAccordionItemProps) => {
  return (
    <Accordion.Item bg={primaryAccordionColor} value={value}>
      <Accordion.Control bg={primaryAccordionColor} c="black">
        {value}
      </Accordion.Control>
      <Accordion.Panel bg={primaryAccordionColor} c="black">
        {children}
      </Accordion.Panel>
    </Accordion.Item>
  );
};
export default CustomAccordionItem;
