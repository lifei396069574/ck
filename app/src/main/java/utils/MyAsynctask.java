package utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.myandroid.R;
import com.google.gson.Gson;

import java.util.List;

import adapter.MyAdapter;
import adapter.ViewHolder;
import bean.JsonBean;

/**
 * 作者：李飞 on 2017/4/7 20:35
 * 类的用途：
 */

public class MyAsynctask extends AsyncTask {

    private final Context context;
    private final String url;
    private final ListView lv;

    public MyAsynctask(Context context , String url, ListView lv) {
        this.context=context;
        this.url=url;
        this.lv=lv;
    }

    @Override
    protected Object doInBackground(Object[] params) {

        String json = new MyHttp(url).getHttpContunt();
        Log.i("zzz",json+"");
        return json;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        Gson gson = new Gson();
        JsonBean jsonBean = gson.fromJson((String) o, JsonBean.class);
        final List<JsonBean.ListBean> list = jsonBean.getList();

        lv.setAdapter(new MyAdapter<JsonBean.ListBean>(context,list, R.layout.list_item) {
            @Override
            public void convert(ViewHolder helper, JsonBean.ListBean item) {
                    helper.setText(R.id.text_title,item.getSite_name());
                    helper.setText(R.id.text_button,item.getAddress());
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                list.remove(position);

                lv.setAdapter(new MyAdapter<JsonBean.ListBean>(context,list, R.layout.list_item) {
                    @Override
                    public void convert(ViewHolder helper, JsonBean.ListBean item) {
                        helper.setText(R.id.text_title,item.getSite_name());
                        helper.setText(R.id.text_button,item.getAddress());
                    }
                });
                // 自己消费
                // 避免 与 点击事件同时出现
                return true;
            }
        });

      lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Toast.makeText(context,"ID "+list.get(position).getId(),Toast.LENGTH_SHORT).show();
          }
      });

    }


}
