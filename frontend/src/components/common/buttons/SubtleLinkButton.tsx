import { Button } from "@mantine/core";
import { Link } from "react-router-dom";

interface SubtleLinkButtonProps {
  to: string;
  size?: string;
  children: React.ReactNode;
}

const SubtleLinkButton = ({ to, size, children }: SubtleLinkButtonProps) => {
  return (
    <Link to={to}>
      <Button size={size || "lg"} variant="subtle" color="#5D5FEF" radius="xs">
        {children}
      </Button>
    </Link>
  );
};

export default SubtleLinkButton;
