package com.human.edu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 이 액티비티에서는 리사이클러뷰에 RestAPI Json 데이터를 바인딩 시키는 기능
 * List객체(Json데이터바인딩)-어댑터클래스(데이터와 뷰객체중간)-리사이클러뷰
 */
public class SubActivity extends AppCompatActivity {
    //리사이클러 뷰를 사용할 멤버변수(필드변수) 생성
    private RecyclerAdapter mRecyclerAdapter;
    private List mItemList = new ArrayList<MemberVO>();
    //어댑터에서 선택한 값 확인 변수(선택한 회원을 삭제하기 위해서)
    private int currentCursorId = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
    }
}