package cn.edu.gdin.util;

import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  

import org.junit.Test;
  
public class MD5Utils {  
      
    /** 
     * md5���ܷ��� 
     * @param password 
     * @return 
     */  
    public static String md5Password(String password) {  
  
        try {  
            // �õ�һ����ϢժҪ��  
            MessageDigest digest = MessageDigest.getInstance("md5");  
            byte[] result = digest.digest(password.getBytes());  
            StringBuffer buffer = new StringBuffer();  
            // ��ûһ��byte ��һ�������� 0xff;  
            for (byte b : result) {  
                // ������  
                int number = b & 0xff;// ����  
                String str = Integer.toHexString(number);  
                if (str.length() == 1) {  
                    buffer.append("0");  
                }  
                buffer.append(str);  
            }  
  
            // ��׼��md5���ܺ�Ľ��  
            return buffer.toString();  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
            return "";  
        }  
  
    } 
    
    /*** 
     * MD5���� ����32λmd5�� 
     */  
    public static String string2MD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
  
    }  
  
    /** 
     * ���ܽ����㷨 ִ��һ�μ��ܣ����ν��� 
     */   
    public static String convertMD5(String inStr){  
  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
  
    }  
    
    
    
    @Test
    public void testMd5(){
    	String s = new String("tangfuqiang");  
        System.out.println("ԭʼ��" + s);  
        System.out.println("MD5��" + string2MD5(s));  
        System.out.println("���ܵģ�" + convertMD5(s));  
        System.out.println("���ܵģ�" + convertMD5(convertMD5(s)));  
    }
  
}  
