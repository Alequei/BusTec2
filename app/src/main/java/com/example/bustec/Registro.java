package com.example.bustec;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

public class Registro extends AppCompatActivity {
    ImageButton btnvolver;
    ImageButton Imgusuariophoto;
    static  int PReqCode=1;
    static  int REQUESCODE=1;
    Uri pickedImage;

    private EditText useremail,userpasswor1,userpasswor2,usernomber;
    private  ImageButton btRegister;
    private FirebaseAuth auth;
    private ProgressBar logindproce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Llamado de variables
        useremail=findViewById(R.id.ingresocorreo);
        userpasswor1=findViewById(R.id.ingresodeclave);
        userpasswor2=findViewById(R.id.verificarclave);
        usernomber=findViewById(R.id.ingresodeusuario);
        logindproce=findViewById(R.id.processbarregis);
        btRegister=findViewById(R.id.btRegister);
        //
        logindproce.setVisibility(View.INVISIBLE);
        //Variable de Firebase
        auth=FirebaseAuth.getInstance();

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             btRegister.setVisibility(View.INVISIBLE);
             logindproce.setVisibility(View.VISIBLE);
             final String email=useremail.getText().toString();
                final String pass1=userpasswor1.getText().toString();
                final String pass2=userpasswor2.getText().toString();
                final String name=usernomber.getText().toString();
                //Condicion par a ver los campos

                if (email.isEmpty()||name.isEmpty()||pass1.isEmpty() || !pass1.equals(pass2)){


                    //algo sale mal: todos los campos deben ser rellenados
                    //necesitamos mostrar un mensaje de error
                    showMessage("Verifica todos os campos");
                    btRegister.setVisibility(View.VISIBLE);
                    logindproce.setVisibility(View.INVISIBLE);

                }
                else{
                    //todo está bien y todos los campos están completos ahora podemos comenzar a crear una cuenta de usuario
                   //El método Crear cuenta de usuario intentará crear el usuario si el correo electrónico es válido
                    CreateUserAccount(email,name,pass1);
                }


            }
        });
        //Regresamos al Login
        setContentView(R.layout.activity_registro);
        btnvolver = findViewById(R.id.volver);
        btnvolver .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
        //Varialbes de  imagen de perfil
        Imgusuariophoto=findViewById(R.id.imagenperfil);
        Imgusuariophoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT>=22){
                    checkAndRequestForPermission();
                }
                else
                {
                    openGallery();
                }
            }
        });
    }

    private void CreateUserAccount(String email, final String name, String pass1) {

        // this method  create user account with specific email and password

        auth.createUserWithEmailAndPassword(email,pass1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //user account create succressfully
                    showMessage("Cuenta Creada");
                    //after we create user account we need to update his profile picture   and name
                    updateUserinfo(name,pickedImage,auth.getCurrentUser());
                }else {
                    //account  creation falled
                    showMessage("Creación de Cuenta Fallida"+task.getException().getMessage());
                    btRegister.setVisibility(View.VISIBLE);
                }
            }
        });


    }
    //update user photo and name
    private void updateUserinfo(final String name, Uri pickedImage, final FirebaseUser currentUser) {

        //first we need to upload user  phto to firebase storage and get url
        StorageReference reference= FirebaseStorage.getInstance().getReference().child("User_Photos");
        final StorageReference imageFilePath=reference.child(pickedImage.getLastPathSegment());
        imageFilePath.putFile(pickedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //Image upload succesfully
                //now we can get our iamge url

                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //uri contain user image url

                        UserProfileChangeRequest profileChangeRequest=new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .setPhotoUri(uri)
                                .build();
                        currentUser.updateProfile(profileChangeRequest)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            //user info update successfully
                                            showMessage("Registro completado");
                                            updateUI();
                                        }
                                    }
                                });
                    }
                });
            }
        });
    }

    private void updateUI() {
        Intent reficlogeo=new Intent(getApplicationContext(),Login.class);
        startActivity(reficlogeo);
        finish();
    }

    //Metodo para  enviar  el error
    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

    }

    //Clase que llavara a  la galeria de imagen y abrira
    private void openGallery() {
        //TODO:abre la intención de la galería y espera a que el usuario elija una imagen!

        Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);


    }

    private void checkAndRequestForPermission() {

    if (ContextCompat.checkSelfPermission(Registro.this, Manifest.permission.READ_EXTERNAL_STORAGE)

        != PackageManager.PERMISSION_GRANTED){
        if (ActivityCompat.shouldShowRequestPermissionRationale(Registro.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(Registro.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();
        }
        else {
            ActivityCompat.requestPermissions(Registro.this,
                                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                                PReqCode);
        }

    }
    else
        openGallery();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode ==REQUESCODE && data !=null){
            //the user has successfully picked an image
            //we  need to save its reference to a Uri varial
            pickedImage=data.getData();
            Imgusuariophoto.setImageURI(pickedImage);

        }
    }
}
