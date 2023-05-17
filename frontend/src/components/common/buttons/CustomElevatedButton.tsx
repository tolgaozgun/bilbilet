import { Button } from "@mantine/core";
import { primaryButtonColor } from "../../../constants/colors";

interface CustomElevatedButtonProps {
  text: String;
}
const CustomElevatedButton = ({ text }: CustomElevatedButtonProps) => {
  return <Button bg={primaryButtonColor}>{text}</Button>;
};
export default CustomElevatedButton;
