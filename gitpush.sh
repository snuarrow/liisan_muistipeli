#!/bin/bash

dt=$(date)
echo $dt

git add .
git commit -m "$(date)"
git push
