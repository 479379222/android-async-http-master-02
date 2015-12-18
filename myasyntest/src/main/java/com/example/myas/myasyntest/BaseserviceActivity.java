package com.example.myas.myasyntest;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.myas.myasyntest.base.CommonUtils;
import com.example.myas.myasyntest.base.WebServiceClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseserviceActivity extends AppCompatActivity {

    private ListView lstView;
    private Context context;
    private SimpleAdapter adapter = null;
    private List<Map<String, Object>> listitems = null;
    private String[] PageParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseservice);
        initView();
        PageParams = new String[2];
        PageParams[0] = "";
        PageParams[1] = "";
        if(CommonUtils.isNetworkAvailable(this))
            new getCourseTask().execute(PageParams);
    }

    private void initView(){
        context=BaseserviceActivity.this;
        lstView=(ListView)findViewById(R.id.lstView);
    }

    private class getCourseTask extends AsyncTask<String,Void,List<Map<String,Object>>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Map<String, Object>> doInBackground(String... params) {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            final String serviceurlString = "http://service.cheer-edu.net/wyservice/wyservice.asmx";

            String ymd = CommonUtils.getDay();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("Code", CommonUtils.getMd5Value(ymd));
            map.put("RegionID", params[0]);
            map.put("CourseTypeID", params[1]);
            map.put("SchoolID", "");
            map.put("CourseName", "");
            map.put("Startindex", 0);
            map.put("PageSize", "20");
            WebServiceClient serviceClient = new WebServiceClient() {
                @Override
                protected String getServiceUrl() {

                    return serviceurlString;
                }
            };

            SoapObject soapObject = serviceClient.getData("GetRegionCourse",
                    map);

            if (soapObject != null) {
                Map<String, Object> flag = null;

                try {

                    JSONObject temp = null;
                    JSONArray jsonArray = new JSONArray(soapObject.getProperty(
                            0).toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        temp = new JSONObject(jsonArray.get(i).toString());
                        flag = new HashMap<String, Object>();
                        // flag.put("Pic", R.drawable.user_blue);

                        flag.put("CourseID", temp.get("CourseID").toString());
                        flag.put("SNames", temp.get("SNames").toString());
                        flag.put("CourseName", temp.get("CourseName"));
                        flag.put("CourseDate", temp.get("CourseDate"));
                        flag.put("CourseCount", temp.get("CourseCount"));
                        flag.put("Score", temp.get("Score"));
                        flag.put("Content", temp.get("Describe"));
                        list.add(flag);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return list;
            } else {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Map<String, Object>> maps) {
            super.onPostExecute(maps);
            adapter = new SimpleAdapter(context, listitems,
                    R.layout.listitem_course, new String[] { "CourseName",
                    "SNames", "CourseDate","CourseCount","Score" }, new int[] {
                    R.id.txtCourseName, R.id.txtSNames,
                    R.id.txtCourseDate,R.id.txtCourseCount,R.id.txtScore});

            adapter.setViewBinder(new SimpleAdapter.ViewBinder() {

                public boolean setViewValue(View view, Object data,
                                            String textRepresentation) {
                    if (view instanceof ImageView && data instanceof Bitmap) {
                        ImageView iv = (ImageView) view;
                        iv.setImageBitmap((Bitmap) data);
                        return true;
                    } else
                        return false;
                }
            });
            lstView.setAdapter(adapter);
        }
    }

}
