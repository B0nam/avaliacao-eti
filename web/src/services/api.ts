import axios from 'axios';
import {
  ComputadorRequestDTO,
  ComputadorResponseDTO,
  PerifericoRequestDTO,
  PerifericoResponseDTO,
} from './types';

const api = axios.create({
  baseURL: 'http://localhost:8080/api/v1',
});

export const getComputadores = async (): Promise<ComputadorResponseDTO[]> => {
  const { data } = await api.get('/computadores/');
  return data;
};

export const createComputador = async (comp: ComputadorRequestDTO): Promise<ComputadorResponseDTO> => {
  const { data } = await api.post('/computadores/', comp);
  return data;
};

export const deleteComputador = async (id: number) => {
  await api.delete(`/computadores/${id}`);
};

export const updateComputador = async (id: number, comp: ComputadorRequestDTO): Promise<ComputadorResponseDTO> => {
  const { data } = await api.put(`/computadores/${id}`, comp);
  return data;
};

export const getPerifericos = async (): Promise<PerifericoResponseDTO[]> => {
  const { data } = await api.get('/perifericos/');
  return data;
};

export const createPeriferico = async (peri: PerifericoRequestDTO): Promise<PerifericoResponseDTO> => {
  const { data } = await api.post('/perifericos/', peri);
  return data;
};

export const deletePeriferico = async (id: number) => {
  await api.delete(`/perifericos/${id}`);
};

export const updatePeriferico = async (id: number, peri: PerifericoRequestDTO): Promise<PerifericoResponseDTO> => {
  const { data } = await api.put(`/perifericos/${id}`, peri);
  return data;
};

export const relacionar = async (computadorId: number, perifericoId: number) => {
  await api.post(`/relacionar/${computadorId}/${perifericoId}`);
};

export const desrelacionar = async (computadorId: number, perifericoId: number) => {
  await api.delete(`/relacionar/${computadorId}/${perifericoId}`);
};
