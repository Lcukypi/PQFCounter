package com.luckypi.pqfcounterfinal;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

import com.luckypi.pqfcounterfinal.forum.ForumActivity;
import com.luckypi.pqfcounterfinal.mine.MineActivity;
import com.luckypi.pqfcounterfinal.news.NewsActivity;

import cn.cumt.counter.step.activity.StepCounterActivity;

//import cn.cumt.counter.step.activity.StepCounterActivity;

//import static com.baidu.location.d.j.R;


//定义activity继承自TabActivity，并且实现了OnCheckedChangeListener接口
public class MainActivity extends TabActivity implements OnCheckedChangeListener {
    /** Called when the activity is first created. */
	//定义的tabhost对象
	private TabHost mHost;
	//定义RadioGroup对象
	private RadioGroup radioderGroup;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化TabHost
        mHost=this.getTabHost();
        
        //添加选项卡，并且设置跳转intent
        mHost.addTab(mHost.newTabSpec("ONE").setIndicator("ONE")
        			.setContent(new Intent(this,StepCounterActivity.class)));
        mHost.addTab(mHost.newTabSpec("TWO").setIndicator("TWO")
        		.setContent(new Intent(this,NewsActivity.class)));
        mHost.addTab(mHost.newTabSpec("THREE").setIndicator("THREE")
        		.setContent(new Intent("android.intent.action.MUSIC_PLAYER")));
        mHost.addTab(mHost.newTabSpec("FOUR").setIndicator("FOUR")
        		.setContent(new Intent(this,ForumActivity.class)));
        mHost.addTab(mHost.newTabSpec("FIVE").setIndicator("FIVE")
        		.setContent(new Intent(this,MineActivity.class)));
        //得到radioGroup对象
        radioderGroup = (RadioGroup) findViewById(R.id.main_radio);
        //设置radioGroup对象的切换监听器
		radioderGroup.setOnCheckedChangeListener(this);
    }
    
    //实现OnCheckedChangeListener中的RadioGroup的选项切换回调函数
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		//根据所选中的RadioGroup的选项id，设置tabhost的选项卡
		switch(checkedId){
		case R.id.radio_button0:
			mHost.setCurrentTabByTag("ONE");
			break;
		case R.id.radio_button1:
			mHost.setCurrentTabByTag("TWO");
			break;
		case R.id.radio_button2:
			mHost.setCurrentTabByTag("THREE");
			break;
		case R.id.radio_button3:
			mHost.setCurrentTabByTag("FOUR");
			break;
		case R.id.radio_button4:
			mHost.setCurrentTabByTag("FIVE");
			break;
		}		
	}
}