import { GeneratedFile } from './generated-file';

export interface AiPatternResponse {
  generatedFiles: GeneratedFile[];
  patternName: string | null;
}
