    package com.human.edu;

    import android.content.Intent;
    import android.net.Uri;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.TextView;

    import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnTel, btnLogout;
    TextView textViewWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------ 기본 ---------------
        //메인액티비티가 onCreate로 생성될때, 인텐트로 받은 값을 출력가능
        Intent intent = new Intent(this.getIntent());
        String userId = intent.getStringExtra("editTextID");
        String userPw = intent.getStringExtra("editTextPassword");
        textViewWelcome = findViewById(R.id.textViewWelcome);
        textViewWelcome.setText(userId + "님 환영합니다.");
        //-------------------------------------
        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextID,editTextPassword;
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                //데이터를 입력해서 메인액티비티 화면열기

                startActivity(loginIntent);
                finish();
            }
        });
        btnTel = findViewById(R.id.btnTel);
        btnTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "전화걸기실행!",Toast.LENGTH_LONG).show();
                Intent telIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:010-1111-1111"));
                startActivity(telIntent);
                finish();//앱이 종료되는 것은아니고,
                System.exit(0);//자바프로그램 강제종료방법 0 정상종료, 1 비정상종료
            }
        });

    }

    public void goToNaver(View view) {
        //디버그 : 작동확인, system.out.println();
        //jsp의 alert와 기능을 하는
        //Toast토스트(식빵이 구워서 튀어나오는 모양)
        //Toast.makeText(getApplicationContext(), "네이버웹 클릭!",Toast.LENGTH_LONG).show();
        Intent naverIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://naver.com"));
        startActivity(naverIntent);//액티비티화면 실행
    }
}