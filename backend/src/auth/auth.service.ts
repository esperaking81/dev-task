import { Injectable } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import { PrismaService } from '../prisma/prisma.service';
import * as bcrypt from 'bcrypt';
import { User } from 'prisma/generated/client';

// Create a constant for user selection without password
export const userSelectWithoutPassword = {
  id: true,
  name: true,
  email: true,
  createdAt: true,
  updatedAt: true,
} as const;

type UserWithoutPassword = Omit<User, 'password'>;

@Injectable()
export class AuthService {
  constructor(
    private prisma: PrismaService,
    private jwtService: JwtService,
  ) {}

  async validateUser(
    email: string,
    password: string,
  ): Promise<UserWithoutPassword | null> {
    const user = await this.prisma.user.findUnique({
      where: { email },
      select: {
        id: true,
        name: true,
        email: true,
        createdAt: true,
        updatedAt: true,
        password: true,
      },
    });

    if (user && (await bcrypt.compare(password, user.password))) {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const { password, ...result } = user;
      return result;
    }

    return null;
  }

  async login(user: Partial<User>): Promise<{
    access_token: string;
    user: {
      id: string;
      name: string;
      email: string;
    };
  }> {
    const payload = { email: user.email, sub: user.id };
    const token = await this.jwtService.signAsync(payload);

    return {
      access_token: token,
      user: {
        id: user.id!,
        name: user.name!,
        email: user.email!,
      },
    };
  }

  async signUp(
    name: string,
    email: string,
    password: string,
  ): Promise<UserWithoutPassword> {
    const hashedPassword = await bcrypt.hash(password, 10);
    return await this.prisma.user.create({
      data: {
        name,
        email,
        password: hashedPassword,
      },
      select: userSelectWithoutPassword,
    });
  }

  async getMe(email: string): Promise<UserWithoutPassword> {
    const user = await this.prisma.user.findUnique({
      where: { email },
      select: userSelectWithoutPassword,
    });

    return user!;
  }
}
