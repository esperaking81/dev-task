"use client";

import { Button } from "@/components/ui/button";
import {
  Field,
  FieldDescription,
  FieldGroup,
  FieldLabel,
} from "@/components/ui/field";
import { Input } from "@/components/ui/input";
import { cn } from "@/lib/utils";
import { LoadingSpinner } from "./ui/loading-spinner";
import { Logo } from "./ui/logo";
import { login } from "@/lib/actions/auth";
import { useFormStatus } from "react-dom";
import { useState } from "react";
import { toast } from "sonner";

export function LoginForm({
  className,
  ...props
}: React.ComponentProps<"div">) {
  const [errorState, setErrorState] = useState<{
    email?: string;
    password?: string;
  }>({});

  const clientAction = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const formData = new FormData(e.currentTarget);
    const { errors, message } = await login(formData);

    if (message) {
      toast.error(message);
      return;
    }

    if (errors) {
      setErrorState({
        email: errors.email,
        password: errors.password,
      });
      return;
    }
  };

  return (
    <div className={cn("flex flex-col gap-6", className)} {...props}>
      <div className="flex items-center gap-2">
        <Logo />
        <div className="text-3xl font-medium">DevTask</div>
      </div>
      <div className="mt-4">
        <div className="text-3xl font-bold tracking-tight text-white">
          Welcome back
        </div>
        <div className="mt-2 text-sm text-text-secondary">
          Please enter your details to sign in.
        </div>
      </div>
      <form onSubmit={clientAction}>
        <FieldGroup>
          <EmailField error={errorState.email} />
          <PasswordField error={errorState.password} />
          <ForgotPassword />
          <Field>
            <SubmitButton />
            <SignUp />
          </Field>
        </FieldGroup>
      </form>
    </div>
  );
}

const EmailField = ({ error }: { error?: string }) => {
  const { pending } = useFormStatus();
  return (
    <Field>
      <FieldLabel htmlFor="email">Email</FieldLabel>
      <Input
        id="email"
        type="email"
        name="email"
        disabled={pending}
        placeholder="m@example.com"
        required
      />
      {error && <p className="text-red-500 text-sm">{error}</p>}
    </Field>
  );
};

const ForgotPassword = () => (
  <a
    href="#"
    className="ml-auto inline-block text-text-secondary text-sm underline-offset-4 hover:underline"
  >
    Forgot your password?
  </a>
);

const PasswordField = ({ error }: { error?: string }) => {
  const { pending } = useFormStatus();

  return (
    <Field>
      <FieldLabel htmlFor="password">Password</FieldLabel>
      <Input
        id="password"
        name="password"
        disabled={pending}
        type="password"
        required
      />
      {error && <p className="text-red-500 text-sm">{error}</p>}
    </Field>
  );
};

const SubmitButton = () => {
  const { pending } = useFormStatus();
  return (
    <Button className="mb-4" type="submit" disabled={pending}>
      {pending ? <LoadingSpinner /> : "Login"}
    </Button>
  );
};

const SignUp = () => (
  <FieldDescription className="text-center">
    Don&apos;t have an account?{" "}
    <a href="/signup" className="text-primary">
      Sign up for free
    </a>
  </FieldDescription>
);
