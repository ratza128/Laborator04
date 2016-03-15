package ro.pub.cs.systems.pdsd.lab04.contactsmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class ContactsManagerActivity extends Activity {

	private int flag=0;
	private String numar;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        setContentView(R.layout.activity_contacts_manager);
        final Button add=(Button)findViewById(R.id.button_additional);
        add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(flag==0){
					add.setText("Hide additional info");
					LinearLayout additional=(LinearLayout) findViewById(R.id.layout_additional);
					additional.setVisibility(View.VISIBLE);
					flag++;
				}
				else if(flag==1){
					add.setText("Show Additional info");
					LinearLayout additional=(LinearLayout) findViewById(R.id.layout_additional);
					additional.setVisibility(View.GONE);
					flag=0;
				}
				
			}
		});        
        
        EditText mPhone=(EditText) findViewById(R.id.text_phone);
        Intent intent = getIntent();
        if (intent != null) {
          String phone = intent.getStringExtra("ro.pub.cs.systems.pdsd.lab04.contactsmanager.PHONE_NUMBER_KEY");
          if (phone != null) {
            mPhone.setText(phone);
          } else {
            Toast.makeText(this,"eroare", Toast.LENGTH_LONG).show();
           
          }
        } 
        
        Button save=(Button) findViewById(R.id.button_save);
        save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
				intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
				
				
		        String name;
		    	String phone;
		    	String email;
		    	String address;
		    	String company;
		    	String jobTitle;
		    	String website;
		    	String im;
		    	
		        
		        
		        EditText mName=(EditText) findViewById(R.id.text_name);
		        name=mName.getText().toString();
		        
		        EditText mPhone=(EditText) findViewById(R.id.text_phone);
		        phone=mPhone.getText().toString();
		        numar=phone;
		        
		        EditText mEmail=(EditText) findViewById(R.id.text_email);
		        email=mEmail.getText().toString();
		        
		        EditText mAddress=(EditText) findViewById(R.id.text_address);
		        address=mAddress.getText().toString();
		        
		        EditText mCompany=(EditText) findViewById(R.id.text_company);
		        company=mCompany.getText().toString();
		        
		        EditText mJobTitle=(EditText) findViewById(R.id.text_jobtitle);
		        jobTitle=mJobTitle.getText().toString();
		        
		        EditText mWebsite=(EditText) findViewById(R.id.text_website);
		        website=mWebsite.getText().toString();
		        
		        EditText mIm=(EditText) findViewById(R.id.text_im);
		        im=mIm.getText().toString();
				
				if (name != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
				}
				if (phone != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
				}
				if (email != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
				}
				if (address != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
				}
				if (jobTitle != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle);
				}
				if (company != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
				}
				ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
				if (website != null) {
				  ContentValues websiteRow = new ContentValues();
				  websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
				  websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website);
				  contactData.add(websiteRow);
				}
				if (im != null) {
				  ContentValues imRow = new ContentValues();
				  imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
				  imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im);
				  contactData.add(imRow);
				}
				intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
				startActivity(intent);
			}
		});
        
        Button cancel=(Button) findViewById(R.id.button_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contacts_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
