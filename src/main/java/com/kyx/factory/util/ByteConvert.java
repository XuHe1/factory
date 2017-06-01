package com.kyx.factory.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;



/**
 * 整型数和网络字节序(即 byte[]数组)之间的相互转换
 * 
 * @author j.wang
 */
public class ByteConvert {
	/** 16进制数字字符集 */
	private static final String HexString = "0123456789ABCDEF";
	private static final char[] HexArray = HexString.toCharArray();
    
	/**
	 * 低字节在前，高字节在后
	 */
	public static byte[] long2Bytes(Long n) {
		if(n == null){
			return null;
		}
		byte[] b = new byte[8];
		b[0] = (byte) (n & 0xff);
		b[1] = (byte) (n >> 8 & 0xff);
		b[2] = (byte) (n >> 16 & 0xff);
		b[3] = (byte) (n >> 24 & 0xff);
		b[4] = (byte) (n >> 32 & 0xff);
		b[5] = (byte) (n >> 40 & 0xff);
		b[6] = (byte) (n >> 48 & 0xff);
		b[7] = (byte) (n >> 56 & 0xff);
		return b;
	}

	/**
	 * 低字节在前，高字节在后
	 */
	public static Long bytes2Long(byte[] b) {
		if(b == null || b.length < 8){
			return null;
		}
		return ((((long) b[7] & 0xff) << 56)
				| (((long) b[6] & 0xff) << 48)
				| (((long) b[5] & 0xff) << 40)
				| (((long) b[4] & 0xff) << 32)
				| (((long) b[3] & 0xff) << 24)
				| (((long) b[2] & 0xff) << 16)
				| (((long) b[1] & 0xff) << 8) 
				| (((long) b[0] & 0xff) << 0));
	}

	/** 低字节在前，高字节在后 */
	public static byte[] int2Bytes(Integer n) {
		if(n == null){
			return null;
		}
		
		 byte[] b = new byte[4];
		 b[0] = (byte) (n & 0xff);
		 b[1] = (byte)(n >> 8 & 0xff);
		 b[2] = (byte) (n >> 16 & 0xff);
		 b[3] = (byte) (n >> 24 & 0xff);
		 
		 return b;
	}

	/** 低字节在前，高字节在后 */
	public static Integer bytes2Int(byte[] b) {
		if(b == null || b.length < 4){
			return null;
		}
		return b[0] & 0xff 
		| (b[1] & 0xff) << 8 
		| (b[2] & 0xff) << 16
		| (b[3] & 0xff) << 24;
	}

	/** 低字节在前，高字节在后 */
	public static Long bytes2UInt(byte[] b) {
		if(b == null || b.length < 4){
			return null;
		}
		return ((long)b[0] & 0xff 
				| (long)(b[1] & 0xff) << 8 
				| (long)(b[2] & 0xff) << 16
				| (long)(b[3] & 0xff) << 24);
	}

	/** 1、2、4字节的可变长度字节数组转有符号Int，低字节在前，高字节在后 */
	public static Long variableBytes2Int(byte[] b) {
		if(b == null){
			return null;
		}
		if (b.length == 1) {
			return (long) ByteConvert.byte2Ubyte(b[0]);
		} else if (b.length == 2) {
			return (long) ByteConvert.bytes2Short(b);
		} else if (b.length == 4) {
			return (long) ByteConvert.bytes2Int(b);
		} else {
			return null;
		}
	}

	/** 1、2、4字节的可变长度字节数组转UInt，低字节在前，高字节在后 */
	public static Long variableBytes2UInt(byte[] b) {
		if(b == null){
			return null;
		}
		if (b.length == 1) {
			return (long)byte2Ubyte(b[0]);
		} else if (b.length == 2) {
			return (long)bytes2UShort(b);
		} else if (b.length == 4) {
			return bytes2UInt(b);
		} else {
			return null;
		}
	}
	
	/**
	 * 把低字节在前，高字节在后的字节数组转换为short
	 * @param b	低字节在前
	 * @return
	 */
	public static Short bytes2Short(byte[] b) {
		if(b == null || b.length < 2){
			return null;
		}
        short s = 0; 
        short s0 = (short) (b[0] & 0xff);// 最低位 
        short s1 = (short) (b[1] & 0xff); 
        s1 <<= 8; 
        s = (short) (s0 | s1); 
        return s; 
    }
	
	/** 把低字节在前，高字节在后 */
	public static Integer bytes2UShort(byte[] b) {
		if(b == null || b.length < 2){
			return null;
		}
		int s = 0; 
		int s0 = (int) (b[0] & 0xff);// 最低位 
		int s1 = (int) (b[1] & 0xff); 
        s1 <<= 8; 
        s = (int) (s0 | s1); 
        return s; 
    }
 
	/**
	 * 把short转换为低字节在前，高字节在后的字节数组
	 * @param n
	 * @return
	 */
	public static byte[] short2Bytes(Short n) {
		if(n == null){
			return null;
		}
		byte [] b =  new   byte [ 2 ];  
		b[0 ] = ( byte ) (n &  0xff );  
		b[1 ] = ( byte ) (n >>  8  &  0xff );  
		return  b;  
	} 
	
	/**
	 * 把一个字节转换为无符号整型(0-255)
	 * @param b
	 * @return int 范围：0-255
	 */
	public static int byte2Ubyte(Byte b) {
		return b & 0xff;
	}

	/**
	 * 将一个无符号整型转为一个字节，注意：整型值不能大于255,否则会造成计算错误
	 * @param n 范围：0-255
	 */
	public static Byte ubyte2Byte(Integer n) {
		if(n == null){
			return null;
		}
		byte b = (byte) (n & 0xff);
		return b;
	}

	/** 将字节数组处理成16进制格式字符串 */
	public static String bytes2HexString(byte[] bytes) {
		if(bytes == null){
			return null;
		}
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		//将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			sb.append(HexArray[(bytes[i] & 0xf0) >> 4]);
			sb.append(HexArray[(bytes[i] & 0x0f) >> 0]);
        }
        return sb.toString();
    }
	
	/** 将单个字节处理成16进制格式字符串 */
	public static String byte2HexString(Byte b) {
		if(b == null){
			return null;
		}
		StringBuilder sb = new StringBuilder(2);
		sb.append(HexArray[(b & 0xf0) >> 4]);
		sb.append(HexArray[(b & 0x0f) >> 0]);
		return sb.toString();
	}

	/** 将16进制格式字符串处理成字节数组 */
	public static byte[] hexString2Bytes(String hexString) {
		if(hexString == null){
			return null;
		}
		char[] hexCharArray = hexString.toCharArray();
		int length = hexCharArray.length / 2;
		byte[] rawData = new byte[length];
		for (int i = 0; i < length; i++) {
			int high = Character.digit(hexCharArray[i * 2], 16);
			int low = Character.digit(hexCharArray[i * 2 + 1], 16);
			int value = (high << 4) | low;
			if (value > 127)
				value -= 256;
			rawData[i] = (byte) value;
		}
		return rawData;
	}
	
	/**
	 * 移除转义符
	 * @param bytes
	 * @return
	 */
	public static byte[] dislodgeShiftCharacter(byte[] bytes) {
		if (bytes == null || bytes.length == 0){
			return null;
		}
		String hexString = ByteConvert.bytes2HexString(bytes);
		hexString = hexString.replace("5E7D", "7E");
		hexString = hexString.replace("5E5D", "5E");
		
		return ByteConvert.hexString2Bytes(hexString);
	}
	
	/**
	 * 移除转义符，按字节一个一个转换
	 * @param bytes
	 * @return
	 */
	public static byte[] dislodgeShiftByte(byte[] bytes) {
		if (bytes == null || bytes.length == 0){
			return null;
		}
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (int i=0; i<bytes.length; i++) {
			byte[] temp = new byte[1];
			byte b = bytes[i];
			if (b == (byte)0x5E && i<bytes.length-1) {
				byte b2 = bytes[++i];
				if (b2 == (byte) 0x5D) {
					temp[0] = (byte) 0x5E;
				} else if (b2 == (byte) 0x7D) {
					temp[0] = (byte) 0x7E;
				} else {
					return null; 
				}
			} else {
				temp[0] = b;
			}
			try {
				baos.write(temp);
			} catch (IOException e) {
				e.printStackTrace();
				
				return null;
			}
		}
		
		return baos.toByteArray();
	}
	
	/**
	 * 转义，使用hex字符串直接替换
	 * @param bytes
	 * @return
	 */
	public static byte[] shiftCharacter(byte[] bytes) {
		if (bytes == null || bytes.length == 0){
			return null;
		}
		String hexString = ByteConvert.bytes2HexString(bytes);
		hexString = hexString.replace("5E", "5E5D");
		hexString = hexString.replace("7E", "5E7D");
		
		return ByteConvert.hexString2Bytes(hexString);
	}
	
	/**
	 * 转义，一个一个字节的转换
	 * @param bytes
	 * @return
	 */
	public static byte[] shiftByte(byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (byte b : bytes) {
			byte[] temp = null;
			if (b == (byte)0x5E) {
				temp = new byte[2];
				temp[0] = (byte)0x5E;
				temp[1] = (byte)0x5D;
			} else if (b == (byte)0x7E) {
				temp = new byte[2];
				temp[0] = (byte)0x5E;
				temp[1] = (byte)0x7D;
			} else {
				temp = new byte[1];
				temp[0] = b;
			}
			
			try {
				baos.write(temp);
			} catch (IOException e) {
				e.printStackTrace();
				
				return null;
			}
		}
		
		return baos.toByteArray();
	}
	
	//该方法等同于Integer.toBinaryString(b)
	public static String byte2bits(byte b) {
		int z = b;
		z |= 256;
		String str = Integer.toBinaryString(z);
		int len = str.length();
		return str.substring(len - 8, len);
	}

	//将二进制字符串转换回字节
	public static byte bit2byte(String bString){
		byte result=0;
		for(int i=bString.length()-1,j=0;i>=0;i--,j++){
			result+=(Byte.parseByte(bString.charAt(i)+"")*Math.pow(2, j));
		}
		return result;
	}
	
	/**
	 * Byte转Bit
	 */
	public static String byteToBit(byte b) {
		return "" +(byte)((b >> 7) & 0x1) + 
		(byte)((b >> 6) & 0x1) + 
		(byte)((b >> 5) & 0x1) + 
		(byte)((b >> 4) & 0x1) + 
		(byte)((b >> 3) & 0x1) + 
		(byte)((b >> 2) & 0x1) + 
		(byte)((b >> 1) & 0x1) + 
		(byte)((b >> 0) & 0x1);
	}
	/**
	 * Bit转Byte
	 */
	public static byte BitToByte(String byteStr) {
		int re, len;
		if (null == byteStr) {
			return 0;
		}
		len = byteStr.length();
		if (len != 4 && len != 8) {
			return 0;
		}
		if (len == 8) {// 8 bit处理
			if (byteStr.charAt(0) == '0') {// 正数
				re = Integer.parseInt(byteStr, 2);
			} else {// 负数
				re = Integer.parseInt(byteStr, 2) - 256;
			}
		} else {//4 bit处理
			re = Integer.parseInt(byteStr, 2);
		}
		return (byte) re;
	}
	
//	public static void main(String[] args) {
//		System.out.println("=================== test byte convert =============");
//		byte[] byteArray;
////		byteArray = long2Bytes(9223372036854775807L);
////		byteArray = long2Bytes(20120214171412L);
////		System.out.println(bytes2Long(byteArray));
////		byteArray = short2Bytes((short) 32767);
////		System.out.println(bytes2Short(byteArray));
////		 byteArray = ushort2Bytes((short) 65535);
////		 System.out.println(bytes2Ushort(byteArray));
////		 byteArray = ubyte2Bytes(127);
////		 byteArray[0] = (byte) 0x81;
////		 System.out.println(bytes2Ubyte(byteArray));
//		//hex转时间
////		byteArray = hexString2Bytes("FFFF");
////		long ddd = bytes2UShort(byteArray);
////		System.out.println(ddd);
////
////		byte b = ubyte2Byte(127);
////		System.out.println(byte2HexString(b));
////
////		System.out.println("===========================================");
////
////		byteArray = hexString2Bytes("7FFF");
////		System.out.println(bytes2UShort(byteArray));
////		System.out.println(bytes2Short(byteArray));
////		byteArray = hexString2Bytes("FF7F");
////		System.out.println("" + bytes2UShort(byteArray) + "," + (bytes2UShort(byteArray)-65536) + "," + bytes2Short(byteArray));
////		byteArray = hexString2Bytes("0080");
////		System.out.println("" + bytes2UShort(byteArray) + "," + (bytes2UShort(byteArray)-65536) + "," + bytes2Short(byteArray));
////		byteArray = hexString2Bytes("0180");
////		System.out.println("" + bytes2UShort(byteArray) + "," + (bytes2UShort(byteArray)-65536) + "," + bytes2Short(byteArray));
////		byteArray = hexString2Bytes("FFFF");
////		System.out.println("" + bytes2UShort(byteArray) + "," + (bytes2UShort(byteArray)-65536) + "," + bytes2Short(byteArray));
////
////
////		//时间转hex
////		byteArray = int2Bytes((int)(DateTime.toDate(20130304191015L).getTime()/1000));
//////		int tempTime = bytes2Int(byteArray);
//////		System.out.println(bytes2HexString(byteArray));
////		//int转hex
////		byteArray = int2Bytes(3067434);
//////		System.out.println(bytes2HexString(byteArray));
//
//	}

}
