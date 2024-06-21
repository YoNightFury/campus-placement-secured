#!/bin/bash
export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"  # This loads nvm
[ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"  # This loads nvm bash_completion
# Navigate to backend directory and build
mvn clean package  -DskipTests=true

# Navigate to frontend directory
cd placement-portal-front
nvm use 16
# Install frontend dependencies and build
npm install
npm run build

# Move build output to backend's static resource directory
mkdir -p ../src/main/resources/static/
rm -rf ../src/main/resources/static/*
mv build/* ../src/main/resources/static/
