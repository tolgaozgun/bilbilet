import { Button } from "@mantine/core";
import { Link } from "react-router-dom";
import { primaryButtonColor } from "../../../constants/colors";

interface SubtleLinkButtonProps {
  to: string;
  size?: string;
  children: React.ReactNode;
}

const SubtleLinkButton = ({ to, size, children }: SubtleLinkButtonProps) => {
  return (
    <Link to={to}>
      <Button
        size={size || "lg"}
        variant="subtle"
        color={primaryButtonColor}
        radius="xs"
      >
        {children}
      </Button>
    </Link>
  );
};

export default SubtleLinkButton;
