import { VariableGroup } from "./variable-group";

export interface VariableExtractionResult {
  singleVariables: string[];
  repeatableVariables: string[]; 
  groupedVariables: VariableGroup[];
  booleanVariables: string[];
}
