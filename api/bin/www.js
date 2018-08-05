'use strict';

const http = require('http');
const app = require('../app');
const server = http.createServer(app);

server.listen(8080, function() {
  console.log('Servidor rodando na porta 8080');
});
