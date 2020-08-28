package com.example.numerica;

import android.content.Context;  
import android.content.res.AssetManager;  
import android.graphics.Typeface;  
import android.util.AttributeSet;  
import android.widget.TextView;  
  
import java.io.File;  
  

public class NewText extends TextView{  
    public NewText(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        init(context);  
    }   
    private void init(Context context) {  
        String file = "fonts" + File.separator + "digital-7.ttf";  
        AssetManager assets = context.getAssets();  
        Typeface font = Typeface.createFromAsset(assets, file);  
        setTypeface(font);  
    }  
}  
