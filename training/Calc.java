
/**
 * 
 * @author xbkaishui
 * @version $Id: Calc.java, v 0.1 2016-6-30 上午10:28:22 xbkaishui Exp $
 */
public class Calc {

    public static void main(String[] args) {
        String expr = "1+36-7+4";
        long val = eval(expr);
        System.out.println(val);
    }

    /**
     * @param expr
     * @return
     */
    private static long eval(String expr) {
        long rs = 0;
        boolean firstIter = true;
        char oper = 0;
        String firNum = "";
        String secdNum = "";
        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);
            //判断是否是运算符合
            if (ch == '+' || ch == '-') {
                if (oper != 0) {
                    //计算
                    rs = calc(firNum, secdNum, oper);
                    oper = ch;
                    firNum = String.valueOf(rs);
                    secdNum = "";
                } else {
                    //第一次遇到运算符号
                    oper = ch;
                    firstIter = false;
                }
            }else if ( ch >= '0' && ch <= '9') {
              //如果是数字
                if (firstIter) {
                    firNum = firNum + ch;
                } else {
                    secdNum = secdNum + ch;
                }
            }
            //去除空白字符
            else if (ch == ' ') {
                continue;
            }
        }
        return rs;
    }

    /**
     * 
     * @param firNum
     * @param secdNum
     * @param oper
     * @return
     */
    private static long calc(String firNum, String secdNum, char oper) {
        if (oper == '+') {
            return Long.valueOf(firNum) + Long.valueOf(secdNum);
        } else if (oper == '-') {
            return Long.valueOf(firNum) - Long.valueOf(secdNum);
        }
        return 0;
    }
}
