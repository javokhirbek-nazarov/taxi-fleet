FROM node:20-alpine

WORKDIR /taxi-fleet-ui/

COPY public/ /taxi-fleet-ui/public
COPY src/ /taxi-fleet-ui/src
COPY package.json /taxi-fleet-ui/

RUN npm install

EXPOSE 3000

CMD ["npm", "start"]
