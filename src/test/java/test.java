import java.math.BigDecimal;

/**
 * @program: weixin
 * @description:
 * @author: cwf
 * @create: 2020-02-19 17:07
 **/
public class test {
    public static void main(String[] args) {
        String a="0.5";
        Double aDouble = Double.valueOf(a);
        System.out.println(aDouble);

        String str1="2.30";
        BigDecimal bd=new BigDecimal(str1);
        System.out.println(bd);
    }
}
