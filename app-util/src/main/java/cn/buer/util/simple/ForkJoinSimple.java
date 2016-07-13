package cn.buer.util.simple;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSimple {
	
	private static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();
	/**
	 * 任务
	 *@Title:  
	 *@Description:  
	 *@Author:BuEr  
	 *@Since:2016年4月12日  
	 *@Version:1.1.0
	 */
	class WriterFileTask extends RecursiveTask<List<File>>{
		/**
		* @Fields serialVersionUID : 
		*/
		
		private static final long serialVersionUID = 1L;
		private int THRESHOLD = 100000;
		private int start;
		private int end;
		public WriterFileTask(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		protected List<File> compute() {
			List<File> result = new ArrayList<>();
			int sum = end - start;
			if(sum <= THRESHOLD){
				File file = createFile(start, end);
				try {
					writerFile(start, end, file);
				} catch (Exception e) {
					e.printStackTrace();
				}
				result.add(file);
			}else{
				int middle = (start + end)/2;
				WriterFileTask rightTask = new WriterFileTask(start, middle);
				WriterFileTask leftTask = new WriterFileTask(middle + 1, end);
				rightTask.fork();
				leftTask.fork();
				result.addAll(rightTask.join());
				result.addAll(leftTask.join());
			}
			return result;
		}
		
	}
	/**
	 * 写文件
	 * @param start
	 * @param end
	 * @param file
	 * @throws Exception  
	 * @Description:
	 */
	private void writerFile(int start,int end,File file) throws Exception{
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		FileChannel channel = raf.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		StringBuilder content = new StringBuilder();
		for(;start <= end; start++){
			content.append(start);
			if(start != end){
				content.append("\n");
			}
			try {
				buffer.put(content.toString().getBytes());
				content.delete(0, content.length());
			} catch (BufferOverflowException e) {
				writerChannel(channel, buffer);
				
			}
		}
		if(buffer.position() > 0){
			writerChannel(channel, buffer);
		}
		channel.close();
		raf.close();
	}
	
	private void writerChannel(FileChannel channel,ByteBuffer bf) throws IOException{
		bf.flip();
		channel.write(bf);
		bf.clear();
	}
	/**
	 * 创建文件
	 * @param start
	 * @param end
	 * @return  
	 * @Description:
	 */
	private File createFile(int start,int end){
		StringBuilder filePath = new StringBuilder();
		filePath.append(this.getClass().getClassLoader().getResource("").getPath());
		filePath.append("temp/");
		filePath.append("/forkJoinWork/");
		
		File directory = new File(filePath.toString());
		if(!directory .exists()){
			directory .mkdirs();
		}else{
			filePath.append(String.valueOf(start));
			filePath.append(String.valueOf("-"));
			filePath.append(String.valueOf(end));
			filePath.append(String.valueOf("-"));
			filePath.append(String.valueOf(System.currentTimeMillis()));
			filePath.append(".txt");
			File file = new File(filePath.toString());
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return file;
		}
		return null;
	}
	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
		ForkJoinSimple simple = new ForkJoinSimple();
		WriterFileTask task =  simple.new WriterFileTask(1, 10000 * 1000);
		Future<List<File>> result =  FORK_JOIN_POOL.submit(task);
		try {
			List<File> fileList = result.get();
			for(File file : fileList){
				file.delete();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("forkJoin耗时:" + (System.currentTimeMillis() - begin));
		
		long aBegin = System.currentTimeMillis();
		File file = simple.createFile(0, 10000*1000);
		try {
			simple.writerFile(0, 10000*1000, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("no forkJoin耗时：" + (System.currentTimeMillis() - aBegin));
	}
}
