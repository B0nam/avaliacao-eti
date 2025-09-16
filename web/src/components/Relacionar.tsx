import React, { useEffect, useState } from 'react';
import { Box, Typography, FormControl, TextField, Button, List, ListItem, ListItemText } from '@mui/material';
import { getComputadores, getPerifericos, relacionar, desrelacionar } from '../services/api';
import { ComputadorResponseDTO, PerifericoResponseDTO } from '../services/types';

const Relacionar: React.FC = () => {
  const [computadores, setComputadores] = useState<ComputadorResponseDTO[]>([]);
  const [perifericos, setPerifericos] = useState<PerifericoResponseDTO[]>([]);
  const [computadorId, setComputadorId] = useState<number | ''>('');
  const [perifericoId, setPerifericoId] = useState<number | ''>('');
  const [refresh, setRefresh] = useState(false);

  const fetchData = async () => {
    setComputadores(await getComputadores());
    setPerifericos(await getPerifericos());2025
  };

  useEffect(() => {
    fetchData();
  }, [refresh]);

  const handleRelacionar = async () => {
    if (computadorId && perifericoId) {
      await relacionar(Number(computadorId), Number(perifericoId));
      setRefresh((r) => !r);
    }
  };

  const handleDesrelacionar = async (compId: number, periId: number) => {
    await desrelacionar(compId, periId);
    setRefresh((r) => !r);
    console.log(compId, periId);
  };

  return (
    <Box sx={{ maxWidth: "40%", mx: 'auto' }}>
      <Typography variant="h4" align="center">Relacionar Periferico / Computador</Typography>
      <Box sx={{ display: 'flex', gap: 2, mb: 2 }}>
        <FormControl fullWidth>
          <TextField
            label="ID do Computador"
            type="number"
            value={computadorId}
            onChange={e => setComputadorId(e.target.value === '' ? '' : Number(e.target.value))}
          />
        </FormControl>
        <FormControl fullWidth>
          <TextField
            label="ID do Periferico"
            type="number"
            value={perifericoId}
            onChange={e => setPerifericoId(e.target.value === '' ? '' : Number(e.target.value))}
          />
        </FormControl>
        <Button variant="contained" onClick={handleRelacionar} disabled={!computadorId || !perifericoId}>Relacionar</Button>
      </Box>
      <Typography variant="h6" sx={{ mt: 2 }}>Perifericos por Computador</Typography>
      <List>
        {computadores.map(c => (
          <Box key={c.id} sx={{ mb: 2 }}>
            <Typography variant="subtitle1">{`Computador: ID: ${c.id}  N: ${c.nome}`}</Typography>
            <List sx={{ pl: 2 }}>
              {c.perifericos.length === 0 && <ListItem><ListItemText primary="Nenhum periferico" /></ListItem>}
              {c.perifericos.map(p => (
                <ListItem key={p.id} secondaryAction={
                  <Button color="error" onClick={() => handleDesrelacionar(c.id, p.id)}>Desrelacionar</Button>
                }>
                  <ListItemText primary={`Periferico: ID: ${p.id}  N: ${p.nome}`} />
                </ListItem>
              ))}
            </List>
          </Box>
        ))}
      </List>
    </Box>
  );
};

export default Relacionar;
