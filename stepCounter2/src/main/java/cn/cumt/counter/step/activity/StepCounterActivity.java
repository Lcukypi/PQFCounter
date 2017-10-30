package cn.cumt.counter.step.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cn.cumt.counter.step.R;
import cn.cumt.counter.step.step.config.Constant;
import cn.cumt.counter.step.step.service.StepService;
import cn.cumt.counter.step.step.utils.SharedPreferencesUtils;
import cn.cumt.counter.step.step.utils.StepCountModeDispatcher;
import cn.cumt.counter.step.view.StepArcView;

public class StepCounterActivity extends AppCompatActivity implements Handler.Callback , View.OnClickListener{
    private TextView tv_data;
    private StepArcView cc;
    private TextView tv_set;
    private TextView tv_isSupport;
    private Handler delayHandler;
    private SharedPreferencesUtils sp;
    private TextView tv_news;
    private TextView tv_music;

    private void assignViews() {
        tv_data = (TextView) findViewById(R.id.tv_data);
        cc = (StepArcView) findViewById(R.id.cc);
        tv_set = (TextView) findViewById(R.id.tv_set);
        tv_isSupport = (TextView) findViewById(R.id.tv_isSupport);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);
        assignViews();
        initData();
        addListener();
    }
    //检测手机是否支持计步功能
    private void initData() {
        sp = new SharedPreferencesUtils(this);
        String planWalk_QTY = (String) sp.getParam("planWalk_QTY", "7000");
        //cc.setCurrentCount(Integer.parseInt(planWalk_QTY), 1000);
        if (StepCountModeDispatcher.isSupportStepCountSensor(this)) {
            tv_isSupport.setText("计步中...");
            delayHandler = new Handler(this);
            setupService();
        } else {
            tv_isSupport.setText("该设备不支持计步");
        }
    }


    private void addListener() {
        tv_set.setOnClickListener(this);
        tv_data.setOnClickListener(this);
    }


    private boolean isBind = false;
    private Messenger mGetReplyMessenger = new Messenger(new Handler(this));
    private Messenger messenger;

    /**
     * 从service服务中拿到步数
     */
    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case Constant.MSG_FROM_SERVER:
                String planWalk_QTY = (String) sp.getParam("planWalk_QTY", "7000");
                cc.setCurrentCount(Integer.parseInt(planWalk_QTY), msg.getData().getInt("step"));
                break;
        }
        return false;
    }

    /**
     * 开启计步服务
     */
    private void setupService() {
        Intent intent = new Intent(this, StepService.class);
        isBind = bindService(intent, conn, Context.BIND_AUTO_CREATE);
        startService(intent);


    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                messenger = new Messenger(service);
                Message msg = Message.obtain(null, Constant.MSG_FROM_CLIENT);
                msg.replyTo = mGetReplyMessenger;
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        /**
         * 当与Service之间的连接丢失的时候会调用该方法，
         * 这种情况经常发生在Service所在的进程崩溃或者被Kill的时候调用，
         * 此方法不会移除与Service的连接，当服务重新启动的时候仍然会调用 onServiceConnected()。
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBind) {
            this.unbindService(conn);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_set) {
            startActivity(new Intent(this, SetPlanActivity.class));

        } else if (i == R.id.tv_data) {
            startActivity(new Intent(this, HistoryActivity.class));

        }
    }


//    /*
//    点击自定义控件显示地图
//     */
//    public void onClickMap(View view){
//        Intent intent = new Intent(StepCounterActivity.this, PathRecordActivity.class);
//        startActivity(intent);
//    }

}
