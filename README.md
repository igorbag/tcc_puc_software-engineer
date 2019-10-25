API REST Gerenciamento de Alunos TCC PUC - Engenharia de Software

Técnologias:
- Nodejs
- MongoDB
- Express
- ES6

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


English : 
REST API Student Management Paper PUC - Software Engineering

Technology:
- Nodejs
- MongoDB
- Express
- ES6

Local project execution
- git clone of the project
- npm installation
- start npm


Project execution in production:

Create an image on the docker:
docker build -t <your username> / node-web-app.

docker run -p [WEB SERVER EXTERNAL PORT]: 8080 -d <your username> / node-web-app

docker exec -it <container ID> / bin / bash

docker ps
