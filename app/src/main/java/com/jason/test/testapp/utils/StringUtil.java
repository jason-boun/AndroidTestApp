package com.jason.test.testapp.utils;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 字符串处理工具类
 */
public class StringUtil {

    /**
     * 获取包名
     *
     * @param context
     * @param template
     * @return
     */
    public static String getStringOfPackageName(Context context, String template) {
        return String.format(template, context.getPackageName());
    }

    /**
     * 剔除两侧空格后是否为空
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0);
    }

    public static boolean isNotBlank(String str) {
        return (str != null && str.trim().length() != 0);
    }

    /**
     * 是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    /**
     * 字符串是否相等
     *
     * @param actual
     * @param expected
     * @return
     */
    public static boolean isEquals(String actual, String expected) {
        return actual != null && actual.equals(expected);
    }

    /**
     * 预防空指针
     *
     * @param str
     * @return
     */
    public static String nullStrToEmpty(String str) {
        return (str == null ? "" : str);
    }

    /**
     * 首字母变大写
     *
     * @param str
     * @return
     */
    public static String capitalizeFirstLetter(String str) {
        if (isEmpty(str)) {
            return str;
        }

        char c = str.charAt(0);
        return (!Character.isLetter(c) || Character.isUpperCase(c)) ? str
                : new StringBuilder(str.length())
                .append(Character.toUpperCase(c))
                .append(str.substring(1)).toString();
    }

    /**
     * 转UTF8编码
     *
     * @param str
     * @return
     */
    public static String utf8Encode(String str) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(
                        "UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }

    /**
     * 转UTF8编码，带默认返回值
     *
     * @param str
     * @param defultReturn
     * @return
     */
    public static String utf8Encode(String str, String defultReturn) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return defultReturn;
            }
        }
        return str;
    }


    /**
     * html转字符串
     *
     * @param source
     * @return
     */
    public static String htmlEscapeCharsToString(String source) {
        return isEmpty(source) ? source : source.replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">").replaceAll("&amp;", "&")
                .replaceAll("&quot;", "\"");
    }

    /**
     * 全角转半角
     *
     * @param s
     * @return
     */
    public static String fullWidthToHalfWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == 12288) {
                source[i] = ' ';
                // } else if (source[i] == 12290) {
                // source[i] = '.';
            } else if (source[i] >= 65281 && source[i] <= 65374) {
                source[i] = (char) (source[i] - 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    /**
     * 半角转全角
     *
     * @param s
     * @return
     */
    public static String halfWidthToFullWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }
        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == ' ') {
                source[i] = (char) 12288;
            } else if (source[i] >= 33 && source[i] <= 126) {
                source[i] = (char) (source[i] + 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }


    /**
     * 拼接字符串集，默认逗号
     *
     * @param array
     * @return
     */
    public static String join(List<String> array) {
        if (array == null || array.size() == 0) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.size(); i++) {
                if (i != 0) {
                    sb.append(',');
                    sb.append(array.get(i));
                } else {
                    sb.append(array.get(i));
                }
            }
            return sb.toString();
        }
    }

    /**
     * 用ch拼接字符串集
     *
     * @param array
     * @param ch
     * @return
     */
    public static String join(List<String> array, char ch) {
        if (array == null || array.size() == 0) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.size(); i++) {
                if (i != 0) {
                    sb.append(ch);
                    sb.append(array.get(i));
                } else {
                    sb.append(array.get(i));
                }
            }
            return sb.toString();
        }
    }

    /**
     * 按字节长度截串
     *
     * @param str
     * @param len
     * @return
     */
    public static String getSub(String str, int len) {
        return getSub(str, len, "");
    }

    /**
     * 截取子串并拼接
     *
     * @param str
     * @param len
     * @param symbol
     * @return
     */
    public static String getSub(String str, int len, String symbol) {
        if (str == null)
            return "";
        try {
            int counterOfDoubleByte = 0;
            byte[] b = str.getBytes("gbk");
            if (b.length <= len)
                return str;
            for (int i = 0; i < len; i++) {
                if (b[i] < 0)
                    counterOfDoubleByte++; // 通过判断字符的类型来进行截取
            }
            if (counterOfDoubleByte % 2 == 0)
                str = new String(b, 0, len, "gbk") + symbol;
            else
                str = new String(b, 0, len - 1, "gbk") + symbol;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }
        return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }


    /**
     * 获取字符串的字节长度
     *
     * @param str
     * @return
     */
    public static int getLen(String str) {
        try {
            return str.getBytes("gbk").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }
        return 0;
    }

    /**
     * 对double进行格式化
     *
     * @param value
     * @param format
     * @return
     */
    public static String format(double value, String format) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(value);
    }

    /**
     * 格式化double，保留小数点3位
     *
     * @param value
     * @return
     */
    public static String format(double value) {
        String format = "#,###";
        DecimalFormat df = new DecimalFormat(format);
        return df.format(value);
    }


    /**
     * 字节转十六进制字符串
     *
     * @param bcd
     * @return
     */
    public static String bytesToHexStr(byte[] bcd) {
        char[] bcdLookup = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        String s = "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bcd.length; i++) {
            sb.append(bcdLookup[((bcd[i]) >> 4) & 0x0f]);
            sb.append(bcdLookup[bcd[i] & 0x0f]);
        }
        return sb.toString();
    }


    public static String escapeString(String input) {
        return input.replaceAll("\"", "&quot;")
                .replaceAll("\'", " &apos;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;");
    }

    public static String getFormattedNumber(double value) {
        return getFormattedNumber(value, false);
    }

    public static String getFormattedNumber(double value, boolean groupingUsed) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setGroupingUsed(groupingUsed);
        return nf.format(value);
    }

    public static String getFormattedCCurrency(double value) {
        String formatted = getFormattedNumber(value, false);
        if (formatted.contains(".")) {
            formatted = formatted.substring(0, formatted.indexOf('.'));
        }
        return formatted;
    }

    public static String handleNick(String nick, int num) {
        int baseCount = num;
        if (getLen(nick) > baseCount) {
            nick = nick.substring(0, num);
            while (getLen(nick) > baseCount) {
                num--;
                nick = nick.substring(0, num);
            }
        }
        return nick + "...";
    }

    /**
     * 在数字型字符串千分位加逗号
     *
     * @param str
     * @return
     */
    public static String addComma(String str) {
        boolean neg = false;
        if (str.startsWith("-")) { // 处理负数
            str = str.substring(1);
            neg = true;
        }
        String tail = null;
        if (str.indexOf('.') != -1) { // 处理小数点
            tail = str.substring(str.indexOf('.'));
            str = str.substring(0, str.indexOf('.'));
        }
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        for (int i = 3; i < sb.length(); i += 4) {
            sb.insert(i, ',');
        }
        sb.reverse();
        if (neg) {
            sb.insert(0, '-');
        }
        if (tail != null) {
            sb.append(tail);
        }
        return sb.toString();
    }

    /**
     * 在数字型字符串千分位删除逗号
     *
     * @param str
     * @return
     */
    public static String deleteComma(String str) {
        if (str != null) {
            str = str.replace(",", "");
        }
        return str;
    }

    /**
     * 获取bytes大小的格式化
     *
     * @param remainSize
     * @return
     */
    public static String getFormattedStorageSize(long remainSize) {
        double remain = 0;
        String[] unit = new String[]{"B", "K", "M", "G", "T"};
        if (remainSize > -1) {
            for (int i = 0; i < unit.length; i++) {
                if (remainSize / 1024 > 0) {
                    remain = remainSize / 1024d;
                    remainSize = remainSize / 1024;
                } else {
                    return String.format("%.2f", remain) + unit[i];
                }
            }
        }
        return "未知";
    }


    /**
     * @param str
     * @return
     */
    public static int strToInt(String str) {
        int i = 0;
        try {
            if (str.length() > 0) {
                i = Integer.parseInt(str);
            }
        } catch (Exception e) {
            return 0;
        }
        return i;
    }

    /**
     * 处理聊天String长度
     *
     * @param string
     * @return
     */
    public static int getStringLength(String string) {

        if (TextUtils.isEmpty(string)) {
            return 0;
        } else {
            int orignLen = string.length();
            int resultLen = 0;
            String temp;
            for (int i = 0; i < orignLen; i++) {
                temp = string.substring(i, i + 1);
                try {
                    if (temp.getBytes("utf-8").length == 3) {
                        resultLen += 2;
                    } else {
                        resultLen++;
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (resultLen % 2 == 0) {
                return resultLen / 2;
            } else {
                return resultLen / 2 + 1;
            }
        }
    }

    /**
     * 集合判定是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isCollectionEmpty(Collection collection) {
        if (collection == null || collection.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 集合判定是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isCollectionNotEmpty(Collection collection) {
        if (collection != null && collection.size() > 0) {
            return true;
        }
        return false;
    }

    public static String[] listCovertToArray(List<String> list) {
        Object[] res = list.toArray();
        if (res == null || res.length == 0) {
            return new String[0];
        }
        int size = res.length;
        String[] dest = new String[size];
        for (int i = 0; i < size; i++) {
            dest[i] = (String) res[i];
        }
        return dest;
    }


    /**
     * Json反序列化
     *
     * @param jsonStr
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T parseToJsonObject(String jsonStr, Class<T> t) {
        try {
            return new Gson().fromJson(jsonStr, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 处理数字显示逻辑
     * 规则说明：数量的数目最多4位，万以下用准确数值，万以上，单位为“万”，保留小数点后一位（取整数）；
     * 例如：3241app上表示为：3241；13999app上表示为：1.3万
     *
     * @param count
     * @return
     */
    public static String parseShowCount(int count) {
        String result = "0";
        if (count <= 0) {
            result = String.valueOf(0);
        } else if (count < 10000) {
            result = String.valueOf(count);
        } else {
            result = count / 10000 + "." + String.valueOf(count % 10000).substring(0, 1) + "万";
        }
        return result;
    }

    /**
     * 将long型转换为int值
     *
     * @param num
     * @return
     */
    public static int convertLongToInt(long num) {
        int result = 0;
        try {
            result = new Long(num).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 字符串做特殊处理以及MD5加密处理
     * 加密校验规则：原始encryptToken + timeStamp + random进行MD5加密处理
     *
     * @param redisToken
     * @param timeStamp
     * @param random
     * @return
     */
    public static String encryptPrPrMD5(String redisToken, String timeStamp, String random) {
        StringBuilder sb = new StringBuilder();
        String token = redisToken + timeStamp + random;
        int len = token.length();
        for (int i = 0; i < len; i++) {
            if (!isPrime(i)) {
                sb.append(token.charAt(i));
            }
        }
        return encryptedToMD5(sb.toString());
    }

    /**
     * 是否素数
     */
    private static boolean isPrime(int n) {
        if (n % 2 == 0) {
            return false;
        }
        int circle = (int) Math.sqrt(n);
        for (int i = 3; i <= circle; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取MD5加密后的字符串
     *
     * @param text
     * @return
     */
    public static String encryptedToMD5(String text) {
        String result = text;
        try {
            //生成实现指定摘要算法的 MessageDigest 对象。
            MessageDigest md = MessageDigest.getInstance("MD5");
            //使用指定的字节数组更新摘要。
            md.update(text.getBytes());
            //通过执行诸如填充之类的最终操作完成哈希计算。
            byte b[] = md.digest();
            //生成具体的md5密码到buf数组
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
//            System.out.println("32位: " + buf.toString());// 32位的加密
//            System.out.println("16位: " + buf.toString().substring(8, 24));// 16位的加密
            result = buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String hideMobiePhone(String mobiePhonenum) {
        if (mobiePhonenum.length() == 11) {
            String hide=mobiePhonenum.substring(0,3)+"****"+mobiePhonenum.substring(7,11);
            return hide;
        } else return "";
    }

    public static  boolean isMobileNum(String mobiePhonenum){
        if (mobiePhonenum.contains("@")||mobiePhonenum.length()!=11){
            return false;
        }
        for (int i = 0; i < mobiePhonenum.length(); i++) {
            char num=  mobiePhonenum.charAt(i);
            if (!Character.isDigit(num)){
                return false;
            }
        }
        return true;
    }

    /**
     * 校验字符串是否超限
     *
     * @param str     需要校验的字符串
     * @param limited 长度限定
     * @return
     */
    public static boolean validStringLength(String str, int limited) {
        boolean result = false;
        try {
            if (StringUtil.isNotBlank(str)) {
                result = str.getBytes("UTF-8").length <= limited ? true : false;
            } else {
                result = true;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 去掉字符串右侧空格
     *
     */
    public static String trimRight(String sString){
        String sResult = "";
        if (sString.startsWith(" ")){
            sResult = sString.substring(0,sString.indexOf(sString.trim().substring(0, 1))
                    +sString.trim().length());
        }
        else sResult = sString.trim();
        return sResult;
    }

    /**
     * 获取指定位数的随机数
     *
     * @return
     */
    public static String getRandomId(int count) {
        StringBuilder str = new StringBuilder();//定义变长字符串
        Random random = new Random();
        //随机生成数字，并添加到字符串
        for (int i = 0; i < count; i++) {
            str.append(random.nextInt(10));
        }
        //将字符串转换为数字并输出
        int num = Integer.parseInt(str.toString());
        return num + "";
    }

    /**
     * 通过UUID生成随机数
     * @return
     */
    public static String getIdByUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return machineId + String.format("%015d", hashCodeV);
    }

    /**
     * 字符串是否为一连串的"0"组成
     *
     * @param str
     * @return
     */
    public static boolean isZeroString(String str) {
        boolean result = true;
        if (StringUtil.isBlank(str)) {
            result = false;
        } else {
            for (int i = 0; i < str.length(); i++) {
                String item = String.valueOf(str.charAt(i));
                if (!"0".equals(item)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

}
