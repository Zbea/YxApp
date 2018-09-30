package com.yx.Pharmacy.util;


import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

/**
 * SD卡相关的辅助类
 * 
 * @author jumps
 * 
 */
public class SDCardUtils
{
	public static final String ZGBJ_ROOT     = "data/yuanxin";
	public static final String PICTURE       = "picture";
	public static final String FILE          = "file";
	public static final String RECORD        = "record";
	public static final String SAVE          = "save";
	public static final String APK           = "apk";
	public static final String MALL_APK      = "mall";
	private SDCardUtils()
	{
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 判断SDCard是否可用
	 * 
	 * @return
	 */
	public static boolean isSDCardEnable(){
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	/**
	 * 获取SD卡里的zbgj路径
	 * @param FILENAME
	 * @return
	 */
	public static String getCachePath(Context context, String FILENAME){
		String path  = null;
		if(isSDCardEnable()){
			if(FILENAME == null){
				return Environment.getExternalStorageDirectory().getAbsolutePath()
						+ File.separator+ ZGBJ_ROOT
//						+File.separator+SPUtils.get(context, "uid", "0")
						+ File.separator;
			}
			return 	Environment.getExternalStorageDirectory().getAbsolutePath()
					+ File.separator+ ZGBJ_ROOT
//					+File.separator+SPUtils.get(context, "uid", "0")
					+ File.separator+FILENAME
					+ File.separator;
		}else{
			if(FILENAME == null){
				return  context.getCacheDir().getAbsolutePath()
						+ File.separator+ ZGBJ_ROOT
//						+File.separator+SPUtils.get(context, "uid", "0")
						+ File.separator;
			}
			return 	context.getCacheDir().getAbsolutePath()
					+ File.separator+ ZGBJ_ROOT
//					+File.separator+SPUtils.get(context, "uid", "0")
					+ File.separator+FILENAME
					+ File.separator;
		}
	}

	public static long getCacheAllSize(Context context)
	{
		if(context == null) 
			return 0;
		StatFs stat = new StatFs(getCachePath(context,null));
		long availableBlocks = (long) stat.getAvailableBlocks() - 4;
		long freeBlocks = stat.getAvailableBlocks();
		return freeBlocks * availableBlocks;
	}

	/**
	 * 获取指定路径所在空间的剩余可用容量字节数，单位byte
	 * 
	 * @param filePath
	 * @return 容量字节 SDCard可用空间，内部存储可用空间
	 */
	public static long getFreeBytes(Context context, String filePath)
	{
		if(isSDCardEnable()){
			// 如果是sd卡的下的路径，则获取sd卡可用容量
			if (filePath.startsWith(getCachePath(context,null)))
			{
				filePath = getCachePath(context,null);
			} else
			{// 如果是内部存储的路径，则获取内存存储的可用容量
				filePath = Environment.getDataDirectory().getAbsolutePath();
			}
			StatFs stat = new StatFs(filePath);
			long availableBlocks = (long) stat.getAvailableBlocks() - 4;
			return stat.getBlockSize() * availableBlocks;
		}
		return 0;
	}


	public static long getFileSizes(File f) throws Exception {//取得文件大小
		long s=0;
		if (f.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			s= fis.available();
		} else {
			f.createNewFile();
		}
		return s;
	}
	// 递归
	public static long getFileSize(File f)throws Exception//取得文件夹大小
	{
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++)
		{
			if (flist[i].isDirectory())
				size = size + getFileSize(flist[i]);
			else
				size = size + flist[i].length();

		}
		return size;
	}
	public static String FormetFileSize(long fileS) {//转换文件大小
		DecimalFormat df = new DecimalFormat("#0.00");
		String fileSizeString = "";
		if (fileS < 1024) 
			fileSizeString = df.format((double) fileS) + "B";
		else if (fileS < 1048576) 
			fileSizeString = df.format((double) fileS / 1024D) + "KB";
		else if (fileS < 1073741824) 
			fileSizeString = df.format((double) fileS / 1048576D) + "MB";
		else 
			fileSizeString = df.format((double) fileS / 1073741824D) + "GB";
		return fileSizeString;
	}
	public static long getlist(File f){//递归求取目录文件个数
		long size = 0;
		File flist[] = f.listFiles();
		size=flist.length;
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getlist(flist[i]);
				size--;
			}
		}
		return size;
	}

	public static void deleteFile(String path) {
		File file = new File(path);
		File to;
		try {
//			to = File.createTempFile("tempjs", ".js");
//			file.renameTo(to);
			if (file.isDirectory()) {
				File[] ff = file.listFiles();
				for (int i = 0; i < ff.length; i++) {
					deleteFile(ff[i].getPath());
				}
			}else if(file.isFile()){
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.d("tAg", "delete error");
		}
	}
}
