import { Injectable } from '@nestjs/common';
import { PassportStrategy } from '@nestjs/passport';
import { Request } from 'express';
import { Strategy } from 'passport-jwt';

const extractJwt = (req: Request): string | null => {
  let token: string | null = null;

  // Try Authorization header first (mobile/API clients)
  if (req?.headers?.authorization) {
    token = req.headers.authorization.replace('Bearer ', '');
  }

  // Fall back to cookie (browser)
  // eslint-disable-next-line @typescript-eslint/no-unsafe-member-access
  if (!token && req?.cookies?.access_token) {
    // eslint-disable-next-line @typescript-eslint/no-unsafe-assignment, @typescript-eslint/no-unsafe-member-access
    token = req.cookies.access_token;
  }

  return token!;
};

@Injectable()
export class JwtStrategy extends PassportStrategy(Strategy) {
  constructor() {
    super({
      jwtFromRequest: extractJwt,
      ignoreExpiration: false,
      secretOrKey: process.env.JWT_SECRET || 'your-secret-key',
    });
  }

  validate(payload: any) {
    // eslint-disable-next-line @typescript-eslint/no-unsafe-assignment, @typescript-eslint/no-unsafe-member-access
    return { id: payload.sub, email: payload.email };
  }
}
