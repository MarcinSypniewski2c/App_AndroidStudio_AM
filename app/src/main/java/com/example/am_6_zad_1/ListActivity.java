package com.example.am_6_zad_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.MutableBoolean;
import android.view.View;
import android.widget.Switch;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.series.DataPoint;

import org.json.JSONObject;

import java.util.ArrayList;

class WrapInt{
    int value;

    public WrapInt(int ival) {
        value = ival;
    }
}

public class ListActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RecyclerView.Adapter adap;
    private RecyclerView.LayoutManager lm;
    private ArrayList<ExampleItem> measurelist;

    private CountDownTimer ct_list;
    private RequestQueue queue_list;

    private int item_pos;

    private Switch sw_temp;
    private Switch sw_pres;
    private Switch sw_hum;
    private Switch sw_roll;
    private Switch sw_pitch;
    private Switch sw_yaw;
    private Switch sw_jx;
    private Switch sw_jy;
    private Switch sw_pc;

    private WrapInt t_flag=new WrapInt(0);
    private WrapInt p_flag=new WrapInt(0);
    private WrapInt h_flag=new WrapInt(0);
    private WrapInt rl_flag=new WrapInt(0);
    private WrapInt pt_flag=new WrapInt(0);
    private WrapInt yw_flag=new WrapInt(0);
    private WrapInt jx_flag=new WrapInt(0);
    private WrapInt jy_flag=new WrapInt(0);
    private WrapInt pc_flag=new WrapInt(0);

    private int temp_pos;
    private int pres_pos;
    private int hum_pos;
    private int roll_pos;
    private int pitch_pos;
    private int yaw_pos;
    private int jx_pos;
    private int jy_pos;
    private int pc_pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        queue_list = Volley.newRequestQueue( ListActivity.this);

        measurelist = new ArrayList<>();


        rv = findViewById(R.id.recv);
        rv.setHasFixedSize(true);
        lm = new LinearLayoutManager(this);
        adap = new ExampleAdapter(measurelist);

        rv.setLayoutManager(lm);
        rv.setAdapter(adap);

        sw_temp = findViewById(R.id.switch_temp);
        sw_pres = findViewById(R.id.switch_pres);
        sw_hum = findViewById(R.id.switch_hum);
        sw_roll = findViewById(R.id.switch_roll);
        sw_pitch = findViewById(R.id.switch_pitch);
        sw_yaw = findViewById(R.id.switch_yaw);
        sw_jx = findViewById(R.id.switch_jx);
        sw_jy = findViewById(R.id.switch_jy);
        sw_pc = findViewById(R.id.switch_pc);
    }

    public void change_list_items(View view){

        item_pos = 0;

        list_item_set(temp_pos,"Temperature: ","0","C",sw_temp,t_flag,temp_pos);
        list_item_set(pres_pos,"Pressure: ","0","hPa",sw_pres,p_flag,pres_pos);
        list_item_set(hum_pos,"Humidity: ","0","%",sw_hum,h_flag,hum_pos);
        list_item_set(roll_pos,"Roll: ","0","deg",sw_roll,rl_flag,roll_pos);
        list_item_set(pitch_pos,"Pitch: ","0","deg",sw_pitch,pt_flag,pitch_pos);
        list_item_set(yaw_pos,"Yaw: ","0","deg",sw_yaw,yw_flag,yaw_pos);
        list_item_set(jx_pos,"Joystick X: ","0","",sw_jx,jx_flag,jx_pos);
        list_item_set(jy_pos,"Joystick Y: ","0","",sw_jy,jy_flag,jy_pos);
        list_item_set(pc_pos,"Press Count: ","0","",sw_pc,pc_flag,pc_pos);


    }

    public void timer_met_list(){
        String URL = "http://192.168.0.11/list_test.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        for(int k=0;k<measurelist.size();k++) {
                            ExampleItem temporary_item = measurelist.get(k);
                            if (sw_temp.isChecked() && t_flag.value == 1 && temporary_item.get_name() == "Temperature: ") {
                                String tps = response.optString("temperature");
                                temporary_item.set_value(tps);
                                adap.notifyDataSetChanged();
                            }
                            if (sw_pres.isChecked() && p_flag.value == 1 && temporary_item.get_name() == "Pressure: ") {
                                String pps = response.optString("pressure");
                                temporary_item.set_value(pps);
                                adap.notifyDataSetChanged();
                            }
                            if (sw_hum.isChecked() && h_flag.value == 1 && temporary_item.get_name() == "Humidity: ") {
                                String pps = response.optString("humidity");
                                temporary_item.set_value(pps);
                                adap.notifyDataSetChanged();
                            }
                            if (sw_roll.isChecked() && rl_flag.value == 1 && temporary_item.get_name() == "Roll: ") {
                                String pps = response.optString("roll");
                                temporary_item.set_value(pps);
                                adap.notifyDataSetChanged();
                            }
                            if (sw_pitch.isChecked() && pt_flag.value == 1 && temporary_item.get_name() == "Pitch: ") {
                                String pps = response.optString("pitch");
                                temporary_item.set_value(pps);
                                adap.notifyDataSetChanged();
                            }
                            if (sw_yaw.isChecked() && yw_flag.value == 1 && temporary_item.get_name() == "Yaw: ") {
                                String pps = response.optString("yaw");
                                temporary_item.set_value(pps);
                                adap.notifyDataSetChanged();
                            }
                            if (sw_jx.isChecked() && jx_flag.value == 1 && temporary_item.get_name() == "Joystick X: ") {
                                String pps = response.optString("joystick_x");
                                temporary_item.set_value(pps);
                                adap.notifyDataSetChanged();
                            }
                            if (sw_jy.isChecked() && jy_flag.value == 1 && temporary_item.get_name() == "Joystick Y: ") {
                                String pps = response.optString("joystick_y");
                                temporary_item.set_value(pps);
                                adap.notifyDataSetChanged();
                            }
                            if (sw_pc.isChecked() && pc_flag.value == 1 && temporary_item.get_name() == "Press Count: ") {
                                String pps = response.optString("press");
                                temporary_item.set_value(pps);
                                adap.notifyDataSetChanged();
                            }
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        queue_list.add(jsonObjectRequest);

        ct_list.start();
    }

    public void ctl_start(View view){
        ct_list = new CountDownTimer(5000,20) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                timer_met_list();
            }
        }.start();
    }

    public void ctl_stop(View view){
        ct_list.cancel();
    }


    public void list_back(View view){
        finish();
    }

    public void insert_item(int position, String new_name, String new_value, String new_unit){
        measurelist.add(position, new ExampleItem(new_name, new_value, new_unit));
        adap.notifyDataSetChanged();
    }
    public void remove_item(int position){
        measurelist.remove(position);
        adap.notifyDataSetChanged();
    }

    public void list_item_set(int position, String new_name, String new_value, String new_unit, Switch sw_item, WrapInt item_flag, int tem_item_pos){
        if(sw_item.isChecked() && item_flag.value == 0){
            tem_item_pos = item_pos;
            insert_item(tem_item_pos,new_name, new_value, new_unit);
            item_flag.value = 1;
            item_pos++;
        }
        else if(!sw_item.isChecked() && item_flag.value == 1){
            remove_item(tem_item_pos);
            item_flag.value = 0;
        }
    }
}