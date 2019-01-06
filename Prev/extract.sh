#!/bin/bash
echo "record size" ", " "ideal throughput(records/sec)" ", " "real throughput(records/sec)" ", " \
"speed(MB/sec)" ", " "average latency(ms)" ", " "max latency(ms)" \
>> producer.csv
for file in $(ls ./result/test-producer-*-*.txt)
do
    variables=`echo $(basename $file .txt) | awk -F '-' '{print $3, $4}' | sed 's/ /, /g'`
    metrics=`tail -n 1 $file | sed 's/(//' | awk '{print $4, $6, $8, $12}' | sed 's/ /, /g'` 
    echo $variables ", " $metrics >> producer.csv
done