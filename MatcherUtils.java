package com.android.common.utils;


import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Matcher Utils
 */
public class MatcherUtils {

    /**
     * 数字
     *
     * @param number
     * @return
     */
    public static boolean isNumber(String number) {
        if (null == number || number.length() == 0) {
            return false;
        }

        return matches("^[0-9]*$", number);
    }

    /**
     * 字符串是否是数字
     *
     * @param str
     * @return
     * @deprecated
     */
    private static boolean isNumeric(String str) {
        if (null == str || str.length() == 0) {
            return false;
        }

        try {
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(str);
            if (!isNum.matches()) {
                return false;
            }
        } catch (Exception e) {
            // LogUtils.e(e.getMessage());
        }

        return true;
    }

    /**
     * 判断是否为整数
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }


    /**
     * 是否为URL
     *
     * @param src
     * @return
     */
    public static boolean isUrl(String src) {
        if (null == src || src.length() == 0) {
            return false;
        }

        return src.matches("^http://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$");
    }

    /**
     * URL
     *
     * @param url
     * @return
     */
    public static boolean isUrl2(String url) {
        if (null == url || url.length() == 0) {
            return false;
        }

        Pattern patt = Pattern
                .compile(
                        "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)" +
                                "*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*" +
                                "(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$",
                        Pattern.CASE_INSENSITIVE);

        Matcher matcher = patt.matcher(url);

        return matcher.matches();
    }

    /**
     * 判断email格式是否正确
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (null == email || email.length() == 0) {
            return false;
        }

        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2," +
                "4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    /**
     * [\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?
     * </pre>
     *
     * @param email
     * @return
     */
    public static boolean isEmail2(String email) {
        if (null == email || email.length() == 0 || !email.contains("@")) {
            return false;
        }

        if (email.length() <= 22) {
            String patternMail = "^([a-z0-9A-Z]+[_|-|\\.]?){0,}[a-z0-9A-Z]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

            return matches(patternMail, email);
        }

        String a[] = email.split("@");
        if (a.length == 2) {
            String patternMailLeft = "^([a-z0-9A-Z]+[_|-|\\.]?){0,}[a-z0-9A-Z]+$";
            String patternMailRight = "^([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            return matches(patternMailLeft, a[0]) && matches(patternMailRight, a[1]);
        }


        return false;
    }

    /**
     * 手机号码
     *
     * @param cellphone
     * @return
     */
    public static boolean isCellphone(String cellphone) {
        if (null == cellphone || cellphone.length() == 0) {
            return false;
        }

        String patternPhone = "^(13[0-9]|14[5,7]|15[0-9]|17[6-8]|18[0-9])[0-9]{8}$";

        return matches(patternPhone, cellphone);
    }

    /**
     * 15位手机号码
     *
     * @param cellphone
     * @return
     */
    public static boolean isCellphone15(String cellphone) {
        if (null == cellphone || cellphone.length() == 0) {
            return false;
        }

        String patternPhone = "^[0-9]{0,4}(13[0-9]|14[5,7]|15[0-9]|17[6-8]|18[0-9])[0-9]{8}$";

        return matches(patternPhone, cellphone);
    }

    /**
     * @param cellphone
     * @return
     */
    public static boolean isPhone(String cellphone) {
        if (null == cellphone || cellphone.length() == 0) {
            return false;
        }

        String patternPhone = "^[0-9\\,\\.\\#\\*\\(\\)\\+-\\;\\/\\s]+$";

        return matches(patternPhone, cellphone);
    }

    /**
     * 电话号码
     *
     * @param phoneNum
     * @return
     */
    public static boolean isPhoneNum(String phoneNum) {
        if (null == phoneNum || phoneNum.length() == 0) {
            return false;
        }

        Pattern patternPhoneNum = Pattern.compile("[0-9]{3,4}//-?[0-9]+");
        Matcher matcher = patternPhoneNum.matcher(phoneNum);

        return matcher.matches();
    }

    /**
     * 身份证
     * 判断一个字符串是不是身份证号码，即是否是15或18位数字。
     *
     * @param identityCard
     * @return
     */
    public static boolean isIdentityCard(String identityCard) {
        if (null == identityCard || identityCard.length() == 0) {
            return false;
        }

        Pattern patternStr = Pattern.compile("^//d{15}|//d{18}$");
        Matcher matcher = patternStr.matcher(identityCard);

        return matcher.matches();
    }

    /**
     * 校验汉子
     *
     * @param chinese
     * @return
     */
    public static boolean isChinese(String chinese) {
        if (null == chinese || chinese.length() == 0) {
            return false;
        }

        String checkChinese = "^[\u4e00-\u9fa5],{0,}$";

        return matches(checkChinese, chinese);
    }


    /**
     * Host
     *
     * @param host
     * @return
     */
    public static boolean isHost(String host) {
        if (null == host || host.length() == 0) {
            return false;
        }

        String checkHost = "^([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

        return matches(checkHost, host);
    }

    /**
     * IP
     *
     * @param ip
     * @return
     */
    public static boolean isIP(String ip) {
        if (null == ip || ip.length() == 0) {
            return false;
        }

        String checkIP = "^((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))$";

        return matches(checkIP, ip);
    }

    /**
     * @param regex      正则表达式
     * @param matcherStr 被检测字符串
     * @return
     */
    public static boolean matches(String regex, String matcherStr) {
        if (null == regex || null == matcherStr
                || regex.length() == 0 || matcherStr.length() == 0) {
            return false;
        }

        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(matcherStr);
            return matcher.matches();
        } catch (PatternSyntaxException e) {
            Logger.e(e);
            return false;
        }
    }

    public static boolean matches2(@NonNull String regex, @NonNull String matcherStr) {
        try {
            return matcherStr.matches(regex);
        } catch (Exception e) {
            Logger.e(e);
            return false;
        }
    }

}
