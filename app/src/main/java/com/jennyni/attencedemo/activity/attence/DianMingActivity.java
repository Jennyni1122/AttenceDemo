package com.jennyni.attencedemo.activity.attence;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.adapter.StudentAdaper;
import com.jennyni.attencedemo.adapter.apater.IAdapter;
import com.jennyni.attencedemo.contentprovider.ProviderContract;
import com.jennyni.attencedemo.dao.CourseDAO;
import com.jennyni.attencedemo.dao.StudentDAO;
import com.jennyni.attencedemo.db.Tb_course;
import com.jennyni.attencedemo.db.Tb_student;

import java.util.ArrayList;
import java.util.List;

public class DianMingActivity extends AppCompatActivity {


    private ListView lv_student;

    private BluetoothAdapter mBluetoothAdapter;
    private Spinner sp_coursetype;
    private String courseCode;
    private CourseDAO courseDAO;
    private Handler handler = new Handler();
    private List<Tb_student> currentCourseStudent;
    private List<String> macAddress = new ArrayList<>();
    private IAdapter<Tb_student> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_ming);

        initVIew();

        courseDAO = new CourseDAO(getContentResolver());
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, arr);
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

    private void initVIew() {
        lv_student = findViewById(R.id.lv_student);
        adapter = new StudentAdaper(this);
        lv_student.setAdapter((ListAdapter) adapter.getAdapter());
    }

    void getData(List<Tb_course> list, int position) {
        courseCode = list.get(position).getCourcode();
    }

    List<Tb_student> getCurrentCourseStudent(String courseCode) {
        StudentDAO studentDAO = new StudentDAO(getContentResolver());
        return studentDAO.querByCourse(courseCode);
    }

    private BluetoothAdapter.LeScanCallback scanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            String address = device.getAddress();
            int containsAddress = isContainsAddress(address);
            if (containsAddress != -1) {
                macAddress.add(address);
                currentCourseStudent.remove(containsAddress);
            }
        }
    };

    public int isContainsAddress(String address) {
//        for (int i = 0; i < currentCourseStudent.size(); i++) {
//            currentCourseStudent.get(i)
//        }
        for (Tb_student student : currentCourseStudent) {
            if (student.getCourcode().equals(address)) {
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
        adapter.clearList();
        currentCourseStudent = getCurrentCourseStudent(courseCode);
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        mBluetoothAdapter.enable();
        mBluetoothAdapter.startLeScan(scanCallback);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mBluetoothAdapter.stopLeScan(scanCallback);
                Toast.makeText(DianMingActivity.this, "剩下未点名的用户：" + currentCourseStudent.size() + "", Toast.LENGTH_SHORT).show();
                Log.e("剩下未点名的用户: ", currentCourseStudent.size() + "");
                adapter.addAll(currentCourseStudent);
            }

        }, 25 * 1000);


    }


}
