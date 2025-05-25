import { VariableGroup } from "./variable-group";

export interface VariableExtractionResult {
  singleVariables: string[];
  groupedVariables: VariableGroup[];
}
