import React, { useState } from 'react';
import { Box, Tabs, Tab } from '@mui/material';
import Computadores from './components/Computadores';
import Perifericos from './components/Perifericos';
import Relacionar from './components/Relacionar';

function App() {
  const [tab, setTab] = useState(0);

  return (
    <Box sx={{ width: '100%' }}>
      <Tabs value={tab} onChange={(_, v) => setTab(v)} centered>
        <Tab label="Computadores" />
        <Tab label="Periféricos" />
        <Tab label="Relacionar" />
      </Tabs>
      <Box>
        {tab === 0 && <Computadores />}
        {tab === 1 && <Perifericos />}
        {tab === 2 && <Relacionar />}
      </Box>
    </Box>
  );
}

export default App;
