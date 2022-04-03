package algorithm;

import java.net.DatagramSocket;
import java.net.SocketException;

public class KMP {

    public static boolean kmp(String s1, String s2) {
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int cl1 = ch1.length;
        int cl2 = ch2.length;

        if(cl1 < cl2) {
            return false;
        }

        if(cl1 == cl2) {
            for(int i = 0; i < cl1; i++) {
                if(ch1[i] != ch2[i]) {
                    return false;
                }
            }
            return true;
        }

        int[] arr = next(s2);


        int i = 0;
        int j = 0;

        while (i<cl1 && j < cl2) {
            if(ch1[i]==ch2[j]) {
                i++;
                j++;
            }else {
                while (j>=0) {
                    if(ch1[i] == ch2[j]) {
                        break;
                    }else {
                        if(j==0) {
                            i++;
                            break;
                        }else {
                            j = arr[j];
                        }
                    }
                }
            }
        }

        if(j==cl2) {
            System.out.println(i+":"+s1.substring(i-s2.length(),i));
            return true;
        }
        return false;
    }

    public static int[] next(String s) {
        int[] arr = new int[s.length()];

        char[] c = s.toCharArray();

        arr[0] = 0;
        int len = 0;
        int i = 1;
        while (i<c.length) {
            if(c[i]==c[len]) {
                len++;
                arr[i] = len;
                i++;
            }else if(len > 0){
                len = arr[len-1];
            }else {
                i++;
            }
        }
        for(i = arr.length-2; i>= 0; i--) {
            arr[i+1] = arr[i];
        }
        arr[0] = -1;
        return arr;
    }

    public static void main(String[] args) throws SocketException {
        kmp("adfgsahahjjajs","jja");
        DatagramSocket datagramSocket = new DatagramSocket(99911);
    }
}
