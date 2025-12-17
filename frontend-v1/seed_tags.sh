#!/bin/bash
# Login and get token
TOKEN_RESP=$(curl -s -X POST http://localhost:3000/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"dev@devtask.com","password":"password123"}')

TOKEN=$(echo $TOKEN_RESP | grep -o '"access_token":"[^"]*' | cut -d'"' -f4)

if [ -z "$TOKEN" ]; then
  echo "Failed to get token"
  exit 1
fi

echo "Created Token for seeding..."

# Create Tags
echo "Creating Tags..."
curl -X POST http://localhost:3000/tags \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{"name":"Frontend","color":"#0ea5e9"}'

curl -X POST http://localhost:3000/tags \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{"name":"Backend","color":"#8b5cf6"}'

curl -X POST http://localhost:3000/tags \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{"name":"Bug","color":"#ef4444"}'
