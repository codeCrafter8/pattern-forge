export interface PatternCategory {
  name: string;
  patternNames: string[];
}

export const AVAILABLE_PATTERNS: PatternCategory[] = [
  {
    name: 'Structural Patterns',
    patternNames: ['Adapter', 'Composite']
  },
  {
    name: 'Behavioral Patterns',
    patternNames: ['Observer', 'Memento']
  },
  {
    name: 'Creational Patterns',
    patternNames: ['Factory Method', 'Singleton']
  }
];

export const SUPPORTED_PATTERN_NAMES: string[] = AVAILABLE_PATTERNS
  .flatMap(category => category.patternNames)
  .map(name => name.toLowerCase());
