import { HttpService } from '@nestjs/axios';
import { Injectable, Logger } from '@nestjs/common';
import { TaskPriority } from '../tasks/dto/task.dto';
import { firstValueFrom } from 'rxjs';

export interface BreakdownSuggestion {
  title: string;
  description?: string;
  priority: string;
  order: number;
}

const getBaseUrl = () =>
  process.env.ANTROPIC_URL || 'https://api.anthropic.com/v1';
const getApiKey = () => process.env.ANTHROPIC_API_KEY;

interface AnthropicRequest {
  model: string;
  max_tokens: number;
  messages: Array<{
    role: string;
    content: string;
  }>;
}

interface AnthropicResponse {
  content: Array<{
    type: string;
    text: string;
  }>;
}

@Injectable()
export class AiService {
  private readonly logger = new Logger(AiService.name);

  constructor(private readonly httpService: HttpService) {}

  async breakdownTask(task: string): Promise<BreakdownSuggestion[]> {
    const baseUrl = getBaseUrl();
    const apiKey = getApiKey();

    if (!apiKey) {
      this.logger.error('ANTHROPIC_API_KEY environment variable is not set');
      return [];
    }

    this.logger.log(`Using Anthropic API at: ${baseUrl}`);

    try {
      const prompt = this.buildPrompt(task);
      const response = await this.callAnthropicAPI(prompt, apiKey);
      return this.parseResponse(response);
    } catch (error) {
      this.logger.error('Failed to breakdown task', error);
      return [];
    }
  }

  private buildPrompt(task: string): string {
    return `Break down this task into numbered subtasks: "${task}"

Format your response exactly as:
## 1. Subtask Title
Brief description (1-2 sentences)

## 2. Subtask Title
Brief description (1-2 sentences)

Keep descriptions concise. Do not ask follow-up questions or offer to expand on topics.`;
  }

  private async callAnthropicAPI(
    prompt: string,
    apiKey: string,
  ): Promise<string> {
    const request: AnthropicRequest = {
      model: 'claude-haiku-4-5-20251001',
      max_tokens: 1000,
      messages: [
        {
          role: 'user',
          content: prompt,
        },
      ],
    };

    const headers = {
      'x-api-key': apiKey,
      'content-type': 'application/json',
      'anthropic-version': '2023-06-01',
    };

    const { data } = await firstValueFrom(
      this.httpService.post<AnthropicResponse>(
        `${getBaseUrl()}/messages`,
        request,
        { headers },
      ),
    );

    if (!data.content || data.content.length === 0) {
      throw new Error('No content in Anthropic response');
    }

    return data.content[0].text;
  }

  private parseResponse(response: string): BreakdownSuggestion[] {
    const tasks: BreakdownSuggestion[] = [];
    const regex = /## (\d+)\.\s*(.+?)\n([^#]+)/g;

    let match: RegExpExecArray | null;
    let order = 1;
    while ((match = regex.exec(response)) !== null) {
      const [, , title, description] = match;

      const task: BreakdownSuggestion = {
        title: title.trim(),
        description: description.trim(),
        order: order++,
        priority: TaskPriority.MEDIUM,
      };

      tasks.push(task);
    }

    return tasks;
  }
}
