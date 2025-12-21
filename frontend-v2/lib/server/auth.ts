import { serverFetcher } from "./api-client";
import { API_ENDPOINTS } from "../api/endpoints";
import * as z from "zod";

export const LoginDtoSchema = z.object({
  email: z.email("Invalid email"),
  password: z.string("Password is required"),
});

export type LoginDto = z.infer<typeof LoginDtoSchema>;

export const SignUpDtoSchema = z.object({
  name: z.string(),
  email: z.email(),
  password: z.string().check(z.minLength(8)),
});

export type SignUpDto = z.infer<typeof SignUpDtoSchema>;

export const authApi = {
  login: (credentials: LoginDto) =>
    serverFetcher(API_ENDPOINTS.auth.login, {
      method: "POST",
      body: JSON.stringify(credentials),
    }),
  signUp: (credentials: SignUpDto) =>
    serverFetcher(API_ENDPOINTS.auth.signUp, {
      method: "POST",
      body: JSON.stringify(credentials),
    }),
};
