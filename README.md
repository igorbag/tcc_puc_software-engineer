API REST Gerenciamento de Alunos TCC PUC - Engenharia de Software

Execução do projeto local
- git clone do projeto
- npm install
- npm start


Execução do projeto em produção:

Criar a imagem no docker:
docker build -t <your username>/node-web-app .

docker run -p [PORT EXTERNA DO SERVIDOR WEB]:8080 -d <your username>/node-web-app

docker exec -it <container id> /bin/bash

docker ps
