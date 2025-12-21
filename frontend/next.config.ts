import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  images: {
    remotePatterns: [
      new URL("https://picsum.photos/**"),
      new URL("https://lh3.googleusercontent.com/aida-public/*"),
    ],
  },
};

export default nextConfig;
