import { IsString, IsOptional } from 'class-validator';
import { ApiProperty, ApiPropertyOptional } from '@nestjs/swagger';

export class CreateTagDto {
  @ApiProperty({ description: 'Tag name' })
  @IsString()
  name: string;

  @ApiPropertyOptional({ description: 'Tag color in hex format' })
  @IsOptional()
  @IsString()
  color?: string;
}

export class UpdateTagDto {
  @ApiPropertyOptional({ description: 'Tag name' })
  @IsOptional()
  @IsString()
  name?: string;

  @ApiPropertyOptional({ description: 'Tag color in hex format' })
  @IsOptional()
  @IsString()
  color?: string;
}
