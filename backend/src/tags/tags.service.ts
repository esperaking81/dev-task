import { Injectable } from '@nestjs/common';
import { PrismaService } from '../prisma/prisma.service';
import { CreateTagDto, UpdateTagDto } from './dto/tag.dto';

@Injectable()
export class TagsService {
  constructor(private prisma: PrismaService) {}

  async create(createTagDto: CreateTagDto) {
    return await this.prisma.tag.create({
      data: createTagDto,
    });
  }

  async findAll() {
    return await this.prisma.tag.findMany({
      orderBy: [{ name: 'asc' }],
    });
  }

  async findOne(id: string) {
    const tag = await this.prisma.tag.findUnique({
      where: { id },
      include: {
        tasks: true,
      },
    });

    if (!tag) {
      throw new Error('Tag not found');
    }

    return tag;
  }

  async update(id: string, updateTagDto: UpdateTagDto) {
    const existingTag = await this.prisma.tag.findUnique({
      where: { id },
    });

    if (!existingTag) {
      throw new Error('Tag not found');
    }

    return await this.prisma.tag.update({
      where: { id },
      data: updateTagDto,
    });
  }

  async remove(id: string) {
    const existingTag = await this.prisma.tag.findUnique({
      where: { id },
    });

    if (!existingTag) {
      throw new Error('Tag not found');
    }

    return await this.prisma.tag.delete({
      where: { id },
    });
  }
}
