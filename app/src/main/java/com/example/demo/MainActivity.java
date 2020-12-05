package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    // TODO khai báo các control
    LinearLayout linearLayout_QuanLy_SP;
    private EditText edtTen_SP, edtGia_SP;
    private Button btnThem_SP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO khởi tạo các control toàn cục
        linearLayout_QuanLy_SP = (LinearLayout)findViewById(R.id.linearLayout_QuanLy_SP) ;
        edtTen_SP = (EditText)findViewById(R.id.edtTen_SP);
        edtGia_SP = (EditText)findViewById(R.id.edtGia_SP);
        btnThem_SP = (Button)findViewById(R.id.btnThem_SP);

        //TODO đăng ký sự kiện khi click button them_sp
        btnThem_SP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten_SP = edtTen_SP.getText() + "";
                ten_SP = ten_SP.trim();
                if (ten_SP.length() == 0){
                    Toast.makeText(MainActivity.this, "Xin vui lòng nhập tên sản phẩm", Toast.LENGTH_LONG).show();
                    edtTen_SP.selectAll();
                    edtTen_SP.requestFocus();
                    return;
                }
                float gia_SP = 0;
                try {
                    gia_SP = Float.parseFloat( edtGia_SP.getText() + "");
                    if (gia_SP < 1){
                        Toast.makeText(MainActivity.this, "Giá sản phẩm phải lớn hơn 1", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    edtGia_SP.selectAll();
                    edtGia_SP.requestFocus();
                }

                //Tạo một dòng
                final LinearLayout linearLayout_Row = new LinearLayout(MainActivity.this);
                linearLayout_Row.setOrientation(LinearLayout.HORIZONTAL);

                //Tên
                TextView tvTen_SP = new TextView(MainActivity.this);
                tvTen_SP.setText(ten_SP);
                tvTen_SP.setTextSize(20);
                tvTen_SP.setPadding(5,5,5,5);

                //Giá
                TextView tvGia_SP = new TextView(MainActivity.this);
                tvGia_SP.setText(gia_SP + "");
                tvGia_SP.setTextSize(20);
                tvGia_SP.setPadding(5,5,5,5);
                tvGia_SP.setGravity(Gravity.RIGHT);

                // làm cho nó giản rộng
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                                       ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.weight =1;
                tvGia_SP.setLayoutParams(layoutParams);

                //Đăng ký sự kiện khi khởi tạo
                Button btnXoa_SP = new Button(MainActivity.this){
                    @Override
                    public boolean performClick(){
                        View row = (View) this.getParent();
                        linearLayout_QuanLy_SP.removeView(row);
                        return false;
                    }
                };

                btnXoa_SP.setText("Xoá");
                btnXoa_SP.setTextSize(20);
                linearLayout_Row.addView(tvTen_SP);
                linearLayout_Row.addView(tvGia_SP);
                linearLayout_Row.addView(btnXoa_SP);

                linearLayout_QuanLy_SP.addView(linearLayout_Row);
            }
        });

    }
}
