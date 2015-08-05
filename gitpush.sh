#!/bin/bash

dt=$(date)
echo $dt

git add .
git commit -m `$dt`
git push
