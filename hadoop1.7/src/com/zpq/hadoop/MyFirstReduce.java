package com.zpq.hadoop;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MyFirstReduce extends Reducer<Text,IntWritable,Text,IntWritable> {
    IntWritable mvalue = new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //cat-hello 1
        //cat-hello 0
        //cat-hello 0
        //有0直接输出0，没0加起来
        int sum=0;
        int flag = 1;
        for(IntWritable val : values){
            sum+=val.get();
            if(val.get()==0){
                flag=0;
                break;
            }
        }
        if(flag==0){
            mvalue.set(0);
            context.write(key,mvalue);
        }else{
            mvalue.set(sum);
            context.write(key,mvalue);
        }
    }
}
