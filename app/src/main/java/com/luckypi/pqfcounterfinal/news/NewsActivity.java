package com.luckypi.pqfcounterfinal.news;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.luckypi.pqfcounterfinal.R;

import java.io.InputStream;
import java.util.List;
    /*
    created by dell in 2017-6-9
    新闻界面，显示各种运动新闻
     */

public class NewsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private LinearLayout loading;
    private ListView lvNews;
    private List<NewsInfo> newsInfos;
    private TextView tv_title;
    private TextView tv_description;
    private TextView tv_type;
    private NewsInfo newsInfo;
    private ImageView iv_pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        loading = (LinearLayout)findViewById(R.id.loading);
        lvNews = (ListView)findViewById(R.id.lv_news);

        try {
            InputStream is=this.getResources().openRawResource(R.raw.newsinfo);
            newsInfos = NewsService.getNewsInfo(is);
            lvNews.setAdapter(new NewsAdapter());
            lvNews.setOnItemClickListener(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //点击各种item显示具体新闻
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int arg2, long id) {
        arg2 = arg2+1;
        String showText = "点击第" + arg2 + "项";
        arg2 = arg2-1;
        Toast.makeText(this, showText, Toast.LENGTH_LONG).show();
        Intent intent= new  Intent(NewsActivity.this,NewsAnotherActivity.class);
        Bundle bundle = new Bundle();
        newsInfo = newsInfos.get(arg2);
        bundle.putString("message",newsInfo.getMessage());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private class NewsAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return newsInfos.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /*
        显示listview界面细节
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(NewsActivity.this,R.layout.news_item,null);
            iv_pic = (ImageView)view.findViewById(R.id.iv_pic);
            tv_title = (TextView)view.findViewById(R.id.tv_title);
            tv_description = (TextView)view.findViewById(R.id.tv_description);
            tv_type = (TextView)view.findViewById(R.id.tv_type);
            newsInfo = newsInfos.get(position);

            tv_title.setText(newsInfo.getTitle());
            tv_description.setText(newsInfo.getContent());
            int type = newsInfo.getType();
            switch (type){
                case 1:
                {tv_type.setText("评论:"+newsInfo.getComment());
                    iv_pic.setImageResource(R.drawable.pic1);}
                break;
                case 2: {
                    tv_type.setTextColor(Color.RED);
                    iv_pic.setImageResource(R.drawable.pic2);
                    tv_type.setText("专题");
                }
                break;
                case 3: {
                    tv_type.setTextColor(Color.BLUE);
                    iv_pic.setImageResource(R.drawable.pic3);
                    tv_type.setText("LIVE");
                }
                break;
            }
            return view;
        }
    }

}

