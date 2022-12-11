package com.cookandroid.refrigerator;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.List;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public ArrayList<Food> foodList;
    int pushcount;
    int push;

    public MyFirebaseMessagingService() {
        super();
        Task<String> token = FirebaseMessaging.getInstance().getToken();
        token.addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(task.isSuccessful()){
                    Log.d("FCM Token", task.getResult());
                }
            }
        });
        foodList = ((MainActivity)MainActivity.mainContext).getFoodlist();
        push = ((MainActivity)MainActivity.mainContext).getIspush();
        pushcount = ((MainActivity)MainActivity.mainContext).getPushday_count();
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.d("푸시알림", "여기 오면 알림 발송!!!!");

        Log.d("개수", Integer.toString(foodList.size()));

        if(((MainActivity)MainActivity.mainContext).getIspush() == 0){
            for(int i=0; i<foodList.size(); i++){
                Log.d("유통기한",Integer.toString(foodList.get(i).getExpirationDDay()));
                if(foodList.get(i).getExpirationDDay()<pushcount){
                    showNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("body"));
                    break;  //유통기한 임박 재료 하나라도 찾으면 반복문 중지
                }
            }
        }
        else{

        }



    }
    public void showNotification(String title, String message) {
        //팝업 터치시 이동할 액티비티를 지정합니다.
        Intent intent = new Intent(this, MainActivity.class);
        //알림 채널 아이디 : 본인 하고싶으신대로...
        String channel_id = "CHN_ID";
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pushimage);

        //기본 사운드로 알림음 설정. 커스텀하려면 소리 파일의 uri 입력
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channel_id)
                .setSmallIcon(R.drawable.pushimage).setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap)).setLargeIcon(bitmap) //logo
                .setSound(uri)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000}) //알림시 진동 설정 : 1초 진동, 1초 쉬고, 1초 진동
                .setOnlyAlertOnce(true) //동일한 알림은 한번만.. : 확인 하면 다시 울림
                .setContentIntent(pendingIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) { //안드로이드 버전이 커스텀 알림을 불러올 수 있는 버전이면
            //커스텀 레이아웃 호출
//            builder = builder.setContent(getCustomDesign(title, message));
            builder = builder.setContentTitle("유통기한 임박!!")
                    .setContentText("유통기한 임박 재료를 확인해주세요.");
        } else { //아니면 기본 레이아웃 호출
            builder = builder.setContentTitle("유통기한 임박!!")
                    .setContentText("유통기한 임박 재료를 확인해주세요.").setSmallIcon(R.drawable.pushimage);
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //알림 채널이 필요한 안드로이드 버전을 위한 코드
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channel_id, "CHN_NAME", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setSound(uri, null);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        //알림 표시 !
        notificationManager.notify(0, builder.build());
    }
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        //String tempToken = FirebaseMessaging.getInstance().getToken().getResult();
        Log.i("토큰", "성공!!!");
//        Log.d("토큰",tempToken);
    }
}
