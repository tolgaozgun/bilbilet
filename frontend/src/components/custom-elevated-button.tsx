import { Button } from "@mantine/core";

interface CustomElevatedButtonProps{
    text: String
}
const CustomElevatedButton = ({text}: CustomElevatedButtonProps) => {
    return (<Button
        styles={(theme) => ({
          root: {
            backgroundColor: "#5D5FEF",
          },
        })}
      >
        {text}
      </Button>);
}
export default CustomElevatedButton;