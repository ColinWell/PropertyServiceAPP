package com.antonioleiva.mvpexample.app.main.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.antonioleiva.mvpexample.app.Complaint.JumpCActivity;
import com.antonioleiva.mvpexample.app.Decoration.JumpDActivity;
import com.antonioleiva.mvpexample.app.Repair.JumpRActivity;
import com.antonioleiva.mvpexample.app.Service.PaymentService;
import com.antonioleiva.mvpexample.app.bean.ListMenuItem;
import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.context.MyApplication;
import com.antonioleiva.mvpexample.app.login.LoginActivity;
import com.antonioleiva.mvpexample.app.personalInfo.PersonalInfoActivity;
import com.antonioleiva.mvpexample.app.util.MyRetrofit;
import com.antonioleiva.mvpexample.app.util.OnMenuListItemClick;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ExtendFragment extends Fragment {
    private List<ListMenuItem> extendMenu = new ArrayList<>();
    private OnMenuListItemClick onMenuListItemClick;
    private View contentView; // 缓存view，避免因切换重复加载

    public ExtendFragment() {
        super();
    }

    // 使用静态工厂创建实例
    public static ExtendFragment newInstance(){
        ExtendFragment extendFragment = new ExtendFragment();
        return extendFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(contentView == null) {
            initMenu();
            contentView = inflater.inflate(R.layout.fragment_extend, null);
            final ListView listView = (ListView) contentView.findViewById(R.id.extend_menu);
            ListViewAdapter adapter = new ListViewAdapter(extendMenu,getContext());
            listView.setAdapter(adapter);
            // Inflate the layout for this fragment
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int row,
                                        long arg3) {
                    //4、如果接口成员变量不为空null，则调用接口变量的方法。
                    if(onMenuListItemClick!=null){
                        onMenuListItemClick.onClick(listView,row);
                    }
                    switch (row){
                        case 0:
                            Intent intent0=new Intent(getActivity(), JumpRActivity.class);
                            intent0.putExtra("id",0);
                            startActivity(intent0);
                            break;
                        case 1:
                            Intent intent1=new Intent(getActivity(), JumpDActivity.class);
                            startActivity(intent1);
                            break;
                        case 2:
                            Intent intent2=new Intent(getActivity(), JumpCActivity.class);
                            intent2.putExtra("id",2);
                            startActivity(intent2);
                            break;
                        case 3:
                            Intent intent = new Intent(getActivity(), PersonalInfoActivity.class);
                            intent.putExtra("id",3);
                            startActivityForResult(intent,3);
                            break;
                        case 4:
                            logout();
                            break;
                    }
                    System.out.println("点击了第"+row+"行");
                }
            });
        }else{
            ViewGroup parent = (ViewGroup) contentView.getParent();
            if(parent != null){
                parent.removeView(contentView);
            }
        }

        return contentView;
    }

    private void logout() {
        Retrofit retrofit = MyRetrofit.getInstance();
        // 创建 网络请求接口 的实例
        PaymentService request = retrofit.create(PaymentService.class);
        Call<ResponseBody> call = request.logout();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        if(jsonObject.getString("logoutResult").equals("true")) {
                            SharedPreferences config = getActivity().getSharedPreferences("config",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = config.edit();
                            editor.putString("userName",null);
                            editor.putString("password",null);
                            editor.apply();
                            Intent intent = new Intent(getActivity(),LoginActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                        else{
                            Toast.makeText(getActivity(),"退出登录失败",Toast.LENGTH_SHORT).show();
                        }
                    }catch (JSONException e){
                        System.out.println(e.getMessage());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                } catch (IOException ex){
                    System.out.println(ex.getMessage());
                }
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("调用失败:"+t.toString());
            }
        });
    }


    public void initMenu(){
        extendMenu.add(new ListMenuItem("在线报修","发现故障，在线及时完成报修"));
        extendMenu.add(new ListMenuItem("装修申请","提交装修申请，无纸申请更轻松"));
        extendMenu.add(new ListMenuItem("投诉建议","对小区建设和服务有什么建议，欢迎提出"));
        extendMenu.add(new ListMenuItem("个人资料","完善个人信息，以便提供更好的服务"));
        extendMenu.add(new ListMenuItem("注销账号","退出当前账号，重新登录"));

    }

    public OnMenuListItemClick getOnMenuListItemClick() {
        return onMenuListItemClick;
    }

    public void setOnMenuListItemClick(OnMenuListItemClick onMenuListItemClick) {
        this.onMenuListItemClick = onMenuListItemClick;
    }
}
