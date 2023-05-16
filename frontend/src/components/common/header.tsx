import { Group, Header, Title } from "@mantine/core";
import ToggleThemeIcon from "./toggle-theme-icon";

const AppHeader = () => {
  return (
    <Header height={80} p="lg">
      <Group position="apart">
        <Title color="blue" ml={20}>
          BilBilet
        </Title>
        <ToggleThemeIcon mr={50} />
      </Group>
    </Header>
  );
};

export default AppHeader;
