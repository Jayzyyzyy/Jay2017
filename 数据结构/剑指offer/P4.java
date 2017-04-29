package 剑指offer;

/**
 *  替换空格
 */
public class P4 {
    public static String replaceSpace(String str) {
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
        String str = "We Are Happy.";
        System.out.println(replaceSpace(str));

    }
}
