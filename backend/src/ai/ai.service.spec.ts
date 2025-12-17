import { Test, TestingModule } from '@nestjs/testing';
import { HttpService } from '@nestjs/axios';
import { of } from 'rxjs';
import { firstValueFrom } from 'rxjs';
import { AiService } from './ai.service';

const mockFirstValueFrom = jest.fn();
jest.mock('rxjs', () => {
  const rxjs = jest.requireActual<typeof import('rxjs')>('rxjs');
  return {
    ...rxjs,
    firstValueFrom: mockFirstValueFrom,
  };
});

describe('AiService', () => {
  let service: AiService;
  let httpService: jest.Mocked<HttpService>;
  let mockFirstValueFrom: jest.MockedFunction<typeof firstValueFrom>;

  const originalEnv = process.env;

  beforeEach(async () => {
    process.env.ANTHROPIC_API_KEY = 'test-api-key';
    process.env.ANTROPIC_URL = 'https://api.anthropic.com/v1';

    mockFirstValueFrom = firstValueFrom as jest.MockedFunction<
      typeof firstValueFrom
    >;
    mockFirstValueFrom.mockResolvedValue({ data: {} });

    const mockHttpService = {
      post: jest.fn(),
    };

    const module: TestingModule = await Test.createTestingModule({
      providers: [
        AiService,
        {
          provide: HttpService,
          useValue: mockHttpService,
        },
      ],
    }).compile();

    service = module.get<AiService>(AiService);
    httpService = module.get(HttpService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });

  describe('breakdownTask', () => {
    const mockTask = 'Building an app';

    it('should return parsed tasks from Anthropic response', async () => {
      const mockResponse = {
        content: [
          {
            type: 'text',
            text: '## 1. Planning\nDefine requirements and create project plan.\n\n## 2. Design\nCreate wireframes and UI mockups.\n\n## 3. Development\nBuild the application features.',
          },
        ],
      };

      mockFirstValueFrom.mockResolvedValue({ data: mockResponse });

      const result = await service.breakdownTask(mockTask);

      expect(result).toHaveLength(3);
      expect(result[0]).toEqual({
        title: 'Planning',
        description: 'Define requirements and create project plan.',
        order: 1,
      });
      expect(result[1]).toEqual({
        title: 'Design',
        description: 'Create wireframes and UI mockups.',
        order: 2,
      });
      expect(result[2]).toEqual({
        title: 'Development',
        description: 'Build the application features.',
        order: 3,
      });
    });

    it('should return empty array when API key is missing', async () => {
      const originalApiKey = process.env.ANTHROPIC_API_KEY;
      delete process.env.ANTHROPIC_API_KEY;

      const result = await service.breakdownTask(mockTask);

      expect(result).toEqual([]);

      process.env.ANTHROPIC_API_KEY = originalApiKey;
    });

    it('should return empty array on API error', async () => {
      httpService.post.mockReturnValue(of({ data: { content: [] } }));
      mockFirstValueFrom.mockResolvedValue({ data: { content: [] } });

      const result = await service.breakdownTask(mockTask);

      expect(result).toEqual([]);
    });

    it('should handle malformed response gracefully', async () => {
      const mockResponse = {
        content: [
          {
            type: 'text',
            text: 'No numbered sections here',
          },
        ],
      };

      httpService.post.mockReturnValue(of({ data: mockResponse }));
      mockFirstValueFrom.mockResolvedValue({ data: mockResponse });

      const result = await service.breakdownTask(mockTask);

      expect(result).toEqual([]);
    });
  });

  afterEach(() => {
    process.env.ANTHROPIC_API_KEY = originalEnv.ANTHROPIC_API_KEY;
    process.env.ANTROPIC_URL = originalEnv.ANTROPIC_URL;
  });
});
