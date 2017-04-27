package com.panku.pkBaseLibrary.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;


public class NetUtil {
    /**
     * 当前网络状态
     */
    private static NetState CURRENT_NETSTATE;

    /**
     * 获取网络状态
     *
     * @param context
     * @return
     */
    @Deprecated
    public static NetState getNetworkState(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            String type = networkInfo.getTypeName();

            if (type.equalsIgnoreCase("WIFI")) {
                CURRENT_NETSTATE = NetState.NETWORKTYPE_WIFI;
            } else if (type.equalsIgnoreCase("MOBILE")) {
                String proxyHost = android.net.Proxy.getDefaultHost();

                CURRENT_NETSTATE = isEmpty(proxyHost)
                        ? (isFastMobileNetwork(context) ? NetState.NETWORKTYPE_3G : NetState.NETWORKTYPE_2G)
                        : NetState.NETWORKTYPE_WAP;
            }
        } else {
            CURRENT_NETSTATE = NetState.NETWORKTYPE_INVALID;
        }

        return CURRENT_NETSTATE;
    }

    private static boolean isEmpty(String text) {
        if (text == null || "".equals(text)) {
            return true;
        }
        return false;
    }

    /**
     * 是否是快速网络
     *
     * @param context
     * @return
     */
    private static boolean isFastMobileNetwork(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return false; // ~ 14-64 kbps
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return true; // ~ 400-1000 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return true; // ~ 600-1400 kbps
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return false; // ~ 100 kbps
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return true; // ~ 2-14 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return true; // ~ 700-1700 kbps
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return true; // ~ 1-23 Mbps
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return true; // ~ 400-7000 kbps
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return true; // ~ 1-2 Mbps
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return true; // ~ 5 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return true; // ~ 10-20 Mbps
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return false; // ~25 kbps
            case TelephonyManager.NETWORK_TYPE_LTE:
                return true; // ~ 10+ Mbps
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return false;
            default:
                return false;
        }
    }

    public static NetState getNetworkType(Context context) {
        String strNetworkType = "";
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                CURRENT_NETSTATE = NetState.NETWORKTYPE_WIFI;
                strNetworkType = "WIFI";
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String _strSubTypeName = networkInfo.getSubtypeName();
                Log.e("NetUtil", "Network getSubtypeName : " + _strSubTypeName);
                // TD-SCDMA   networkType is 17
                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        CURRENT_NETSTATE = NetState.NETWORKTYPE_2G;
                        strNetworkType = "2G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        CURRENT_NETSTATE = NetState.NETWORKTYPE_3G;
                        strNetworkType = "3G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                        CURRENT_NETSTATE = NetState.NETWORKTYPE_4G;
                        strNetworkType = "4G";
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA") || _strSubTypeName.equalsIgnoreCase("WCDMA") || _strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            CURRENT_NETSTATE = NetState.NETWORKTYPE_3G;
                            strNetworkType = "3G";
                        } else {
                            CURRENT_NETSTATE = NetState.NETWORKTYPE_UNKOOW;
                            strNetworkType = "unkown";
                        }
                        break;
                }

                Log.e("NetUtil", "Network getSubtype : " + Integer.valueOf(networkType).toString());
            }
        } else {
            strNetworkType = "INVALID";
            CURRENT_NETSTATE = NetState.NETWORKTYPE_INVALID;
        }
        Log.e("NetUtil", "Network Type : " + strNetworkType);
        return CURRENT_NETSTATE;
    }

    /**
     * 重置网络状态
     *
     * @param context
     */
    public static void reSetNetState(Context context) {
        NetUtil.CURRENT_NETSTATE = getNetworkType(context);
    }

    /**
     * 获得当前网络状态
     *
     * @return
     */
    public static NetState getCurrentNetState(Context context) {
        reSetNetState(context);
        return NetUtil.CURRENT_NETSTATE;
    }


}