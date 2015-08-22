#!/bin/bash

i=0
for f in * ; do
	mv $f $i
  let "i += 1"
done
