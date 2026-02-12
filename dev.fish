#!/usr/bin/env fish
echo "Starting dev servers..."
docker-compose -f compose.dev.yml --profile dev up -d

if test $status -ne 0
    echo "Starting dev servers... Failed"
end

for i in (seq 1 5)
    echo "Waiting for dev servers to be ready..."
    sleep 1.5
end

echo "Starting dev servers... Done"

echo "Launching frontend..."

open http://localhost:3000
echo "Launching frontend...Done"

echo "Launching Swagger UI..."
sleep 1

open http://localhost:4000/api
echo "Launching Swagger UI...Done"

echo "Dev environment is ready!"
