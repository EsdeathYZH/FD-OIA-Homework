#!/bin/bash
shdir=~/Downloads/kafka_2.11-2.0.1/bin
resdir=~/Scripts/kafka/data
MSGS=100000
sizes=(100 1000 5000 10000 30000)
throughputs=(100 250 500 1000 2000 4000 8000)
broker="47.101.54.182:9092"
cd $shdir
for size in ${sizes[@]}
do
    for th in ${throughputs[@]}
    do
        echo "size: $size, throughput: $th"
        ./kafka-producer-perf-test.sh --topic test --num-records $MSGS --record-size $size --throughput $th --producer-props bootstrap.servers=$broker >> $resdir/test-producer-$size-$th.txt
    done
done
