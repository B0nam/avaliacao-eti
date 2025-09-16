export interface ComputadorRequestDTO {
  nome: string;
  cor: string;
  dataFabricacao: number;
}

export interface PerifericoRequestDTO {
  nome: string;
  computadorId?: number;
}

export interface PerifericoResponseDTO {
  id: number;
  nome: string;
  computadorId?: number | null;
}

export interface ComputadorResponseDTO {
  id: number;
  nome: string;
  cor: string;
  dataFabricacao: number;
  perifericos: PerifericoResponseDTO[];
}
