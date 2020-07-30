package com.example.stock.eink.pioneer.stocklookup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.stock.R;

import java.util.Hashtable;

public class ClassifyActivity extends AppCompatActivity {

    private StockLookupDataHandle dataHandlePt;
    private ClassifyNameAdapter nameAdapter;
    private ClassifyMemberAdapter memberAdapter;

    private String classifyName;
    private ImageView quitImageView;

    private EditText classifynameedit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify);

        dataHandlePt = new StockLookupDataHandle();
        dataHandlePt.setclassifyActivity(this);


        TextView titleView = (TextView)findViewById(R.id.textview_title_stocktype);
        classifyName = getIntent().getStringExtra("name");
        titleView.setText(classifyName);

        quitImageView = (ImageView)findViewById(R.id.imageView_title_quit);
        quitImageView.setOnClickListener(listenerQuit);

        classifynameedit = (EditText)findViewById(R.id.saveclassifyname_edit);
        Button classifynamesavebut = (Button)findViewById(R.id.saveclassifyname_button);
        classifynamesavebut.setOnClickListener(listenerSave);

        ListView nameListview = (ListView)findViewById(R.id.classify_listview);
        nameAdapter = new ClassifyNameAdapter(this);
        nameListview.setAdapter(nameAdapter);
        nameListview.setOnItemClickListener(nameListener);


        ListView memberListview = (ListView)findViewById(R.id.classifymember_listview);
        memberAdapter = new ClassifyMemberAdapter();
        memberListview.setAdapter(memberAdapter);
        memberListview.setOnItemClickListener(memberListener);

        dataHandlePt.InitClassifyName();

    }


    public void InitClassifyName(Hashtable<Integer,String> nameMap){
        nameAdapter.setInitClassifyName(nameMap);
    }


    //@退出界面
    private View.OnClickListener listenerQuit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ClassifyActivity.this.finish();
        }
    };

    //@保存分类名字
    private View.OnClickListener listenerSave = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dataHandlePt.saveclassifyname(classifynameedit.getText().toString());
        }
    };

    //@分类名字监听
    private AdapterView.OnItemClickListener nameListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //TextView tmp = (TextView)view.findViewById(R.id.stock_cn_name);

        }
    };

    //@分类名字监听
    private AdapterView.OnItemClickListener memberListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //TextView tmp = (TextView)view.findViewById(R.id.stock_cn_name);

        }
    };


}