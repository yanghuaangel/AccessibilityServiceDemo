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


    private Handler mHandler;

    private boolean isInMESSAGE = false;


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        PrintUtils.printEvent(event);
//        performGlobalAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            cc();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void cc() {

        //AccessibilityService.GLOBAL_ACTION_BACK
        //GLOBAL_ACTION_HOME
        //GLOBAL_ACTION_NOTIFICATIONS
        //GLOBAL_ACTION_RECENTS
        if (isInMESSAGE == false) {

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
//                performGlobalAction(AccessibilityService.GESTURE_SWIPE_LEFT);
                if (
                        !(
                                PrintUtils.currentPn.equals(qutoutiao)
                                        || PrintUtils.currentPn.equals(shuabao)
                                        || PrintUtils.currentPn.equals(huoshan) || PrintUtils.currentPn.equals(shandian)
                                        || PrintUtils.currentPn.equals(tishi)
                                        || PrintUtils.currentPn.equals(kuaikandian)
                                        || PrintUtils.currentPn.equals(douyin)
                                        || PrintUtils.currentPn.equals(midu)
                                        || PrintUtils.currentPn.equals(fengdu)
                                        || PrintUtils.currentPn.equals(kuaishou)
                        )
                ) {
                    mHandler.sendEmptyMessageDelayed(11, MainActivity.time * 1000);
                    return;
                }

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
        };


//        //可用代码配置当前Service的信息

//        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
//        info.packageNames = new String[]{"com.android.packageinstaller", "com.tencent.mobileqq", "com.trs.gygdapp"}; //监听过滤的包名
//        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK; //监听哪些行为
//        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN; //反馈
//        info.notificationTimeout = 100; //通知的时间
//        setServiceInfo(info);
    }


}
