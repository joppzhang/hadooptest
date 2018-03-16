package com.zpq.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


public class MyFriend {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(MyFriend.class);
        job.setJobName("find_friends");

        FileInputFormat.addInputPath(job,new Path("/root/friends.txt"));
        Path outpath = new Path("/root/midresult.txt");
        if(outpath.getFileSystem(conf).exists(outpath)){
            outpath.getFileSystem(conf).removeAcl(outpath);
        }
        FileOutputFormat.setOutputPath(job,outpath);

        job.setMapperClass(MyFirstMap.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.setReducerClass(MyFirstReduce.class);
        job.waitForCompletion(true);



    }
}
