import React, { useEffect, useState } from 'react';
import { Box, Button, TextField, Typography, List, ListItem, ListItemText, IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import SaveIcon from '@mui/icons-material/Save';
import CancelIcon from '@mui/icons-material/Cancel';
import { ComputadorRequestDTO, ComputadorResponseDTO } from '../services/types';
import { getComputadores, createComputador, deleteComputador, updateComputador } from '../services/api';

const Computadores: React.FC = () => {
  const [computadores, setComputadores] = useState<ComputadorResponseDTO[]>([]);
  const [form, setForm] = useState<ComputadorRequestDTO>({ nome: '', cor: '', dataFabricacao: new Date().getFullYear() });
  const [editId, setEditId] = useState<number | null>(null);
  const [editForm, setEditForm] = useState<ComputadorRequestDTO>({ nome: '', cor: '', dataFabricacao: new Date().getFullYear() });

  const fetchComputadores = async () => {
    setComputadores(await getComputadores());
  };

  useEffect(() => {
    fetchComputadores();
  }, []);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await createComputador({ ...form, dataFabricacao: Number(form.dataFabricacao) });
    setForm({ nome: '', cor: '', dataFabricacao: new Date().getFullYear() });
    fetchComputadores();
  };

  const handleDelete = async (id: number) => {
    await deleteComputador(id);
    fetchComputadores();
  };

  const handleEdit = (c: ComputadorResponseDTO) => {
    setEditId(c.id);
    setEditForm({ nome: c.nome, cor: c.cor, dataFabricacao: c.dataFabricacao });
  };

  const handleEditChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEditForm({ ...editForm, [e.target.name]: e.target.value });
  };

  const handleEditSave = async (id: number) => {
    await updateComputador(id, { ...editForm, dataFabricacao: Number(editForm.dataFabricacao) });
    setEditId(null);
    fetchComputadores();
  };

  const handleEditCancel = () => {
    setEditId(null);
  };

  return (
    <Box sx={{ maxWidth: "40%", mx: 'auto' }}>
      <Typography variant="h4" align="center">Computadores</Typography>
      <form onSubmit={handleSubmit} style={{ display: 'flex', gap: 5, flexDirection: 'column', marginBottom: 16 }}>
        <TextField label="Nome" name="nome" value={form.nome} onChange={handleChange} required />
        <TextField label="Cor" name="cor" value={form.cor} onChange={handleChange} required />
        <TextField label="Data Fabricação" name="dataFabricacao" type="number" value={form.dataFabricacao} onChange={handleChange} required />
        <Button type="submit" variant="contained">Adicionar</Button>
      </form>
      <List>
        {computadores.map((c) => (
          <ListItem key={c.id} secondaryAction={
            editId === c.id ? (
              <>
                <IconButton edge="end" aria-label="save" onClick={() => handleEditSave(c.id)}><SaveIcon /></IconButton>
                <IconButton edge="end" aria-label="cancel" onClick={handleEditCancel}><CancelIcon /></IconButton>
              </>
            ) : (
              <>
                <IconButton edge="end" aria-label="edit" onClick={() => handleEdit(c)}><EditIcon /></IconButton>
                <IconButton edge="end" aria-label="delete" onClick={() => handleDelete(c.id)}><DeleteIcon /></IconButton>
              </>
            )
          }>
            {editId === c.id ? (
              <Box sx={{ display: 'flex', gap: 1, flexDirection: 'column', width: '100%' }}>
                <TextField size="small" name="nome" value={editForm.nome} onChange={handleEditChange} />
                <TextField size="small" name="cor" value={editForm.cor} onChange={handleEditChange} />
                <TextField size="small" name="dataFabricacao" type="number" value={editForm.dataFabricacao} onChange={handleEditChange} />
              </Box>
            ) : (
              <ListItemText primary={`ID: ${c.id} N: ${c.nome} C: ${c.cor} A: ${c.dataFabricacao}`} />
            )}
          </ListItem>
        ))}
      </List>
    </Box>
  );
};

export default Computadores;
