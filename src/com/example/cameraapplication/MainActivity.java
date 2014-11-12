package com.example.cameraapplication;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	static int TAKE_PICTURE = 1;
	Uri outputFileUri;
	private Button takeaphoto;
	int TAKE_PHOTO_CODE = 0;
	
	public static int count=10000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		takeaphoto=(Button)findViewById(R.id.list);
		//here,we are making a folder named picFolder to store pics taken by the camera using this application
        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/picFolder/"; 
        File newdir = new File(dir); 
        newdir.mkdirs();
		takeaphoto.setOnClickListener(new View.OnClickListener() {

			   @Override
			   public void onClick(View view) {
				   Random generator = new Random();
				    
				   count = generator.nextInt(count);
				
		            String file = dir+"Image"+count+".jpg";
		            File newfile = new File(file);
		            try {
		                newfile.createNewFile();
		            } catch (IOException e) {}       

		            Uri outputFileUri = Uri.fromFile(newfile);

		            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
		            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

		            startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
				   
			   }
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	Intent data)
	{
	if (requestCode == TAKE_PICTURE && resultCode==RESULT_OK){
	Toast.makeText(this, outputFileUri.toString(),
	Toast.LENGTH_LONG).show();
	}
	}
}
