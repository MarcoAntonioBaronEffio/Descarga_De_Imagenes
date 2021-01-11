package com.redsystem.descargadeimagenes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DownloadManager;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISO_ALMACENAMIENTO = 1000;
    EditText urlEt;
    Button descargarBtn,LimpiarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlEt = findViewById(R.id.urlEt);
        descargarBtn = findViewById(R.id.descargarBtn);
        LimpiarBtn = findViewById(R.id.LimpiarBtn);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISO_ALMACENAMIENTO:
                { if (grantResults.length >0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED)
                {
                    //PERMISO OTORGADO
                    IniciarDescargar();
                  }
                else {
                    Toast.makeText(this, "PERMISO DENEGADO", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void IniciarDescargar() {

        //CONVERTIR A STRING
        String url = urlEt.getText().toString().trim();

        if (!TextUtils.isEmpty(url)){
            //CREAR LA SOLICITUS DE DESCARGA
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            //TIPO DE RED
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
            request.setTitle("Descarga");
            request.setDescription("Descargando archivo ... ");

            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);


        }else{
            Toast.makeText(this, "Campo vacio", Toast.LENGTH_SHORT).show();
        }


    }


}