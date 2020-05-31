package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static int count = 0 ;
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    @Override
    protected void onResume () {
        super.onResume();
        count = 0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void createNotification (View view) {
        count ++ ; // esta parte funcionara para agregar un valor de 1 a la variable entera count inicializada en 0
        Intent notificationIntent = new Intent(getApplicationContext() , MainActivity. class ) ;
        //El intent es un objeto de mensajeria que permite la intereacción entre componentes de la aplicación, en caso se llamara notificationIntent
        notificationIntent.putExtra( "fromNotification" , true ) ;
        //El put extra  del objeto notificationIntent nos enviara un valor boolean "true"
        notificationIntent.setFlags(Intent. FLAG_ACTIVITY_CLEAR_TOP | Intent. FLAG_ACTIVITY_SINGLE_TOP ) ;
        PendingIntent pendingIntent = PendingIntent. getActivity ( this, 0 , notificationIntent , 0 ) ;
        // El pending intent especifica una acción que se realizara en el futuro ,en este caso cuando se haga clic en la notificacion generada
        NotificationManager mNotificationManager = (NotificationManager) getSystemService( NOTIFICATION_SERVICE ) ;
        // Establece el administrador de notificaciones del telefono que ejecuta la app
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext() , default_notification_channel_id ) ;
        //NotificationCompat permite con la versión de compatibilidad de recursos crear
        // una notificación la cual se creara en nuestro dispositivo android llamada mBuild
        mBuilder.setContentTitle( "Mi Notificación" ) ;
        //Se le otorga un titulo la notificación mBuilder
        mBuilder.setContentIntent(pendingIntent) ;
        // establece contenido pasando un pendinga Intent
        mBuilder.setContentText( "EJEMPLO EVENTO DE NOTIFICACIÓN" ) ;
        //Se le otorga un cuerpot de texto a  la notificación mBuilder
        mBuilder.setSmallIcon(R.drawable. ic_stat_name ) ;
        //Se le establece el icono de la notificación mBuilder
        mBuilder.setAutoCancel( true ) ;
        //Hace que la  notificación automaticamente  desaparezca cuando el usuario la toca
        mBuilder.setBadgeIconType( NotificationCompat.BADGE_ICON_SMALL );
        //Se establece el  tamaño del icono de la notificación mBuilder
        mBuilder.setNumber( count ) ;
        //Al mBuilder se le establece un valor númerico el cual se mostrara en el la placa del icono de la aplicación
        // este representara la cantidad de notificaciones pendientees
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) { // Evalua que el dispositivo tenga como minimo la version 8 de android
            int importance = NotificationManager. IMPORTANCE_HIGH ; // crea un valor int de importancia de notificación alta
            NotificationChannel notificationChannel = new NotificationChannel( NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
            // Se crea el canala de notificación y se le establece el valor del canal de notificaciones , el nombre que se da y la importancia de la notificación
            mBuilder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
            //Establece un identificador y se le pasa el valor del canal de la notificación
            assert mNotificationManager != null;
            // asegura que el objeto mNotificationManager sea diferente de nulo
            mNotificationManager.createNotificationChannel(notificationChannel) ;
            //Se crea un canal de notificaciones
        }
        assert mNotificationManager != null;
        // asegura que el objeto mNotificationManager sea diferente de nulo para poder  crear una placa de notificación en el icono de la aplicación
        mNotificationManager.notify(( int ) System. currentTimeMillis () , mBuilder.build()) ;

    }
}
