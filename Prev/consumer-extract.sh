#!/bin/bash
output=consumer.csv
echo "size, " `head -n 1 ./result/test-consumer-100.txt` >> $output
for file in $(ls ./result/test-consumer-*.txt)
do
    size=`echo $(basename $file .txt) | awk -F '-' '{print $3}'`
    echo $size ", " `tail -n 1 $file` >> $output
done