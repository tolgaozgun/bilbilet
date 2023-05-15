import React from "react";
import ReactDOM from "react-dom/client";
import { QueryClient, QueryClientProvider } from "react-query";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import ProviderWrapper from "./components/common/provider-wrapper";
import "./index.css";

const router = createBrowserRouter([]);
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
