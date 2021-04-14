package core;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 이 클래스는 AsyncTask 클래스를 상속받아서 비동기 통신(백그라운드작업) 기능 구현합니다.
 */
public class PostResponseAsyncTask extends AsyncTask<String, Void, String> {
    //멤버변수 = 필드변수
    private Context context;
    private HashMap<String, String> postDataParams = new HashMap<String, String>();
    private AsyncResponse asyncResponse;
    private ProgressDialog progressDialog;//스프링으로 요청을 보낸 후 응답을 받을때까지 '처리중 입니다.'메세지를 나오게 하는 대화상자
    private String loadingMessage = "처리중 입니다.";
    private boolean showLoadingMessage = true;//위 로딩메세지를 응답을 받을때 화면에서 지우기 위해

    public PostResponseAsyncTask(Context context, HashMap<String, String> postDataParams, AsyncResponse asyncResponse) {
        this.context = context;
        this.postDataParams = postDataParams;
        this.asyncResponse = asyncResponse;//비동기 통신을 종료할때의 결과를 출력
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public HashMap<String, String> getPostDataParams() {
        return postDataParams;
    }

    public void setPostDataParams(HashMap<String, String> postDataParams) {
        this.postDataParams = postDataParams;
    }

    public AsyncResponse getAsyncResponse() {
        return asyncResponse;
    }

    public void setAsyncResponse(AsyncResponse asyncResponse) {
        this.asyncResponse = asyncResponse;
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public void setProgressDialog(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
    }

    public String getLoadingMessage() {
        return loadingMessage;
    }

    public void setLoadingMessage(String loadingMessage) {
        this.loadingMessage = loadingMessage;
    }

    public boolean isShowLoadingMessage() {
        return showLoadingMessage;
    }

    public void setShowLoadingMessage(boolean showLoadingMessage) {
        this.showLoadingMessage = showLoadingMessage;
    }

    private String invokePost(String requestUrl, HashMap<String, String> postDataParams) {
        URL url;
        String response = "";
        try {
            url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);//15초 동안 커넥션 시도
            conn.setConnectTimeout(15000);//15초 동안만 커넥션 시도 후 중지
            conn.setRequestMethod("POST");//스프링앱과 통신시 POST방식만 사용
            conn.setDoInput(true);//커넥션 후 Input 허용
            conn.setDoOutput(true);//커넥션 후 Output 허용
            // postParms를 받아서 처리
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams)
            );
            writer.flush();//버퍼드 갱신=내보냅니다.
            writer.close();//객체 소멸
            os.close();//객체 소멸
            int responseCode = conn.getResponseCode();//200, 204, 400
            if(responseCode == HttpURLConnection.HTTP_OK) {

            }else{

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String getPostDataString(HashMap<String, String> postDataParams) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true; //플래그변수 
        for(Map.Entry<String, String> entry: postDataParams.entrySet()) {
            if(first) {
                first = false;
            }else{
                result.append("&");//txtUsername=값&txtPassword=값
            }
            result.append(URLEncoder.encode(entry.getKey(),"UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
        }//반복문을 거치면 txtUsername=값&txtPassword=값
        return result.toString();//형변환해서 반환
    }

    @Override
    protected String doInBackground(String... requestUrls) {
        //비동기 통신에서 요청사항을 스프링앱에서 응답받는 기능
        String result = "";
        result = invokePost(requestUrls[0], postDataParams);
        return result;
    }
}
