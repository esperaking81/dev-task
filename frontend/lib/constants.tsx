import { CheckSquare, LayoutDashboard, Settings } from "lucide-react";

export const NAV_ITEMS = [
  { icon: LayoutDashboard, label: "Dashboard", path: "/" },
  { icon: CheckSquare, label: "My Tasks", path: "/my-tasks" },
  { icon: Settings, label: "Settings", path: "/settings" },
];

export const SIGN_UP_BG = "";

export const authLayoutData = {
  image: {
    login: {
      position: "left",
      url: "https://lh3.googleusercontent.com/aida-public/AB6AXuAeHRt3kpXUNLfXyC2zR6kxoAmDJwt96ne_UqyKQsVCjcqB0r5C6UBBPYjVf1osoRPOTJKCsnThGRjciP81XhI-JRh2PjFKbFozqN5BPgv-buyIK24Zd-dQ_k6jUXBTHOoVzZhsqdyNnCI4K3XgckcCQhxItcXd8-eN0fiNVyeNMNwqMMEQ7Qr1z-qGf6_iIt4_n9YhPYRH0IxkR5-j4Lhdw9iwCzMbQ15tg4AwwvXu7P7TBDwVrhZ_RXf4DezNd6VDSzbBnSzRrg",
      alt: "Abstract dark network gradient representing AI technology and connections",
    },
    signup: {
      position: "right",
      url: "https://lh3.googleusercontent.com/aida-public/AB6AXuCKNA9wv5DbEQIMWBQVJ3UwhydPnIWUoTthrAT_idrjudClkXhKHicLgVglZOW3D4vDsf8YOzvqyb0wM5L82p21T8vqsgDk5V48-upLzDEZ69_N8dmcc12-w73hIXYPyMdiRh3Z34aX_WrOcLdnrYVtz-L-A92Dd5ZZ8EwbKb5UCSzaSCKNlfuRcnbJEb99ulvhl5dl9wIPfBRd9SBJ8d778mv52hZwZyvxPl7fZaN4hh_sSwXBGUNv32CZRpUEnWboDOd8cVJWVw",
      alt: "Abstract dark blue cyber security technology pattern with glowing nodes",
    },
  },
};
