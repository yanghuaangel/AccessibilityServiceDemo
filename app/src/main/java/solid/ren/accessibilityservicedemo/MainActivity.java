package solid.ren.accessibilityservicedemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

import solid.ren.accessibilityservicedemo.service.FloatingButtonService;

public class MainActivity extends AppCompatActivity {
    public static boolean switchFlag = true;
    private EditText editText;
    private TextView textView;

    public static int time = 10;

    public static int RadioGroupId = 0;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayUtil.getScreenRelatedInformation(this);

        Log.d("yanghua","机器型号 :" + Build.MODEL);
        Log.d("yanghua","屏幕宽 :" + DisplayUtil.screenWith +"\n"+"屏幕高 ：" + DisplayUtil.screenHeight);
        findViewById(R.id.btn_open_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开系统设置中辅助功能
                Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_open_window).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFloatingButtonService();
            }
        });

        findViewById(R.id.btn_close_window).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopFloatingButtonService();
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    time = Integer.parseInt(editText.getText().toString());
                    textView.setText("当前"+time+"秒"+"滑动一次");
                }catch (Exception e){

                }

            }
        });

        editText = (EditText) findViewById(R.id.edit_text);
        editText.setInputType( InputType.TYPE_CLASS_NUMBER);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("当前"+time+"秒"+"滑动一次");

        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_video:
                        RadioGroupId = 0;
                        break;
                    case R.id.radio_read:
                        RadioGroupId = 1;
                        break;
                }
            }
        });

    }

    public void startFloatingButtonService( ) {
        if (FloatingButtonService.isStarted) {
            FloatingButtonService.getInstance().openWindow();
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "当前无权限，请授权", Toast.LENGTH_SHORT);
                startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 0);
            } else {
                startService(new Intent(MainActivity.this, FloatingButtonService.class));
            }
        }
    }

    public void stopFloatingButtonService( ) {
        if (FloatingButtonService.isStarted) {
            FloatingButtonService.getInstance().closeWindow();
//            Intent intent = new Intent(this, FloatingButtonService.class);
//            stopService(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
                    startService(new Intent(MainActivity.this, FloatingButtonService.class));
                }
            }
        }
    }
}
