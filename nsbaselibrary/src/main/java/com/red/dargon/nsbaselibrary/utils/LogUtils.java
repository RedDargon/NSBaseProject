package com.red.dargon.nsbaselibrary.utils;

import android.util.Log;

/**
 *
 * Log工具类
 * 通过设置打印等级决定是否要打印Log
 */
public class LogUtils {

    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_WARNING = 3;
    public static final int LEVEL_ERROR = 4;

    public static final int LEVEL_NO_LOG = 5;

    private static int mLevel = LEVEL_VERBOSE;

    public static void setLogLevel(int level) {
        mLevel = level;
    }

    public static void setIsLog(boolean isLog) {
        mLevel = isLog ? LEVEL_VERBOSE : LEVEL_NO_LOG;
    }

    public static void v(String tag, String msg) {
        if (mLevel > LEVEL_VERBOSE) {
            return;
        }
        Log.v(tag, msg);
    }

    public static void v(String tag, String msg, Throwable throwable) {
        if (mLevel > LEVEL_VERBOSE) {
            return;
        }
        Log.v(tag, msg, throwable);
    }

    public static void d(String tag, String msg) {
        if (mLevel > LEVEL_DEBUG) {
            return;
        }
        Log.d(tag, msg);
    }

    public static void d(String tag, String msg, Throwable throwable) {
        if (mLevel > LEVEL_DEBUG) {
            return;
        }
        Log.d(tag, msg, throwable);
    }

    public static void i(String tag, String msg) {
        if (mLevel > LEVEL_INFO) {
            return;
        }
        Log.i(tag, msg);
    }

    public static void i(String tag, String msg, Throwable throwable) {
        if (mLevel > LEVEL_INFO) {
            return;
        }
        Log.i(tag, msg, throwable);
    }

    public static void w(String tag, String msg) {
        if (mLevel > LEVEL_WARNING) {
            return;
        }
        Log.w(tag, msg);
    }

    public static void w(String tag, String msg, Throwable throwable) {
        if (mLevel > LEVEL_WARNING) {
            return;
        }
        Log.w(tag, msg, throwable);
    }

    public static void e(String tag, String msg) {
        if (mLevel > LEVEL_ERROR) {
            return;
        }
        Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable throwable) {
        if (mLevel > LEVEL_ERROR) {
            return;
        }
        Log.e(tag, msg, throwable);
    }

    public static void log(String log){
        Log.d("weex_nuanshuidai",log);
    }


    /**
     * 上传日志
     *
     * @param url
     * @param msg
     */
//    public static void uploadLog(String uniqueId, String url, String msg) {
//        HashMap<String, Object> paramMap = new HashMap<String, Object>();
//        if (StringUtils.isEmpty(uniqueId) || StringUtils.isEmpty(url) || StringUtils.isEmpty(msg))
//            return;
//        paramMap.put(RequestParam.UNIQUEID, uniqueId);
//        paramMap.put(RequestParam.NETWORK, NetworkUtils.getNetworkType());
//        paramMap.put(RequestParam.OPERATOR, NetworkUtils.getProvidersName());
//        paramMap.put(RequestParam.TIME, TimeUtils.getFormatTime("yyyy年MM月dd日 HH:mm:ss:sss"));
//        paramMap.put(RequestParam.URL, url);
//        paramMap.put(RequestParam.MSG, msg);
//        LogUtils.log(paramMap.toString());
//    }

    /**
     * 记录日志
     *
     * @param msg
     */
//    public static void recordLog(String msg) {
//        HashMap<String, Object> paramMap = new HashMap<String, Object>();
//        if (StringUtils.isEmpty(msg))
//            return;
//        paramMap.put(RequestParam.NETWORK, NetworkUtils.getNetworkType());
//        paramMap.put(RequestParam.OPERATOR, NetworkUtils.getProvidersName());
//        paramMap.put(RequestParam.TIME, TimeUtils.getFormatTime("yyyy年MM月dd日 HH:mm:ss:sss"));
//        paramMap.put(RequestParam.MSG, msg);
//        LogUtils.log(paramMap.toString());
//    }

    /**
     * 上传log文件
     *
     * @param context
     */
//    public static void uploadLogFile(Context context, final boolean runBack) {
//        final List<String> logPaths = new ArrayList<>();
//        logPaths.add(LogUtils.getLogFilePath());
//        new UploadFileHelp(context).uploadImageByPath(logPaths, new UploadFileHelp.UploadCallBack() {
//            @Override
//            public void onProgress(int totalCount, ArrayList<QiniuResponseVO> doneImage, QiNiuTokenVO token) {
//                LogUtils.e("uploadLog", doneImage.toString());
//                if (totalCount == doneImage.size()) {
//                    Map<String, Object> params = new HashMap<String, Object>();
//                    for (int i = 0; i < doneImage.size(); i++) {
//                        QiniuResponseVO q = ListUtils.getSafeItem(doneImage, i);
//                        if (q != null) {
//                            params.put("logKey_" + i, q.key);
//                        }
//                    }
//                    UserService userService = ServiceLocator.GetInstance().getInstance(UserService.class);
//                    if (userService != null) {
//                        if (!StringUtils.isEmpty(userService.getCurrentUserId())) {
//                            params.put("userId", userService.getCurrentUserId());
//                        }
//                    }
//                    params.put("time", TimeUtils.getFormatTime("yyyy-MM-dd HH:mm:ss:sss"));
//                    if (!runBack) {
//                        ToastUtil.showToast("上传成功");
//                    }
//                    StatisticsUtils.logKey(params);
//                    FileUtils.deleteAllFiles(logPaths);
//                    configLog();
//                }
//            }
//        }, runBack);
//    }
}
