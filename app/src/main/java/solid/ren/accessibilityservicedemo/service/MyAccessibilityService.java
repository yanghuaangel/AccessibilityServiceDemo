package solid.ren.accessibilityservicedemo.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.content.SharedPreferences;
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

import solid.ren.accessibilityservicedemo.DisplayUtil;
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
    private final String huaweitianqi = "com.huawei.android.totemweather";
    private final String huaweilauncher = "com.huawei.android.launcher";
    private final String intelligent = "com.huawei.intelligent";
//    private final String settings = "com.android.settings";
private final String inputhuawei =  "com.baidu.input_huawei";

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
    private final String kuaishou1= "com.smile.gifmaker";
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
    //音浪
    private final String yl = "com.video.yl";
    //趣宠
    private final String qc = "com.video.qc";
    //变身记
    private final String bsj = "com.video.bsj";
    //火火视频极速版
    private final String hhsp = "com.jt.hanhan.video";
    //抖音火山版
    private final String hsxsp = "com.ss.android.ugc.live";
    //波波视频
    private final String bbsp = "tv.yixia.bobo";
    //淘宝直播
    private final String tbzb = "com.taobao.live";
    //秒看视频
    private final String mksp = "com.taige.mygold";
    //热火视频极速版
    private final String rhsp = "com.jt.rhjs.video";
    //追看视频
    private final String zksp = "com.yy.yylite";
    //玩转来电
    private final String wzld = "com.earn.matrix_callervideo";


    //安装程序
    private final String install  = "com.miui.packageinstaller";

    //1加的问题
    private final String pulslauncher = "net.oneplus.launcher";
    //小米不滑动问题
    private final String xiao_securitycenter=  "com.miui.securitycenter";

    private final String dewu = "com.shizhuang.duapp";


    private int recentCount = 0;
    private Handler mHandler;

    private boolean isInMESSAGE = false;
    private long lastscrolltime;

//    private boolean isdebug = true;

    private final int img_close_flag = 23;
    private final int scroll_flag = 11;
    //监管消息
    private final int jianguanyuan = 1000;
    private final int click_x = 100;
    private final int click_gb = 101;
    private final int click_x_wzld = 102;
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
//                    listxk.get(0).performClick(AccessibilityNodeInfo.ACTION_CLICK);
                        removeMessage(click_x);
                        mHandler.sendEmptyMessageDelayed(click_x, 7_000);
                    }

                    if (txt.toString().contains("关闭广告")) {
                        Log.d("yanghua", "找到 关闭广告");
//                    listxk.get(0).performClick(AccessibilityNodeInfo.ACTION_CLICK);
                        removeMessage(click_x);
                        mHandler.sendEmptyMessageDelayed(click_gb, 1_000);
                    }

                }

                if (event.getSource() == null) {
                    return;
                }


                if (!isMoneyApp()) {
                   SharedPreferences sp =  getSharedPreferences("notApp",MODE_PRIVATE);
                   int num = sp.getInt("num",0);
                   SharedPreferences.Editor editor =  sp.edit();
                    editor.putString("appname"+num,PrintUtils.currentPn);
                    editor.putInt("num",num+1);
                    editor.apply();
                    if(num >=1000){
                        editor.clear();
                        editor.apply();
                    }
                    return;
                }

                findVideoEntrance(event);
                videoEnterWZLD(event);
                videoEnterWZLD2(event);
                findX_WZLD(event);



                final List<AccessibilityNodeInfo> listjz = event.getSource().findAccessibilityNodeInfosByText("禁止");
                if (listjz == null || listjz.size() == 0) {
//                    Log.d("yanghua", "没有找到广告 关闭广告");
                } else {
                        Log.d("yanghua", "找到了禁止");
                    performClick(listjz.get(0));
                }

//                final List<AccessibilityNodeInfo> listqx = event.getSource().findAccessibilityNodeInfosByText("取消");
//                if (listqx == null || listqx.size() == 0) {
////                    Log.d("yanghua", "没有找到广告 关闭广告");
//                } else {
//                    Log.d("yanghua", "找到了取消");
//                    listqx.get(0).performClick(AccessibilityNodeInfo.ACTION_CLICK);
//                }


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
                                performClick(listqls.get(0));
                                isAd = false;
                            }
                        }, 1000);

                }

                //彩蛋视频 不要翻倍
                final List<AccessibilityNodeInfo> listcd = event.getSource().findAccessibilityNodeInfosByViewId("com.jifen.dandan:id/close_bottom_button");
                if (listcd == null || listcd.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    Log.d("yanghua", "找到了取消翻倍");
                    isEnterAd = false;
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            performClick(listcd.get(0));
                            isAd = false;
                        }
                    }, 1000);

                }

//                final List<AccessibilityNodeInfo> listksX = event.getSource().findAccessibilityNodeInfosByViewId("com.kuaishou.nebula:id/photo_detail_panel_close");
//                if (listksX == null || listksX.size() == 0) {
////                    Log.d("yanghua", "没有找到广告X");
//                } else {
//                    Log.d("yanghua", "找到了快手X");
////                    isEnterAd = false;
////                    mHandler.postDelayed(new Runnable() {
////                        @Override
////                        public void run() {
//                            listksX.get(0).performClick(AccessibilityNodeInfo.ACTION_CLICK);
////                        }
////                    }, 1000);
//
//                }


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
                                performClick(list1.get(0));
                                isAd = false;
                            }
                        }, 1000);

                        removeMessage(click_x);
                    }

                }

                final List<AccessibilityNodeInfo> listyl = event.getSource().findAccessibilityNodeInfosByViewId("com.video.yl:id/tt_video_ad_close_layout");
                if (listyl == null || listyl.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    if (!Build.MODEL.contains("Redmi")) {
                        Log.d("yanghua", "找到了广告X");
                        isEnterAd = false;
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                performClick(listyl.get(0));
                                isAd = false;
                            }
                        }, 1000);

                        removeMessage(click_x);
                    }

                }

                final List<AccessibilityNodeInfo> listqc = event.getSource().findAccessibilityNodeInfosByViewId("com.video.qc:id/tt_video_ad_close_layout");
                if (listqc == null || listqc.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    if (!Build.MODEL.contains("Redmi")) {
                        Log.d("yanghua", "找到了广告X");
                        isEnterAd = false;
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                performClick(listqc.get(0));
                                isAd = false;
                            }
                        }, 1000);

                        removeMessage(click_x);
                    }

                }


                final List<AccessibilityNodeInfo> listbsj = event.getSource().findAccessibilityNodeInfosByViewId("com.video.bsj:id/tt_video_ad_close_layout");
                if (listbsj == null || listbsj.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    if (!Build.MODEL.contains("Redmi")) {
                        Log.d("yanghua", "找到了广告X");
                        isEnterAd = false;
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                performClick(listbsj.get(0));
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
                                performClick(listxch.get(0));
                            }
                        }
                    }, 3000);


                }

                final List<AccessibilityNodeInfo> listyl1 = event.getSource().findAccessibilityNodeInfosByViewId("com.video.yl:id/img_close");
                if (listyl1 == null || listyl1.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    Log.d("yanghua", "找到了广告 前X");
//                    mHandler.removeMessages(100);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!mHandler.hasMessages(click_x)) {
                                performClick(listyl1.get(0));
                            }
                        }
                    }, 3000);


                }

                final List<AccessibilityNodeInfo> listqc1 = event.getSource().findAccessibilityNodeInfosByViewId("com.video.qc:id/img_close");
                if (listqc1 == null || listqc1.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    Log.d("yanghua", "找到了广告 前X");
//                    mHandler.removeMessages(100);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!mHandler.hasMessages(click_x)) {
                               performClick(listqc1.get(0));
                            }
                        }
                    }, 3000);


                }

                final List<AccessibilityNodeInfo> listbsj1 = event.getSource().findAccessibilityNodeInfosByViewId("com.video.bsj:id/img_close");
                if (listbsj1 == null || listbsj1.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
                } else {
                    Log.d("yanghua", "找到了广告 前X");
//                    mHandler.removeMessages(100);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!mHandler.hasMessages(click_x)) {
                                performClick(listbsj1.get(0));
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
                                performClick(listyy.get(0));
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
                                performClick(listyyimg.get(0));
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
                                performClick(listkd.get(0));
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
                                performClick(listkdimg.get(0));
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
                                performClick(listgs.get(0));
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
                               performClick(listgsimg.get(0));
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
                               performClick(list2.get(0));
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void findX_WZLD(AccessibilityEvent event) {
        //趣铃声的
        final List<AccessibilityNodeInfo> listqls = event.getSource().findAccessibilityNodeInfosByViewId("com.earn.matrix_callervideo:id/tt_video_ad_close_layout");
        if (listqls == null || listqls.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
        } else {
            Log.d("yanghua", "找到了广告X");
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    performClick(listqls.get(0));
                    isAd = false;
                }
            }, 1000);
            removeMessage(click_x_wzld);

        }
    }

    //玩转来电进入广告
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void videoEnterWZLD(AccessibilityEvent event) {
        //玩转来电
        final List<AccessibilityNodeInfo> list = event.getSource().findAccessibilityNodeInfosByViewId("com.earn.matrix_callervideo:id/a7i");
        if (list == null || list.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
        } else {
            Log.d("yanghua", "进入广告按钮");
            isEnterAd = true;
            mHandler.sendEmptyMessageDelayed(click_x_wzld, 60_000);
            mHandler.removeMessages(scroll_flag);
            mHandler.sendEmptyMessageDelayed(scroll_flag, MainActivity.time * 1000);
            lastscrolltime = System.currentTimeMillis();

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isAd = true;
                    performClick(list.get(0));
                }
            }, 2000);

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //60秒之后自动变非广告
                    isAd = false;
                }
            }, 60_000);


        }
    }
    //玩转来电进入广告
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void videoEnterWZLD2(AccessibilityEvent event) {
        //玩转来电
        final List<AccessibilityNodeInfo> list = event.getSource().findAccessibilityNodeInfosByViewId("com.earn.matrix_callervideo:id/f1");
        if (list == null || list.size() == 0) {
//                    Log.d("yanghua", "没有找到广告X");
        } else {
            Log.d("yanghua", "进入广告按钮");
            isEnterAd = true;
            mHandler.sendEmptyMessageDelayed(click_x_wzld, 60_000);
            mHandler.removeMessages(scroll_flag);
            mHandler.sendEmptyMessageDelayed(scroll_flag, MainActivity.time * 1000);
            lastscrolltime = System.currentTimeMillis();

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isAd = true;
                    performClick(list.get(0));
                }
            }, 2000);

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //60秒之后自动变非广告
                    isAd = false;
                }
            }, 60_000);


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
                    performClick(list.get(0));
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
                    performClick(list22.get(0));
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


    public void cc() {

        //AccessibilityService.GLOBAL_ACTION_BACK
        //GLOBAL_ACTION_HOME
        //GLOBAL_ACTION_NOTIFICATIONS
        //GLOBAL_ACTION_RECENTS
        if (!mHandler.hasMessages(scroll_flag)) {

            Log.d("yanghua", "cc 多少秒滑动一次 = " + MainActivity.time);
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
                }else if(msg.what == click_x_wzld){
                    doClickWZLD();
                }else if(msg.what == jianguanyuan){
                    cc();
                    //监管员
                    mHandler.sendEmptyMessageDelayed(jianguanyuan,5*60*1000);
                }

            }
        };
        //监管员
        mHandler.sendEmptyMessageDelayed(jianguanyuan,5*60*1000);


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
            Log.d("yanghua", "isGestureDispath click = " + isGestureDispath);
        }

    }
    private void  doClickWZLD(){
        Path path = new Path();


        //这个要分机型判断了，
        //小米8se 967 69
        //红米6 644 75
//        if (Build.MODEL.contains("Redmi")) {
//            path.moveTo(644, 75);
//            Log.d("yanghua", "Redmi 点击 = ");
//        } else {
        if(Build.MODEL.contains("Mi 10")){
            path.moveTo(94, 165);
            Log.d("yanghua", "小米 10 点击 X = ");
        }else if (Build.MODEL.contains("Redmi 6")) {
            path.moveTo(80, 60);
            Log.d("yanghua", "红米6  点击 X = ");
        }
//        }

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
                || PrintUtils.currentPn.equals(kuaishou1)
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
                || PrintUtils.currentPn.equals(yl)
                || PrintUtils.currentPn.equals(qc)
                || PrintUtils.currentPn.equals(bsj)
                || PrintUtils.currentPn.equals(hhsp)
                || PrintUtils.currentPn.equals(hsxsp)
                || PrintUtils.currentPn.equals(bbsp)
                || PrintUtils.currentPn.equals(tbzb)
                || PrintUtils.currentPn.equals(mksp)
                || PrintUtils.currentPn.equals(rhsp)
                || PrintUtils.currentPn.equals(zksp)
                || PrintUtils.currentPn.equals(wzld)
                || PrintUtils.currentPn.equals(install)
                || PrintUtils.currentPn.equals(huaweitianqi)
                || PrintUtils.currentPn.equals(huaweilauncher)
                || PrintUtils.currentPn.equals(intelligent)
                || PrintUtils.currentPn.equals(inputhuawei)

                || PrintUtils.currentPn.equals(xiao_securitycenter)
                || PrintUtils.currentPn.equals(dewu)
                || PrintUtils.currentPn.equals(android);
//        return  true;
    }

    private void performClick(AccessibilityNodeInfo target){
        if(PrintUtils.currentPn.equals(kuaishou)){
            return;
        }
        target.performAction(AccessibilityNodeInfo.ACTION_CLICK);
    }

    private void doScroll() {
        if(!MainActivity.switchFlag){
            Log.d("yanghua", "switch 开关 关闭了 ");
            return;
        }
        lastscrolltime = System.currentTimeMillis();


        if (!isMoneyApp() || isAd == true) {
            mHandler.sendEmptyMessageDelayed(scroll_flag, MainActivity.time * 1000);
            return;
        }
        Log.d("yanghua", "多少秒滑动一次 = " + MainActivity.time);
        Path path = new Path();
        Log.d("yanghua", "path main radiogroup =  " + MainActivity.RadioGroupId);
        //竖着滑动 1000 600
        if(DisplayUtil.screenWith == 0){
            DisplayUtil.getScreenRelatedInformation(this);
        }
        int X = DisplayUtil.screenWith/2;
        int Y = DisplayUtil.screenHeight/2;
        if (MainActivity.RadioGroupId == 0) {
            Log.d("yanghua", "现在上滑动 y 初始= "+ Y);

//            path.moveTo(X, Y+DisplayUtil.screenHeight/3);
//            path.lineTo(X, DisplayUtil.screenHeight/5);

            path.moveTo(X, 1700);//这个需要适配
            path.lineTo(X, 300);
        } else {
            Log.d("yanghua", "现在左右滑动 = ");
            path.moveTo(800, 120);
            path.lineTo(200, 120);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            final GestureDescription.StrokeDescription sd = new GestureDescription.StrokeDescription(path, 200, 500);
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


        mHandler.sendEmptyMessageDelayed(scroll_flag, MainActivity.time * 1000);

    }


}
