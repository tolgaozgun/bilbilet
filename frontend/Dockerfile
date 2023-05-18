# Use an official Node.js runtime as the base image
FROM node:16-alpine

# Set the working directory in the container
WORKDIR /frontend

# add `/app/node_modules/.bin` to $PATH
ENV PATH /frontend/node_modules/.bin:$PATH

# Copy the package.json and yarn.lock files to the container
COPY package.json /frontend
COPY yarn.lock /frontend

# Install dependencies
RUN yarn install 
#--frozen-lockfile

# Copy the remaining application files to the container
COPY . /frontend

# Build the React application
# RUN yarn build

# Set the command to run the application
# CMD ["yarn", "start"]
CMD ["yarn", "run", "dev"]