#!/bin/bash

# Find all .txt files and count lines in each
for file in $(find . -type f -name "*.txt"); do
    wc -l "$file"
done
