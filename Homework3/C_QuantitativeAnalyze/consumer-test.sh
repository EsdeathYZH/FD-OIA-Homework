#!/bin/bash
shdir=~/Downloads/kafka_2.11-2.0.1/bin
resdir=~/Scripts/kafka/result
MSGS=100000
sizes=(100 1000 5000 10000 30000)
brokers="47.101.54.182:9092"
cd $shdir
for size in ${sizes[@]}
do
    echo "size: $size"
    ./kafka-consumer-perf-test.sh --topic test --messages $MSGS --fetch-size $size --broker-list $brokers --threads 4 >> $resdir/test-consumer-$size.txt
done