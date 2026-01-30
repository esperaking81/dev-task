export class ApiError extends Error {
  constructor(
    public status: number,
    public statusText: string,
    public data?: object,
  ) {
    super(`API error: ${status} ${statusText}`);
  }
}
