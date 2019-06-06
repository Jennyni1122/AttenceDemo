package com.jennyni.attencedemo.activity.attence;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.activity.course.AddCourseActivity;
import com.jennyni.attencedemo.adapter.RecordAdaper;
import com.jennyni.attencedemo.adapter.apater.IAdapter;
import com.jennyni.attencedemo.dao.CourseDAO;
import com.jennyni.attencedemo.dao.RecordDAO;
import com.jennyni.attencedemo.dao.StasDAO;
import com.jennyni.attencedemo.dao.StudentDAO;
import com.jennyni.attencedemo.db.Tb_course;
import com.jennyni.attencedemo.db.Tb_record;
import com.jennyni.attencedemo.db.Tb_stas;
import com.jennyni.attencedemo.db.Tb_student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DianMingActivity extends AppCompatActivity implements IAdapter.ChildViewClickListener {
    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;

    private ListView lv_student;

    private BluetoothAdapter mBluetoothAdapter;
    private Spinner sp_coursetype;
    private String courseCode;
    private CourseDAO courseDAO;
    private Handler handler = new Handler();
    private List<Tb_student> currentCourseStudent;
    private List<String> macAddress = new ArrayList<>();
    private IAdapter<Tb_student> adapter;
    private BluetoothReciver bluetoothReciver;
    private int times = 1;
    private List<Tb_record> uncheckList = new ArrayList<>();
    private RecordDAO recordDAO;
    private StasDAO stasDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_ming);
        initVIew();
        registerBluetoothReciver();
        courseDAO = new CourseDAO(getContentResolver());
        recordDAO = new RecordDAO(getContentResolver());
        stasDAO = new StasDAO(getContentResolver());
        final List<Tb_course> list = courseDAO.queryAll();
        if (list.size() == 0) {
            Toast.makeText(this, "请先添加课程~", Toast.LENGTH_SHORT).show();
            return;
        }
        String[] arr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Tb_course tb_course = list.get(i);
            arr[i] = String.format("%s | %s", tb_course.getCourcode(), tb_course.getCourname());
        }
        getData(list, 0);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        sp_coursetype.setAdapter(adapter);
        sp_coursetype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getData(list, position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void registerBluetoothReciver() {
        bluetoothReciver = new BluetoothReciver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        registerReceiver(bluetoothReciver, filter);
    }


    private void initVIew() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("点名");
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setVisibility(View.VISIBLE);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.rdTextColorPress));
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DianMingActivity.this.finish();
            }
        });

        lv_student = findViewById(R.id.lv_student);
        sp_coursetype = findViewById(R.id.sp_coursetype);
        adapter = new RecordAdaper(this, uncheckList);
        lv_student.setAdapter((ListAdapter) adapter.getAdapter());
        adapter.setOnChildViewClickListener(this);
    }

    void getData(List<Tb_course> list, int position) {
        courseCode = list.get(position).getCourcode();
    }

    List<Tb_student> getCurrentCourseStudent(String courseCode) {
        StudentDAO studentDAO = new StudentDAO(getContentResolver());
        return studentDAO.querByCourseCode(courseCode);
    }

//    private BluetoothAdapter.LeScanCallback scanCallback = new BluetoothAdapter.LeScanCallback() {
//        @Override
//        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
//
//        }
//    };

    public int isContainsAddress(String address) {
        for (Tb_student student : currentCourseStudent) {
            if (student.getMac().equals(address)) {
                return currentCourseStudent.indexOf(student);
            }
        }
        return -1;
    }

    /**
     * 扫描五次？ 设置成扫描25s.
     *
     * @param view
     */
    public void startScanBluetoothMac(View view) {
        if (mBluetoothAdapter != null && mBluetoothAdapter.isDiscovering()) {
            Toast.makeText(DianMingActivity.this, "蓝牙正在工作~", Toast.LENGTH_SHORT).show();
            return;
        }
        currentCourseStudent = getCurrentCourseStudent(courseCode);
        uncheckList.clear();
        adapter.clearList();
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.enable();
        } else {
            mBluetoothAdapter.startDiscovery();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
//                mBluetoothAdapter.stopLeScan(scanCallback);
                    if (currentCourseStudent.size() * 1.0f / (macAddress.size() + currentCourseStudent.size()) < 0.9f && times < 6) {
                        mBluetoothAdapter.cancelDiscovery();
                        mBluetoothAdapter.startDiscovery();
                        times++;
                    } else {
                        times = 1;
                        mBluetoothAdapter.cancelDiscovery();
                        Toast.makeText(DianMingActivity.this, "剩下未点名的用户：" + currentCourseStudent.size() + "", Toast.LENGTH_SHORT).show();
                        Log.e("剩下未点名的用户: ", currentCourseStudent.size() + "");
                        for (int i = 0; i < currentCourseStudent.size(); i++) {
                            Tb_record tb_record = getAttTb_Record(currentCourseStudent.get(i), courseCode, "旷课");
                            uncheckList.add(tb_record);
                            recordDAO.insert(tb_record);
                            upDateStas(currentCourseStudent.get(i));
                        }

                        adapter.addAll(currentCourseStudent);
                    }
                    //完成后的操作

                }

            }, 12 * 1000); //先开启一次
        }

    }


    void upDateStas(Tb_student student) {
        List<Tb_record> tb_records = recordDAO.queryByAttNo(Tb_record.getAttNo(courseCode, student.getCourcode()));
        int[] nums = new int[3];
        for (int i = 0; i < tb_records.size(); i++) {
            if (tb_records.get(i).getAttResult().equals("已到")) {
                nums[0]++;
            } else if (tb_records.get(i).getAttResult().equals("旷课")) {
                nums[1]++;
            } else {
                nums[2]++;
            }
        }

        Tb_stas tb_stas = new Tb_stas(Tb_record.getAttNo(courseCode, student.getCourcode()), student.getCourcode(), courseCode, tb_records.size(), nums[0], nums[1], nums[2], 100);
        stasDAO.insertOrUpdateStas(tb_stas);
    }

    public Tb_record getAttTb_Record(Tb_student student, String courseCode, String attResult) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Tb_record tb_record = new Tb_record(String.valueOf(System.currentTimeMillis()) + (Math.random() * 9 + 1) * 1000,
                Tb_record.getAttNo(courseCode, student.getCourcode()),
                attResult, date

        );
        return tb_record;

    }


    @Override
    public void setOnChildViewClickListener(View v, int position) {
        Tb_student student = adapter.getList().get(position);
        EditAttResultActivity.startActivity(this, uncheckList.get(position), courseCode, student);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == EditAttResultActivity.REQUESTCODE) {
            Tb_record record = (Tb_record) data.getSerializableExtra(EditAttResultActivity.TB_RECORD_KEY);
            for (int i = 0; i < uncheckList.size(); i++) {
                Tb_record record1 = uncheckList.get(i);
                if (record1.getId().equals(record.getId())) {
                    uncheckList.set(i, record);
                    adapter.notifyDataChanged();
                    break;
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (bluetoothReciver != null) {
            unregisterReceiver(bluetoothReciver);
        }
        super.onDestroy();

    }

    class BluetoothReciver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("onReceive: ", intent.getAction());
            if (intent.getAction() == BluetoothDevice.ACTION_FOUND) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String address = device.getAddress();
                int containsAddressIndex = isContainsAddress(address);
                if (containsAddressIndex != -1) {
                    macAddress.add(address);
                    recordDAO.insert(getAttTb_Record(currentCourseStudent.get(containsAddressIndex), courseCode, "已到"));
                    upDateStas(currentCourseStudent.get(containsAddressIndex));
                    currentCourseStudent.remove(containsAddressIndex);
                }
            } else {
                Log.e("onReceive: ", "接收到其他广播啦");
            }
        }
    }
}
