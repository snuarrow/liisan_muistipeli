#!/bin/bash

for f in * ; do
	convert $f -resize 1024x768\> $f
	mogrify -quality 50% $f
done
