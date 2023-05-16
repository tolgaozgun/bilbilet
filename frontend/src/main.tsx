import React from "react";
import ReactDOM from "react-dom/client";
import { QueryClient, QueryClientProvider } from "react-query";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import ProviderWrapper from "./components/common/provider-wrapper";
import "./index.css";
import LoginPage from "./pages/login";
import Layout from "./layout";
import RegisterPage from "./pages/register";

const router = createBrowserRouter([
  {
    children: [
      {
        path: "/",
        element: <Layout />,
        children: [
          {
            path: "/login",
            element: <LoginPage />,
          },
          {
            path: "/register",
            element: <RegisterPage />,
          },
        ],
      },
    ],
  },
]);
const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>
    <QueryClientProvider client={queryClient}>
      <ProviderWrapper>
        <RouterProvider router={router} />
      </ProviderWrapper>
    </QueryClientProvider>
  </React.StrictMode>
);
