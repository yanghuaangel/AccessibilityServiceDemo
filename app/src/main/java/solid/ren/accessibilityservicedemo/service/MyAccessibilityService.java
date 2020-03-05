package solid.ren.accessibilityservicedemo.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

import solid.ren.accessibilityservicedemo.MainActivity;
import solid.ren.accessibilityservicedemo.PrintUtils;

/**
 * Created by _SOLID
 * Date:2016/7/20
 * Time:16:19
 */
public class MyAccessibilityService extends AccessibilityService {
    private final String qutoutiao = "com.jifen.qukan";
    private final String shuabao = "com.jm.video";
    private final String huoshan = "com.ss.android.ugc.livelite";
    private final String shandian = "c.l.a";
    private final String tishi = "com.android.systemui";
    private final String android = "android";
    //快看点
    private final String kuaikandian = "com.yuncheapp.android.pearl";
    //抖音极速版
    private final String douyin = "com.ss.android.ugc.aweme.lite";
    //米读极速版
    private final String midu = "com.lechuan.mdwz";
    //疯读小说
    private final String fengdu = "com.cootek.crazyreader";
    //快手极速版
    private final String kuaishou = "com.kuaishou.nebula";
    //抖音短视频
    private final String douying = "com.ss.android.ugc.aweme";
    //微视
    private final String weishi = "com.tencent.weishi";
    //小吃货视频
    private final String xch = "com.video.xch";
    //玩转星球
    private final String wzxq = "com.planet.light2345";
    //快逗
    private final String kd = "com.video.kd";
    //彩蛋视频
    private final String cdsp = "com.jifen.dandan";
    //有颜短视频
    private final String yydsp = "com.video.yy";
    //刷爆短视频
    private final String sbdsp = "com.gaoyuan.cvideo";
    //高手短视频
    private final String gs = "com.video.gs";


    private int recentCount = 0;
    private Handler mHandler;

    private boolean isInMESSAGE = false;
    private long lastscrolltime;

//    private boolean isdebug = true;


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        PrintUtils.printEvent(event);
//        performGlobalAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            if(isdebug){
//                mHandler.sendEmptyMessageDelayed(100,30_000);
//            }
            cc();

            try {
                for (CharSequence txt : event.getText()) {
                    Log.d("yanghua", txt.toString());
                    if (txt.toString().contains("看视频辛苦")) {
                        Log.d("yanghua", "找到 看视频辛苦");
//                    listxk.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        removeMessage(100);
                        mHandler.sendEmptyMessageDelayed(100, 3_000);
                    }

                }

                if (event.getSource() == null) {
                    return;
                }


//                List<AccessibilityNodeInfo> listxk  = event.getSource().findAccessibilityNodeInfosByText("看视频辛苦");
//                if(listxk == null || listxk.size() == 0 ){
////                    Log.d("yanghua", "没有找到广告 关闭广告");
//                }else {
//                    Log.d("yanghua", "找到 看视频辛苦");
////                    listxk.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                    removeMessage(100);
//                    mHandler.sendEmptyMessageDelayed(100,3_000);
//
//
//                }

                if (!isMoneyApp()) {
                    return;
                }
//                List<AccessibilityNodeInfo> list0  = event.getSource().findAccessibilityNodeInfosByText("0");
//                if(list0 == null || list0.size() == 0 ){
////                    Log.d("yanghua", "没有找到视频奖励按钮");
//                }else {
//                    Log.d("yanghua", "找到了倒计时0");
//                    list0.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                }

                List<AccessibilityNodeInfo> list = event.getSource().findAccessibilityNodeInfosByText("看视频奖励最高翻");
                if (list == null || list.size() == 0) {
//                    Log.d("yanghua", "没有找到视频奖励按钮");
                } else {
                    Log.d("yanghua", "找到了视频奖励按钮");
                    list.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    mHandler.sendEmptyMessageDelayed(100, 38_000);


                    mHandler.removeMessages(11);
                    mHandler.sendEmptyMessageDelayed(11, MainActivity.time * 1000);
                    lastscrolltime = System.currentTimeMillis();
                }

                List<AccessibilityNodeInfo> list1 = event.getSource().findAccessibilityNodeInfosByViewId("com.video.xch:id/tt_video_ad_close_layout");
                if (list1 == null || list1.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    if (!Build.MODEL.contains("Redmi") ) {
                        Log.d("yanghua", "找到了广告X");
                        list1.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        removeMessage(100);
                    }


                }
                List<AccessibilityNodeInfo> listxch = event.getSource().findAccessibilityNodeInfosByViewId("com.video.xch:id/img_close");
                if (listxch == null || listxch.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    Log.d("yanghua", "找到了广告X");
//                    mHandler.removeMessages(100);
                    listxch.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);

                }

//                List<AccessibilityNodeInfo> listyzm  = event.getSource().findAccessibilityNodeInfosByText("已转满");
//                if(listyzm == null || listyzm.size() == 0 ){
////                    Log.d("yanghua", "没有找到视频奖励按钮");
//                }else {
//                    Log.d("yanghua", "找到了XX");
//                    mHandler.removeMessages(100);
//                }

                List<AccessibilityNodeInfo> listyy = event.getSource().findAccessibilityNodeInfosByViewId("com.video.yy:id/tt_video_ad_close_layout");
                if (listyy == null || listyy.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    if (!Build.MODEL.contains("Redmi") ) {
                        Log.d("yanghua", "找到了广告X");
                        listyy.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        removeMessage(100);
                    }
                }

                List<AccessibilityNodeInfo> listyyimg = event.getSource().findAccessibilityNodeInfosByViewId("com.video.yy:id/img_close");
                if (listyyimg == null || listyyimg.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    Log.d("yanghua", "找到了广告X");
                    listyyimg.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                    mHandler.removeMessages(100);
                }

                List<AccessibilityNodeInfo> listkd = event.getSource().findAccessibilityNodeInfosByViewId("com.video.kd:id/tt_video_ad_close_layout");
                if (listkd == null || listkd.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    if (!Build.MODEL.contains("Redmi") ) {
                        Log.d("yanghua", "找到了广告X");
                        listkd.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        removeMessage(100);
                    }
                }

                List<AccessibilityNodeInfo> listkdimg = event.getSource().findAccessibilityNodeInfosByViewId("com.video.kd:id/img_close");
                if (listkdimg == null || listkdimg.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    Log.d("yanghua", "找到了广告X");
                    listkdimg.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                    mHandler.removeMessages(100);
                }

                List<AccessibilityNodeInfo> listgs = event.getSource().findAccessibilityNodeInfosByViewId("com.video.gs:id/tt_video_ad_close_layout");
                if (listgs == null || listgs.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    if (!Build.MODEL.contains("Redmi") ) {
                        Log.d("yanghua", "找到了广告X");
                        listgs.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        removeMessage(100);
                    }
                }

                List<AccessibilityNodeInfo> listgsimg = event.getSource().findAccessibilityNodeInfosByViewId("com.video.gs:id/img_close");
                if (listgsimg == null || listgsimg.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    Log.d("yanghua", "找到了广告X");
                    listgsimg.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                    mHandler.removeMessages(100);
                }

                List<AccessibilityNodeInfo> list2 = event.getSource().findAccessibilityNodeInfosByText("关闭广告");
                if (list2 == null || list2.size() == 0) {
//                    Log.d("yanghua", "没有找到广告 关闭广告");
                } else {
                    if (!Build.MODEL.contains("Redmi") ) {
                        Log.d("yanghua", "找到了广告 关闭广告");
                        list2.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        removeMessage(100);
                    }

                }

                if (System.currentTimeMillis() > lastscrolltime + MainActivity.time * 1000) {
                    doScroll();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void removeMessage(int i) {
        if (mHandler.hasMessages(i)) {
            mHandler.removeMessages(i);
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void cc() {

        //AccessibilityService.GLOBAL_ACTION_BACK
        //GLOBAL_ACTION_HOME
        //GLOBAL_ACTION_NOTIFICATIONS
        //GLOBAL_ACTION_RECENTS
        if (!mHandler.hasMessages(11)) {

            Log.d("yanghua", "多少秒滑动一次 = " + MainActivity.time);
            mHandler.sendEmptyMessageDelayed(11, MainActivity.time * 1000);
            isInMESSAGE = true;
        }


    }

    @Override
    public void onInterrupt() {
        PrintUtils.log("onInterrupt");
    }

    @Override
    protected boolean onGesture(int gestureId) {
        PrintUtils.log("onGesture");
        return super.onGesture(gestureId);
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        PrintUtils.log("onServiceConnected");
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
//                performGlobalAction(AccessibilityService.GESTURE_SWIPE_LEFT);1`
                if (msg.what == 11) {
                    if (System.currentTimeMillis() > lastscrolltime + MainActivity.time * 1000) {
                        doScroll();
                    }
                } else if (msg.what == 100) {
//                    if (Build.MODEL.contains("Redmi") || Build.MODEL.contains("ONEPLUS")) {
                    if(Build.MODEL.contains("Redmi")){
                        doClick();
                    }
//                    if(recentCount > 1){
//                        recentCount =0;
//                        return;
//                    }
//                    recentCount++;
//                    sendEmptyMessageDelayed(100,1500);
//                    performGlobalAction(AccessibilityService.GLOBAL_ACTION_RECENTS);

                }
//                }else if(msg.what == 101){
//                    Log.d("yanghua", "101  ================================= ");
//                    mHandler.sendEmptyMessageDelayed(101,2000);
//                }

            }
        };


//        //可用代码配置当前Service的信息

//        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
//        info.packageNames = new String[]{"com.android.packageinstaller", "com.tencent.mobileqq", "com.trs.gygdapp"}; //监听过滤的包名
//        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK; //监听哪些行为
//        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN; //反馈
//        info.notificationTimeout = 100; //通知的时间
//        setServiceInfo(info);
    }

    private void doClick() {
        Path path = new Path();


        //这个要分机型判断了，
        //小米8se 967 69

        //红米6 644 75
        if (Build.MODEL.contains("Redmi")) {
            path.moveTo(644, 75);
            Log.d("yanghua", "Redmi 点击 = ");
        } else {
            path.moveTo(984, 195);
            Log.d("yanghua", "ONEPLUS 点击 = ");
        }
//            path.lineTo(200, 120);


//先横滑
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            final GestureDescription.StrokeDescription sd = new GestureDescription.StrokeDescription(path, 1, 1);
            boolean isGestureDispath = dispatchGesture(new GestureDescription.Builder().addStroke(sd).build(), new GestureResultCallback() {
                @Override
                public void onCompleted(GestureDescription gestureDescription) {
                    super.onCompleted(gestureDescription);
                    Log.d("yanghua", "点击 = ");
                }

                @Override
                public void onCancelled(GestureDescription gestureDescription) {
                    super.onCancelled(gestureDescription);
                    Log.d("yanghua", "滑动取消 = ");
                }
            }, new Handler());
            Log.d("yanghua", "isGestureDispath = " + isGestureDispath);
        }

    }

    private boolean isMoneyApp() {
        return PrintUtils.currentPn.equals(qutoutiao)
                || PrintUtils.currentPn.equals(shuabao)
                || PrintUtils.currentPn.equals(huoshan) || PrintUtils.currentPn.equals(shandian)
                || PrintUtils.currentPn.equals(tishi)
                || PrintUtils.currentPn.equals(kuaikandian)
                || PrintUtils.currentPn.equals(douyin)
                || PrintUtils.currentPn.equals(midu)
                || PrintUtils.currentPn.equals(fengdu)
                || PrintUtils.currentPn.equals(kuaishou)
                || PrintUtils.currentPn.equals(douying)
                || PrintUtils.currentPn.equals(weishi)
                || PrintUtils.currentPn.equals(xch)
                || PrintUtils.currentPn.equals(wzxq)
                || PrintUtils.currentPn.equals(kd)
                || PrintUtils.currentPn.equals(cdsp)
                || PrintUtils.currentPn.equals(yydsp)
                || PrintUtils.currentPn.equals(sbdsp)
                || PrintUtils.currentPn.equals(gs)
                || PrintUtils.currentPn.equals(android);
    }

    private void doScroll() {
        lastscrolltime = System.currentTimeMillis();


        if (!isMoneyApp()) {
            mHandler.sendEmptyMessageDelayed(11, MainActivity.time * 1000);
            return;
        }
        Log.d("yanghua", "多少秒滑动一次 = " + MainActivity.time);
        Path path = new Path();
        //竖着滑动
        if (MainActivity.RadioGroupId == 0) {
            Log.d("yanghua", "现在上滑动 = ");
            path.moveTo(300, 1000);
            path.lineTo(300, 600);
        } else {
            Log.d("yanghua", "现在左右滑动 = ");
            path.moveTo(800, 120);
            path.lineTo(200, 120);
        }

//先横滑
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            final GestureDescription.StrokeDescription sd = new GestureDescription.StrokeDescription(path, 1, 1);
            boolean isGestureDispath = dispatchGesture(new GestureDescription.Builder().addStroke(sd).build(), new GestureResultCallback() {
                @Override
                public void onCompleted(GestureDescription gestureDescription) {
                    super.onCompleted(gestureDescription);
                    Log.d("yanghua", "滑动结束 = ");
                }

                @Override
                public void onCancelled(GestureDescription gestureDescription) {
                    super.onCancelled(gestureDescription);
                    Log.d("yanghua", "滑动取消 = ");
                }
            }, new Handler());
            Log.d("yanghua", "isGestureDispath = " + isGestureDispath);
        }

//横着滑动
//                Path path1 = new Path();
//
////先横滑
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    final GestureDescription.StrokeDescription sd1 = new GestureDescription.StrokeDescription(path1, 1, 1);
//                    boolean isGestureDispath = dispatchGesture(new GestureDescription.Builder().addStroke(sd1).build(), new GestureResultCallback() {
//                        @Override
//                        public void onCompleted(GestureDescription gestureDescription) {
//                            super.onCompleted(gestureDescription);
//                            Log.d("yanghua", "滑动结束 = ");
//                        }
//
//                        @Override
//                        public void onCancelled(GestureDescription gestureDescription) {
//                            super.onCancelled(gestureDescription);
//                            Log.d("yanghua", "滑动取消 = ");
//                        }
//                    }, new Handler());
//                    Log.d("yanghua", "isGestureDispath = " + isGestureDispath);
//                }

        mHandler.sendEmptyMessageDelayed(11, MainActivity.time * 1000);

//                isInMESSAGE =false;
    }


}
