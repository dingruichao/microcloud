package com.ding.springcloud.service.io;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
 
/**
 * MappedByteBufferReadLineUtil
 * @author fish
 *
 */
public class MappedByteBufferReadLineUtil {
	
	private FileInputStream fis;
	private FileChannel fc;

	public MappedByteBuffer[] getMbbArr() {
		return mbbArr;
	}

	public void setMbbArr(MappedByteBuffer[] mbbArr) {
		this.mbbArr = mbbArr;
	}

	private MappedByteBuffer[] mbbArr;
	
	private int currentReadPos;
//	private int limit ;
	private long fileLength;
	private int number;
	
	public MappedByteBufferReadLineUtil(File file) throws Exception{
		if(!file.exists() || !file.isFile()){
			throw new Exception("指定文件不存在或者不是一个文件");
		}
		fis = new FileInputStream(file);
		fc = fis.getChannel();
		this.fileLength = fc.size();
		this.number = (int) Math.ceil((double) fileLength / (double) Integer.MAX_VALUE);
		this.mbbArr = new MappedByteBuffer[number];// 内存文件映射数组
		long preLength = 0;
		long regionSize = (long) Integer.MAX_VALUE;// 映射区域的大小
		for (int i = 0; i < number; i++) {// 将文件的连续区域映射到内存文件映射数组中
			if (fileLength - preLength < (long) Integer.MAX_VALUE) {
				regionSize = fileLength - preLength;// 最后一片区域的大小
			}
			mbbArr[i] = fc.map(FileChannel.MapMode.READ_ONLY, preLength, regionSize);

			preLength += regionSize;// 下一片区域的开始
		}

	}
	
	public MappedByteBufferReadLineUtil(String filePath) throws Exception{
		this(new File(filePath));
	}
	/**
	 * 指定每行的容量，最大字节数
	 * 如果存在行超过指定最大字节，则会
	 * @param capacity
	 * @return
	 * @throws Exception 
	 */
	public String readLine(MappedByteBuffer mbb,int capacity) throws Exception{
		try{
			if(currentReadPos >= mbb.limit()){
				return null;
			}
			ByteBuffer bb = ByteBuffer.allocate(capacity==0?1024:capacity);
			while(currentReadPos < mbb.limit()){
				byte b = mbb.get();
				currentReadPos++;
				if(System.getProperty("line.separator").equals("\n") && (b==10 || b==13)){
					 //mbb.get();
					 currentReadPos++;
					 break;
				}else if(b==10 || b==13){
					break;
				}else{
					bb.put(b);
				} 
			}
 			return rightTrim(new String(bb.array(),"GBK"));
		}catch(Exception e){
//			clean();
			throw e;
		}finally{ 
			fc.close();
			fis.close(); 
		}
	}

	
	/**
	 * 清理ByteBuffer
	 * @param
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void clean(MappedByteBuffer mbb) throws Exception {
        AccessController.doPrivileged(new PrivilegedAction() {
        	public Object run() {
	            try {
	               Method getCleanerMethod = mbb.getClass().getMethod("cleaner",new Class[0]);
	               getCleanerMethod.setAccessible(true);
	               sun.misc.Cleaner cleaner =(sun.misc.Cleaner)getCleanerMethod.invoke(mbb,new Object[0]);
	               cleaner.clean();
	            } catch(Exception e) {
	               e.printStackTrace();
	            }
	            return null;
        }
        }); 
	}
	private String rightTrim(String s){
		char[] cs = s.toCharArray();
		int pos= 0;
		for(int i=cs.length-1;i>=0;i--){
			String tostr = String.valueOf(cs[i]);
			if(tostr.trim().length()!=0){
				pos = i;
				break ;
			}
		}
		return s.substring(0,pos+1);
	}
	public static void main(String[] args){
		long begin=System.currentTimeMillis();
		MappedByteBufferReadLineUtil mbbrlutil = null;
		try{
			mbbrlutil = new MappedByteBufferReadLineUtil("/Users/dingruichao/a2.log");
			String line = null;
			for(int i=0;i<mbbrlutil.getMbbArr().length;i++) {
				while ((line = mbbrlutil.readLine(mbbrlutil.getMbbArr()[i],0) ) != null) {
//					System.out.println(line);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("usetime="+(System.currentTimeMillis()-begin));
			try {
//				mbbrlutil.clean();
			} catch (Exception e) {
 				e.printStackTrace();
			}
		}
	} 
}