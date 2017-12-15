package com.red.dargon.nsbaselibrary.action;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 跳转协议类.
 */
public class TwAction {
    public static final String TAG = TwAction.class.getSimpleName();
    private static String SCHEME = "";
    private static String SCHEME_LINK = "";
    private static String PARAM_LINK = "";
    private static String VALUE_LINK = "";
    private static String ACTION_TYPE = "";


    private String mScheme = SCHEME;


    private TreeMap<String, Object> mParams = new TreeMap<>();

    public static void initTwAction(String scheme, String schemeLink, String paramLink, String valueLink, String actionType) {
        SCHEME = scheme;
        SCHEME_LINK = schemeLink;
        PARAM_LINK = paramLink;
        VALUE_LINK = valueLink;
        ACTION_TYPE = actionType;
    }


    private TwAction(Builder builder) {
        if (!isEmpty(builder.strTwAction)) {
            parseStrAction(builder.strTwAction);
        }
        if (!isEmpty(builder.scheme)) {
            mScheme = builder.scheme;
        }
        if (builder.mMap != null) {
            mParams.putAll(builder.mMap);
        }
    }


    private void parseStrAction(String action) {
        if (isEmpty(action)) {
            return;
        }
        action = action.trim();
        try {
            // 进行解码, 防止出现乱码.
            action = URLDecoder.decode(action, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (action.contains(SCHEME_LINK)) {
            int index = action.indexOf(SCHEME_LINK);
            mScheme = action.substring(0, index);
            String param = action.substring(index + SCHEME_LINK.length(), action.length());
            String[] params = param.split(PARAM_LINK);
            for (String paramStr : params) {
                try {
                    String[] keyValue = paramStr.split(VALUE_LINK, 2);
                    mParams.put(keyValue[0], keyValue[1]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                JSONObject jsonObject = new JSONObject(action);
                parseJsonAction(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseJsonAction(JSONObject action) {
        if (action == null) {
            return;
        }
        Iterator<String> keys = action.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            try {
                mParams.put(key, action.get(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String getScheme() {
        if (isEmpty(mScheme)) {
            mScheme = SCHEME;
        }
        return mScheme;
    }

    public int getActiontype() {
        return getIntValue(ACTION_TYPE);
    }

    /**
     * 获取某个参数的值.
     */
    public String getValue(String key) {
        if (mParams == null || isEmpty(key)) {
            return null;
        }
        return (String) mParams.get(key);
    }

    /**
     * 获取某个参数的值.
     */
    public int getIntValue(String key) {
        int value = 0;
        if (mParams != null && !isEmpty(key)) {
            try {
                if (mParams.containsKey(key)) {
                    String valueStr = (String) mParams.get(key);
                    value = Integer.parseInt(valueStr);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return value;
    }

    /**
     * 获取所有参数.
     */
    public Map<String, Object> getAllParams() {
        return mParams;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getScheme());
        sb.append(SCHEME_LINK);
        if (mParams != null) {
            for (Map.Entry entry : mParams.entrySet()) {
                if (entry != null && entry.getKey() != null && entry.getValue() != null) {
                    sb.append(entry.getKey()).append(VALUE_LINK).append(entry.getValue()).append(PARAM_LINK);
                }
            }
        }
        if (sb.substring(sb.length() - PARAM_LINK.length()).equals(PARAM_LINK)) {
            sb.replace(sb.length() - PARAM_LINK.length(), sb.length(), "");
        }
        return sb.toString();
    }

    public static class Builder {
        private String strTwAction;
        private String scheme;
        private TreeMap<String, String> mMap = new TreeMap<>();

        public Builder() {
            this(null);
        }

        public Builder(String strTwAction) {
            this.strTwAction = strTwAction;
        }

        public Builder setScheme(String scheme) {
            Builder.this.scheme = scheme;
            return Builder.this;
        }

        public Builder add(String key, String value) {
            mMap.put(key, value);
            return Builder.this;
        }

        /**
         */
        public Builder setStrTwAction(String strTwAction) {
            if (!isEmpty(strTwAction)) {
                scheme = null;
                mMap.clear();
                Builder.this.strTwAction = strTwAction;
            }
            return Builder.this;
        }

        public TwAction build() {
            return new TwAction(Builder.this);
        }

    }

    private static boolean isEmpty(String str) {
        return str == null || str.trim().equals("");
    }
}
