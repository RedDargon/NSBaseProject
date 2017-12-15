package com.red.dargon.nsbaselibrary.action;

import android.content.Context;

/**
 * Action工具类。
 */
public class ActionUtils {
    public static final String TAG = "ActionUtils";
    private static String ACTION_TYPE = "";

    private static IActionHelper sActionHelper;

    public static void initActionType(String actiontype) {
        ACTION_TYPE = actiontype;
    }

    public static void toActionType(Context context, int type) {
        notificationAction(context,
                new TwAction.Builder().add(ACTION_TYPE, Integer.toString(type)).build());

    }

    public static void init(IActionHelper actionHelper) {
        sActionHelper = actionHelper;
    }

    /**
     * 根据通知action做自定义操作.
     */
    public static void notificationAction(Context context, String actionArgument) {
        if (sActionHelper == null) {
            return;
        }
        TwAction twAction = new TwAction.Builder().setStrTwAction(actionArgument).build();
        sActionHelper.notificationAction(context, twAction);//具体的实现细节交给了 ActionHelper
    }

    public static void notificationAction(Context context, TwAction twAction) {
        if (sActionHelper == null) {
            return;
        }
        sActionHelper.notificationAction(context, twAction);
    }


    public interface IActionHelper {
        void notificationAction(Context context, TwAction twAction);
    }

    public static boolean inited() {
        return sActionHelper != null;
    }
}
