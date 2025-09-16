import React, { useEffect, useState } from 'react';
import { Box, Button, TextField, Typography, List, ListItem, ListItemText, IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import SaveIcon from '@mui/icons-material/Save';
import CancelIcon from '@mui/icons-material/Cancel';
import { PerifericoRequestDTO, PerifericoResponseDTO } from '../services/types';
import { getPerifericos, createPeriferico, deletePeriferico, updatePeriferico } from '../services/api';

const Perifericos: React.FC = () => {
  const [perifericos, setPerifericos] = useState<PerifericoResponseDTO[]>([]);
  const [form, setForm] = useState<PerifericoRequestDTO>({ nome: '' });
  const [editId, setEditId] = useState<number | null>(null);
  const [editForm, setEditForm] = useState<PerifericoRequestDTO>({ nome: '' });

  const fetchPerifericos = async () => {
    setPerifericos(await getPerifericos());
  };

  useEffect(() => {
    fetchPerifericos();
  }, []);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await createPeriferico(form);
    setForm({ nome: '' });
    fetchPerifericos();
  };

  const handleDelete = async (id: number) => {
    await deletePeriferico(id);
    fetchPerifericos();
  };

  const handleEdit = (p: PerifericoResponseDTO) => {
    setEditId(p.id);
    setEditForm({ nome: p.nome, computadorId: p.computadorId ?? undefined });
  };

  const handleEditChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEditForm({ ...editForm, [e.target.name]: e.target.value });
  };

  const handleEditSave = async (id: number) => {
    await updatePeriferico(id, editForm);
    setEditId(null);
    fetchPerifericos();
  };

  const handleEditCancel = () => {
    setEditId(null);
  };

  return (
    <Box sx={{ maxWidth: "40%", mx: 'auto' }}>
      <Typography variant="h4" align="center">Perifericos</Typography>
      <form onSubmit={handleSubmit} style={{ display: 'flex', gap: 5, flexDirection: 'column', marginBottom: 16 }}>
        <TextField label="Nome" name="nome" value={form.nome} onChange={handleChange} required />
        <Button type="submit" variant="contained">Adicionar</Button>
      </form>
      <List>
        {perifericos.map((p) => (
          <ListItem key={p.id} secondaryAction={
            editId === p.id ? (
              <>
                <IconButton edge="end" aria-label="save" onClick={() => handleEditSave(p.id)}><SaveIcon /></IconButton>
                <IconButton edge="end" aria-label="cancel" onClick={handleEditCancel}><CancelIcon /></IconButton>
              </>
            ) : (
              <>
                <IconButton edge="end" aria-label="edit" onClick={() => handleEdit(p)}><EditIcon /></IconButton>
                <IconButton edge="end" aria-label="delete" onClick={() => handleDelete(p.id)}><DeleteIcon /></IconButton>
              </>
            )
          }>
            {editId === p.id ? (
              <Box sx={{ display: 'flex', gap: 1, flexDirection: 'column', width: '100%' }}>
                <TextField size="small" name="nome" value={editForm.nome} onChange={handleEditChange} />
              </Box>
            ) : (
              <ListItemText primary={`ID: ${p.id}  N: ${p.nome}`} />
            )}
          </ListItem>
        ))}
      </List>
    </Box>
  );
};

export default Perifericos;
