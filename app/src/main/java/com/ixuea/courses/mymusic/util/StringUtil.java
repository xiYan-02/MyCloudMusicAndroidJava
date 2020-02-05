package com.ixuea.courses.mymusic.util;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import static com.ixuea.courses.mymusic.util.Constant.REGEX_EMAIL;
import static com.ixuea.courses.mymusic.util.Constant.REGEX_PHONE;

/**
 * 字符串相关工具类
 */
public class StringUtil {

    /**
     * 是否是手机号
     *
     * @param value
     * @return
     */
    public static boolean isPhone(String value) {
        return value.matches(REGEX_PHONE);
    }

    /**
     * 是否是邮箱
     *
     * @param value
     * @return
     */
    public static boolean isEmail(String value) {
        return value.matches(REGEX_EMAIL);
    }

    /**
     * 是否符合密码格式
     *
     * @param value
     * @return
     */
    public static boolean isPassword(String value) {
        return value.length() >= 6 && value.length() <= 15;
    }

    /**
     * 是否符合昵称格式
     *
     * @param value
     * @return
     */
    public static boolean isNickname(String value) {
        return value.length() >= 2 && value.length() <= 10;
    }

    /**
     * 是否符合验证码格式
     *
     * @param value
     * @return
     */
    public static boolean isCode(String value) {
        return value.length() == 4;
    }

    /**
     * 将一行字符串
     * 拆分为单个字
     * <p>
     * 只实现了中文
     * 英文是有问题的
     * 大家感兴趣可以在继续实现
     *
     * @param data
     * @return
     */
    public static String[] words(String data) {
        //创建一个列表
        List<String> results = new ArrayList<>();

        //转为char数组
        char[] chars = data.toCharArray();

        //循环每一个字符
        for (char c : chars
        ) {
            //转为字符串
            //冰添加到列表
            results.add(String.valueOf(c));
        }

        //转为数组
        return results.toArray(new String[results.size()]);
    }

    /**
     * 支队文本进行高亮
     * 不添加点击事件
     *
     * @param context
     * @param data
     * @return
     */
/*    public static SpannableString processHighlight(Context context, String data) {
        //查找@
        List<MatchResult> mentionsAndHashTags = RegUtil.findMentions(data);

        //匹配话题
        mentionsAndHashTags.addAll(RegUtil.findHashTags(data));

        //创建结果字符串
        //这个就是Android中的富文本字符串
        SpannableString result = new SpannableString(data);

        //遍历所有数据并处理
        for (MatchResult matchResult : mentionsAndHashTags) {
            //高亮文本
            ForegroundColorSpan span = new ForegroundColorSpan(context.getResources().getColor(R.color.text_highlight));

            //设置span
            //SPAN_EXCLUSIVE_EXCLUSIVE:不包括开始结束位置
            result.setSpan(span, matchResult.getStart(), matchResult.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        //返回结果
        return result;
    }*/

    /**
     * 处理文本添加点击事件
     *
     * @param context
     * @param data
     * @param onMentionClickListener
     * @param onHashTagClickListener
     * @return
     */
//    public static SpannableString processContent(Context context,
//                                                 String data,
//                                                 OnTagClickListener onMentionClickListener,
//                                                 OnTagClickListener onHashTagClickListener) {
//        //创建结果字符串
//        SpannableString result = new SpannableString(data);
//
//        //查找@
//        List<MatchResult> tags = RegUtil.findMentions(data);
//
//        //遍历
//        for (MatchResult matchResult : tags
//        ) {
//            processInner(result, matchResult, onMentionClickListener);
//        }
//
//        //查找话题
//        tags = RegUtil.findHashTags(data);
//
//        for (MatchResult matchResult : tags) {
//            processInner(result, matchResult, onHashTagClickListener);
//        }
//
//        //返回结果
//        return result;
//    }

    /**
     * 内部处理点击事件方法
     *
     * @param result
     * @param matchResult
     * @param onTagClickListener
     */
/*    private static void processInner(SpannableString result,
                                     MatchResult matchResult,
                                     OnTagClickListener onTagClickListener) {
        //设置点击事件
        result.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                onTagClickListener.onTagClick(matchResult.getContent(), matchResult);
            }
        }, matchResult.getStart(), matchResult.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }*/

    /**
     * 移除字符串中首的@
     * 移除首尾的#
     *
     * @param data
     * @return
     */
//    public static String removePlaceholderString(String data) {
//        if (data.startsWith(Constant.MENTION)) {
//            //@人字符串
//
//            //从1开始截取到末尾
//            return data.substring(1);
//        } else if (data.startsWith(Constant.HAST_TAG)) {
//            //话题字符串
//
//            //截取1~最后一个字符串
//            return data.substring(1, data.length() - 1);
//        }
//
//        //如果不满足格式
//        //就直接返回
//        return data;
//    }

    /**
     * 给用户id添加占位字符
     *
     * @param data
     * @return
     */
    public static String wrapperUserId(String data) {
        return "100" + data;
    }

    /**
     * 去除用户id占位符
     *
     * @param data
     * @return
     */
    public static String unwrapUserId(String data) {
        return data.substring(3);
    }

    /**
     * 格式化消息数量
     *
     * @param data
     * @return
     */
    public static String formatMessageCount(int data) {
        if (data > 99) {
            return "99+";
        }

        return String.valueOf(data);
    }
}
