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
    //趣铃声
    private final String qls = "com.zheyun.bumblebee";
    //安装程序
    private final String install  = "com.miui.packageinstaller";

    //1加的问题
    private final String pulslauncher = "net.oneplus.launcher";


    private int recentCount = 0;
    private Handler mHandler;

    private boolean isInMESSAGE = false;
    private long lastscrolltime;

//    private boolean isdebug = true;

    private final int img_close_flag = 23;
    private final int scroll_flag = 11;
    private final int click_x = 100;
    private final int click_gb = 101;
    private boolean isEnterAd = false;
    private boolean isAd = false; //是否进入广告


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        PrintUtils.printEvent(event);
//        performGlobalAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            if(isdebug){
//                mHandler.sendEmptyMessageDelayed(100,30_000);
//            }
            try {
                cc();




                for (CharSequence txt : event.getText()) {
                    Log.d("yanghua", txt.toString());
                    if (txt.toString().contains("看视频辛苦")) {
                        Log.d("yanghua", "找到 看视频辛苦");
//                    listxk.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        removeMessage(click_x);
                        mHandler.sendEmptyMessageDelayed(click_x, 7_000);
                    }

                    if (txt.toString().contains("关闭广告")) {
                        Log.d("yanghua", "找到 关闭广告");
//                    listxk.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        removeMessage(click_x);
                        mHandler.sendEmptyMessageDelayed(click_gb, 1_000);
                    }

                }

                if (event.getSource() == null) {
                    return;
                }


                if (!isMoneyApp()) {
                    return;
                }

                findVideoEntrance(event);

                final List<AccessibilityNodeInfo> listjz = event.getSource().findAccessibilityNodeInfosByText("禁止");
                if (listjz == null || listjz.size() == 0) {
//                    Log.d("yanghua", "没有找到广告 关闭广告");
                } else {
                        Log.d("yanghua", "找到了禁止");
                    listjz.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }

                final List<AccessibilityNodeInfo> listqx = event.getSource().findAccessibilityNodeInfosByText("取消");
                if (listqx == null || listqx.size() == 0) {
//                    Log.d("yanghua", "没有找到广告 关闭广告");
                } else {
                    Log.d("yanghua", "找到了取消");
                    listqx.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }


                //趣铃声的
                final List<AccessibilityNodeInfo> listqls = event.getSource().findAccessibilityNodeInfosByViewId("com.zheyun.bumblebee:id/iv_close");
                if (listqls == null || listqls.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                        Log.d("yanghua", "找到了广告X");
                        isEnterAd = false;
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                listqls.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                isAd = false;
                            }
                        }, 1000);

                }


                final List<AccessibilityNodeInfo> list1 = event.getSource().findAccessibilityNodeInfosByViewId("com.video.xch:id/tt_video_ad_close_layout");
                if (list1 == null || list1.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    if (!Build.MODEL.contains("Redmi")) {
                        Log.d("yanghua", "找到了广告X");
                        isEnterAd = false;
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                list1.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                isAd = false;
                            }
                        }, 1000);

                        removeMessage(click_x);
                    }

                }

                final List<AccessibilityNodeInfo> listxch = event.getSource().findAccessibilityNodeInfosByViewId("com.video.xch:id/img_close");
                if (listxch == null || listxch.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    Log.d("yanghua", "找到了广告 前X");
//                    mHandler.removeMessages(100);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!mHandler.hasMessages(click_x)) {
                                listxch.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                        }
                    }, 3000);


                }


                final List<AccessibilityNodeInfo> listyy = event.getSource().findAccessibilityNodeInfosByViewId("com.video.yy:id/tt_video_ad_close_layout");
                if (listyy == null || listyy.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    if (!Build.MODEL.contains("Redmi")) {
                        Log.d("yanghua", "找到了广告X");
                        isEnterAd = false;
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                listyy.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                isAd = false;
                            }
                        }, 1000);
                        removeMessage(click_x);
                    }
                }

                final List<AccessibilityNodeInfo> listyyimg = event.getSource().findAccessibilityNodeInfosByViewId("com.video.yy:id/img_close");
                if (listyyimg == null || listyyimg.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    Log.d("yanghua", "找到了广告 前X");
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!mHandler.hasMessages(click_x)) {
                                listyyimg.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                        }
                    }, 3000);
                }

                final List<AccessibilityNodeInfo> listkd = event.getSource().findAccessibilityNodeInfosByViewId("com.video.kd:id/tt_video_ad_close_layout");
                if (listkd == null || listkd.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    if (!Build.MODEL.contains("Redmi")) {
                        Log.d("yanghua", "找到了广告X");
                        isEnterAd = false;
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                listkd.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                isAd = false;
                            }
                        }, 1000);
                        removeMessage(click_x);
                    }
                }

                final List<AccessibilityNodeInfo> listkdimg = event.getSource().findAccessibilityNodeInfosByViewId("com.video.kd:id/img_close");
                if (listkdimg == null || listkdimg.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    Log.d("yanghua", "找到了广告 前X");
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!mHandler.hasMessages(click_x)) {
                                listkdimg.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                        }
                    }, 3000);
                }

                final List<AccessibilityNodeInfo> listgs = event.getSource().findAccessibilityNodeInfosByViewId("com.video.gs:id/tt_video_ad_close_layout");
                if (listgs == null || listgs.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    if (!Build.MODEL.contains("Redmi")) {
                        Log.d("yanghua", "找到了广告X");
                        isEnterAd = false;
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                listgs.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                isAd = false;
                            }
                        }, 1000);
                        removeMessage(click_x);
                    }
                }

                final List<AccessibilityNodeInfo> listgsimg = event.getSource().findAccessibilityNodeInfosByViewId("com.video.gs:id/img_close");
                if (listgsimg == null || listgsimg.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    Log.d("yanghua", "找到了广告 前X");
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!mHandler.hasMessages(click_x)) {
                                listgsimg.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                        }
                    }, 3000);
                }

                final List<AccessibilityNodeInfo> list2 = event.getSource().findAccessibilityNodeInfosByText("关闭广告");
                if (list2 == null || list2.size() == 0) {
//                    Log.d("yanghua", "没有找到广告 关闭广告");
                } else {
                    if (!Build.MODEL.contains("Redmi")) {
                        Log.d("yanghua", "找到了广告 关闭广告");
                        isEnterAd = false;
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                list2.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                isAd = false;
                            }
                        }, 1000);
                        removeMessage(click_x);
                    }

                }

                //cpu handler 有一定的延时问题，做了一个补偿
                if (System.currentTimeMillis() > lastscrolltime + MainActivity.time * 1000) {
                    doScroll();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void findVideoEntrance(AccessibilityEvent event) {
        final List<AccessibilityNodeInfo> list = event.getSource().findAccessibilityNodeInfosByText("看视频奖励最高翻");
        if (list == null || list.size() == 0) {
//                    Log.d("yanghua", "没有找到视频奖励按钮");
        } else {
            Log.d("yanghua", "找到了视频奖励按钮");
            isEnterAd = true;
            mHandler.sendEmptyMessageDelayed(click_x, 40_000);

            mHandler.removeMessages(scroll_flag);
            mHandler.sendEmptyMessageDelayed(scroll_flag, MainActivity.time * 1000);
            lastscrolltime = System.currentTimeMillis();

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isAd = true;
                    list.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            }, 3000);

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //60秒之后自动变非广告
                    isAd = false;
                }
            }, 60_000);

        }

        final List<AccessibilityNodeInfo> list22 = event.getSource().findAccessibilityNodeInfosByText("看一段视频即可");
        if (list22 == null || list22.size() == 0) {
//                    Log.d("yanghua", "没有找到视频奖励按钮");
        } else {
            Log.d("yanghua", "找到了视频奖励按钮");
            isEnterAd = true;
            mHandler.sendEmptyMessageDelayed(click_x, 40_000);

            mHandler.removeMessages(scroll_flag);
            mHandler.sendEmptyMessageDelayed(scroll_flag, MainActivity.time * 1000);
            lastscrolltime = System.currentTimeMillis();

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isAd = true;
                    list22.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            }, 3000);

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //60秒之后自动变非广告
                    isAd = false;
                }
            }, 60_000);
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
        if (!mHandler.hasMessages(scroll_flag)) {

            Log.d("yanghua", "多少秒滑动一次 = " + MainActivity.time);
            mHandler.sendEmptyMessageDelayed(scroll_flag, MainActivity.time * 1000);
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
                if (msg.what == scroll_flag) {
                    if (System.currentTimeMillis() > lastscrolltime + MainActivity.time * 1000) {
                        doScroll();
                    }
                } else if (msg.what == click_x) {
//                    if (Build.MODEL.contains("Redmi") || Build.MODEL.contains("ONEPLUS")) {
                    if (Build.MODEL.contains("Redmi")) {
                        doClick();
                        isAd = false;
                    }

                } else if (msg.what == click_gb) {
                    if (Build.MODEL.contains("Redmi")) {
                        doClick2();
                        isAd = false;
                    }
                }

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

    private void doClick2() {
        Path path = new Path();

        //这个要分机型判断了，
        //小米8se 967 69

        //红米6 644 75  关闭广告的位置不同 635 169
        if (Build.MODEL.contains("Redmi")) {
            path.moveTo(635, 169);
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
                || PrintUtils.currentPn.equals(pulslauncher)
                || PrintUtils.currentPn.equals(qls)
                || PrintUtils.currentPn.equals(install)
                || PrintUtils.currentPn.equals(android);
    }

    private void doScroll() {
        lastscrolltime = System.currentTimeMillis();


        if (!isMoneyApp() || isAd == true) {
            mHandler.sendEmptyMessageDelayed(scroll_flag, MainActivity.time * 1000);
            return;
        }
        Log.d("yanghua", "多少秒滑动一次 = " + MainActivity.time);
        Path path = new Path();
        Log.d("yanghua", "path main radiogroup =  " + MainActivity.RadioGroupId);
        //竖着滑动 1000 600
        if (MainActivity.RadioGroupId == 0) {
            Log.d("yanghua", "现在上滑动 = ");
            path.moveTo(300, 1000);
            path.lineTo(300, 700);
        } else {
            Log.d("yanghua", "现在左右滑动 = ");
            path.moveTo(800, 120);
            path.lineTo(200, 120);
        }

//先横滑
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            final GestureDescription.StrokeDescription sd = new GestureDescription.StrokeDescription(path, 1, 20);
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

        mHandler.sendEmptyMessageDelayed(scroll_flag, MainActivity.time * 1000);

//                isInMESSAGE =false;
    }


}
