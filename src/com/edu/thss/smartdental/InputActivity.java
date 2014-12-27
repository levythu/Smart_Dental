package com.edu.thss.smartdental;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity; 
import android.content.Context;
import android.os.Bundle; 
import android.view.MotionEvent; 
import android.view.View; 
import android.view.View.OnClickListener; 
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout; 
import android.widget.RelativeLayout;
import android.widget.Toast; 
public class InputActivity extends Activity { 
	private RelativeLayout layout; 
	private LinearLayout input_layout; 
	private EditText edit;
	private Button post_reply_button;
	@Override 
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_input); 
		//layout=(RelativeLayout)findViewById(R.id.exit_layout); 
		//input_layout = (LinearLayout)findViewById(R.id.input_layout);
		edit=(EditText)findViewById(R.id.edit_reply);
		edit.setFocusable(true);
        edit.setFocusableInTouchMode(true);
        edit.requestFocus();
        
        post_reply_button = (Button) findViewById(R.id.post_reply);
        //InputMethodManager inputManager =(InputMethodManager)edit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        //inputManager.showSoftInput(edit, 0);
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            public void run() 
            {
                InputMethodManager inputManager =
                    (InputMethodManager)edit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(edit, 0);
            }
        },  

            300);
            
        
		post_reply_button.setOnClickListener(new OnClickListener() { 
			@Override 
			public void onClick(View v) { 
				// TODO Auto-generated method stub 
				Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！", 
						Toast.LENGTH_SHORT).show(); 
			} 
		}); 
		
	} 
	
	@Override 
	public boolean onTouchEvent(MotionEvent event){ 
		finish(); 
		return true; 
	} 

	
} 
