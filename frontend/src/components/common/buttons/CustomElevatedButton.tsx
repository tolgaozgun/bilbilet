import { Button } from "@mantine/core";
import { primaryButtonColor } from "../../../constants/colors";

interface CustomElevatedButtonProps {
  text: string;
}
const CustomElevatedButton = ({ text }: CustomElevatedButtonProps) => {
  return <Button bg={primaryButtonColor}>{text}</Button>;
};
export default CustomElevatedButton;
