#!/bin/bash
# Login and get token
TOKEN_RESP=$(curl -s -X POST http://localhost:3000/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"dev@devtask.com","password":"password123"}')

echo "Login Response: $TOKEN_RESP"

TOKEN=$(echo $TOKEN_RESP | grep -o '"access_token":"[^"]*' | cut -d'"' -f4)

if [ -z "$TOKEN" ]; then
  echo "Failed to get token"
  exit 1
fi

echo "Token: $TOKEN"

# Create Task
curl -X POST http://localhost:3000/tasks \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{"title":"Test AI Breakdown","description":"SEED TASK FOR AI TESTING","status":"TODO","tags":["frontend","seed"]}'
