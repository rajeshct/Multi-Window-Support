### Welcome to the Multi-Window-Support

**Note: We can use multi-window feature by pressing recent button and than long press app preview and drag to top**

Lets begin implementing multi-window feature in API>23. Lets proceed step by step

###  1) Changes in manifest
 You can set **android:resizeableActivity="true"** either in **activity** or **application** tag in manifest.xml to tell android that our app or activity support multi-window feature.

` <activity android:resizeableActivity="true">`

or

`<application android:resizeableActivity="true" >`

### 2) Set default size for multi-window activity
We can provide default size for activity by writing below code under activity tag in manifest.xml

```
  <activity android:name=".MainActivity"
             android:resizeableActivity="true">
     <layout
                  android:defaultHeight="500dp"
                  android:defaultWidth="600dp"
                  android:gravity="top|end"
                 android:minHeight="450dp"
                  android:minWidth="300dp" />
  </activity>   
```

### 3) Opening activity in multi-window screen 
 **Action intent explicit here**
 
```
Intent intent = new Intent(MainActivity.this, DragAndDropActivity.class);
intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
startActivity(intent);
```

![Action intent explicit adjacent](https://github.com/rajeshct/Multi-Window-Support/blob/master/Action%20intent%20explicit%20here.gif)

**Action intent explicit adjacent**

 ```
 Intent intent = new Intent(MainActivity.this, DragAndDropActivity.class);
 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
 Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
 startActivity(intent);
 ```
 
![Action intent explicit adjacent](https://github.com/rajeshct/Multi-Window-Support/blob/master/Action%20intent%20explicit%20adjacent.gif)

**Action intent implicit adjacent**

```
Intent intent = new Intent(Intent.ACTION_VIEW);
intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT |
Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
startActivity(intent);
 ```      
 
![Action intent implicit adjacent](https://github.com/rajeshct/Multi-Window-Support/blob/master/Action%20intent%20implicit%20adjacent.gif)

**Action intent implicit here**

```    
Intent intent = new Intent(Intent.ACTION_VIEW);
intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
// It doesn't work when we have a default intent chooser
startActivity(intent);
 ```        
        
![Action intent implicit here](https://github.com/rajeshct/Multi-Window-Support/blob/master/Action%20intent%20implicit%20here.gif)


### 4) Drag and drop data between two activities
To use drag and drop feature between two activity we open activity in parallel (multi-window) and perform drag from
`DragAndDropActivity.java`  to `MainActivity.java`

![Multi-Window screen](https://github.com/rajeshct/Multi-Window-Support/blob/master/drag%20and%20drop.gif)
