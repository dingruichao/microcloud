package com.ding.springcloud.service.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
 
/**
 *
 */
public class MappedBiggerFileReader {
    private MappedByteBuffer[] mappedBufArray;
    FileChannel fc = new RandomAccessFile("/Users/dingruichao/a3.log", "rw").getChannel();
    private MappedByteBuffer mappOut;
    private int count = 0;
    private int number;
    private FileInputStream fileIn;
    private long fileLength;
    private int arraySize;
    private byte[] array;

    public MappedBiggerFileReader(String fileName, int arraySize) throws IOException {
        this.fileIn = new FileInputStream(fileName);
        FileChannel fileChannel = fileIn.getChannel();
        this.fileLength = fileChannel.size();
        this.number = (int) Math.ceil((double) fileLength / (double) Integer.MAX_VALUE);
        this.mappedBufArray = new MappedByteBuffer[number];// 内存文件映射数组
        long preLength = 0;
        long regionSize = (long) Integer.MAX_VALUE;// 映射区域的大小
        for (int i = 0; i < number; i++) {// 将文件的连续区域映射到内存文件映射数组中
            if (fileLength - preLength < (long) Integer.MAX_VALUE) {
                regionSize = fileLength - preLength;// 最后一片区域的大小
            }
            mappedBufArray[i] = fileChannel.map(FileChannel.MapMode.READ_ONLY, preLength, regionSize);

            preLength += regionSize;// 下一片区域的开始
        }
        mappOut  = fc.map(FileChannel.MapMode.READ_WRITE, 0,mappedBufArray[0].limit() );
        this.arraySize = arraySize;
    }

    public int read() throws IOException {
        if (count >= number) {
            return -1;
        }

        int limit = mappedBufArray[count].limit();
        int position = mappedBufArray[count].position();
        if (limit - position > arraySize) {
            array = new byte[arraySize];
            mappedBufArray[count].get(array);
//            if(count>0) {
                mappOut.put(array);
//            }
            return arraySize;
        } else {// 本内存文件映射最后一次读取数据
            array = new byte[limit - position];
            mappedBufArray[count].get(array);
            //            if(count>0) {
            mappOut.put(array);
//            }
            if (count < number) {
                count++;// 转换到下一个内存文件映射

                    if(count<number) {
                        mappOut = fc.map(FileChannel.MapMode.READ_WRITE, count*Integer.MAX_VALUE, mappedBufArray[count].limit());
                    }

            }

            return limit - position;
        }
    }

    public void close() throws IOException {
        fileIn.close();
        array = null;
    }

    public byte[] getArray() {
        return array;
    }

    public long getFileLength() {
        return fileLength;
    }

    public static void main(String[] args) throws IOException {
        FileChannel fc = new RandomAccessFile("/Users/dingruichao/a3.log", "rw").getChannel();

        MappedBiggerFileReader reader = new MappedBiggerFileReader("/Users/dingruichao/a2.log", 65536);
        long start = System.nanoTime();
        while (reader.read() != -1) ;
        long end = System.nanoTime();
        reader.close();
        System.out.println("MappedBiggerFileReader: " + (end - start));
    }
}