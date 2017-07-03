package Sword2Offer;

/**
 *  替换空格
 */
public class P4_ReplaceSpace {
    public static String replaceSpace(StringBuffer str) {
        /*String temp = str.toString();
        return temp.replaceAll("\\s","%20");  //正则表达式匹配*/

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == ' '){
                sb.append('%');
                sb.append('2');
                sb.append('0');
            }else{
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = " We Are Happy. ";
        StringBuffer sb = new StringBuffer(str);
        System.out.println(replaceSpace(sb));

    }
}
