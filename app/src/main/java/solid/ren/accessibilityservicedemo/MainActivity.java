package solid.ren.accessibilityservicedemo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;

    public static int time = 10;

    public static int RadioGroupId = 0;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("yanghua","机器型号 :" + Build.MODEL);
        findViewById(R.id.btn_open_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开系统设置中辅助功能
                Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(intent);
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
}
