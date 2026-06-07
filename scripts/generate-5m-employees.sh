#!/usr/bin/env bash
set -euo pipefail

curl -X POST http://localhost:8080/api/v1/admin/data-loader/employees \
  -H 'Content-Type: application/json' \
  -d '{"targetCount":5000000,"batchSize":10000}'
