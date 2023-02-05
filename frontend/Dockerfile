# pull official base image
FROM node:13.12.0-alpine

# set working directory
WORKDIR /frontend

# add `/app/node_modules/.bin` to $PATH
ENV PATH /frontend/node_modules/.bin:$PATH

# install app dependencies
COPY package.json /frontend
COPY package-lock.json /frontend
RUN npm install --silent
RUN npm install react-scripts@3.4.1 -g --silent

# add app
COPY . /frontend

# start app
CMD ["npm", "start"]
