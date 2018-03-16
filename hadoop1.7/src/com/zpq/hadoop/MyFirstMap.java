package com.zpq.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;

public class MyFirstMap extends Mapper<LongWritable,Text,Text,IntWritable> {
    Text mkey = new Text();
    IntWritable mvalue = new IntWritable();
    @Override
    protected void map(LongWritable key, Text values, Context context) throws IOException, InterruptedException {

        //tom cat hello hadoop

        //数据按照空格切割
        String[] strs = StringUtils.split(values.toString(),' ');

        for(int i = 1;i<strs.length;i++){
            mkey.set(merge(strs[0],strs[i]));
            mvalue.set(0);
            context.write(mkey,mvalue);
            for(int j=i+1;j<strs.length;j++){
                mkey.set(merge(strs[i],strs[j]));
                mvalue.set(1);
                context.write(mkey,mvalue);
            }
        }

    }
    public static String merge(String s1,String s2){
        if(s1.compareTo(s2)>0){
            return s1+"-"+s2;
        }else{
            return s2+"-"+s1;
        }
    }
}
