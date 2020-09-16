package com.example.newdoc2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.newdoc2.airshipActivity.appearwords;
import static com.example.newdoc2.airshipActivity.mNextword;

public class myDbAdapter {
  myDbHelper myhelper;
  public myDbAdapter(Context context)
  {
    myhelper = new myDbHelper(context);

  }




  public long insertData(String name, int ans)
  {
    SQLiteDatabase dbb = myhelper.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(myDbHelper.col_2, name);
    contentValues.put(myDbHelper.col_3, ans);
    long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
    return id;
  }

  public long insertDataToReg(String name, String pass)
  {
    SQLiteDatabase dbb = myhelper.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(myDbHelper.col_2222, name);
    contentValues.put(myDbHelper.col_3333, pass);
    long id = dbb.insert(myDbHelper.TABLE_NAME4, null , contentValues);
    return id;
  }


  public String getQues(int nuberQus)
  {
    String qustion = null;
    SQLiteDatabase dbb = myhelper.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    String[] columns = {myDbHelper.col_1, myDbHelper.col_2, myDbHelper.col_3, myDbHelper.col_4};
    Cursor cursor =dbb.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
    while (cursor.moveToNext())
    {
      int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.col_1));
      if(cid==nuberQus)
      {
        qustion =cursor.getString(cursor.getColumnIndex(myDbHelper.col_2));
        System.out.println("xxxx"+cid);
        break;
      }
    }


    // long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
    return qustion;
  }



  public String[] getQuesFroInfo(int nuberQus)
  {
    String qustion = null;
    int count = 0;
    String []s=new String[9];
    Map<String, Integer> stringIntegerMap = null;
    SQLiteDatabase dbb = myhelper.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    String[] columns = {myDbHelper.col_11, myDbHelper.col_22, myDbHelper.col_33, myDbHelper.col_44,myDbHelper.col_55,myDbHelper.col_66,myDbHelper.col_77,myDbHelper.col_88,myDbHelper.col_99,myDbHelper.col_1010};
    Cursor cursor =dbb.query(myDbHelper.TABLE_NAME2,columns,null,null,null,null,null);
    while (cursor.moveToNext())
    {
      int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.col_11));
      if(cid==nuberQus)
      {
        count=cursor.getInt(cursor.getColumnIndex(myDbHelper.col_33));
        qustion =cursor.getString(cursor.getColumnIndex(myDbHelper.col_22));
        s[0]=qustion;
        s[1]= String.valueOf(count);
        if(count==3)
        {
          s[2] =cursor.getString(cursor.getColumnIndex(myDbHelper.col_44));
          s[3] =cursor.getString(cursor.getColumnIndex(myDbHelper.col_55));
          s[4] =cursor.getString(cursor.getColumnIndex(myDbHelper.col_66));


        }
        else if(count==4)
        {
          s[2] =cursor.getString(cursor.getColumnIndex(myDbHelper.col_44));
          s[3] =cursor.getString(cursor.getColumnIndex(myDbHelper.col_55));
          s[4] =cursor.getString(cursor.getColumnIndex(myDbHelper.col_66));
          s[5] =cursor.getString(cursor.getColumnIndex(myDbHelper.col_77));
        }
        else if(count==6)
        {
          s[2] =cursor.getString(cursor.getColumnIndex(myDbHelper.col_44));
          s[3] =cursor.getString(cursor.getColumnIndex(myDbHelper.col_55));
          s[4] =cursor.getString(cursor.getColumnIndex(myDbHelper.col_66));
          s[5] =cursor.getString(cursor.getColumnIndex(myDbHelper.col_77));
          s[6] =cursor.getString(cursor.getColumnIndex(myDbHelper.col_88));
          s[7] =cursor.getString(cursor.getColumnIndex(myDbHelper.col_99));
        }

        break;
      }
    }

    //stringIntegerMap.put(qustion,count);

    // long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
    //System.out.println();
    return s;
  }



  public boolean isclickallword()
  {
    String isclick,word;
    List<String> data = new ArrayList<>();
    SQLiteDatabase dbb = myhelper.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    String[] columns = {myDbHelper.col_1Air, myDbHelper.col_2Air, myDbHelper.col_3Air, myDbHelper.col_4Air};

    Cursor cursor = null;
    try {
      cursor =dbb.query(myDbHelper.TABLE_NAMEAir,columns,null,null,null,null,null);
      StringBuffer buffer= new StringBuffer();
      while (cursor.moveToNext())
      {
        int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.col_1Air));
        isclick = cursor.getString(cursor.getColumnIndex(myDbHelper.col_4Air));
        word = cursor.getString(cursor.getColumnIndex(myDbHelper.col_2Air));
        if (!isclick.equals("true")&&!appearwords.contains(word)) {


          return false;

        }



      }
      return true;
    } finally {

      if(cursor != null)
        cursor.close();
    }


  }

  public List<String> getinfoFroairship(int numword)
  {
    String txt,word ,typword= null;
    String isclick;
    List<String> data = new ArrayList<>();
    SQLiteDatabase dbb = myhelper.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    String[] columns = {myDbHelper.col_1Air, myDbHelper.col_2Air, myDbHelper.col_3Air, myDbHelper.col_4Air};

    Cursor cursor = null;
    try {
      cursor =dbb.query(myDbHelper.TABLE_NAMEAir,columns,null,null,null,null,null);
      StringBuffer buffer= new StringBuffer();
      while (cursor.moveToNext())
      {
        int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.col_1Air));
        if(cid==numword)
        {
          isclick = cursor.getString(cursor.getColumnIndex(myDbHelper.col_4Air));
          word = cursor.getString(cursor.getColumnIndex(myDbHelper.col_2Air));
          if (!isclick.equals("true")) {

            typword = cursor.getString(cursor.getColumnIndex(myDbHelper.col_3Air));
            data.add(word);
            data.add(typword);
            if (mNextword < 43) {
              mNextword++;
            } else {
              mNextword = 1;
            }

          }
          else {
            if (mNextword < 43) {
              mNextword++;
            } else {
              mNextword = 1;
            }
          }


          break;
        }

      }
      System.out.println("mNextword"+mNextword);
      return data;
    } finally {
      // this gets called even if there is an exception somewhere above
      if(cursor != null)
        cursor.close();
    }

    //  System.out.println("cccccccccc"+data.get(0)+data.get(1));

  }


  public void iswordclick(String word1)
  {
    String iscclick="true";
    SQLiteDatabase dbb = myhelper.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(myDbHelper.col_4Air, iscclick);
    dbb.update(myDbHelper.TABLE_NAMEAir,contentValues,myDbHelper.col_2Air +"='"+word1+"'",null);

  }
  public List<listitem> getinfoFroVed()
  {
    String txt,namimg ,namVedio= null;
    int count = 0;
    // String []s=new String[3];
    // Map<String, Integer> stringIntegerMap = null;
    List<listitem> data = new ArrayList<>();
    SQLiteDatabase dbb = myhelper.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    String[] columns = {myDbHelper.col_11111, myDbHelper.col_22222, myDbHelper.col_33333, myDbHelper.col_44444,myDbHelper.col_55555,myDbHelper.col_66666};
    Cursor cursor =dbb.query(myDbHelper.TABLE_NAME5,columns,null,null,null,null,null);
    StringBuffer buffer= new StringBuffer();
    while (cursor.moveToNext())
    {
      listitem dataModel = new listitem();
      int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.col_11111));

      // if(cid==nuberved)
      // {
      dataModel.setTitle(cursor.getString(cursor.getColumnIndex(myDbHelper.col_22222)));
      dataModel.setImg(cursor.getString(cursor.getColumnIndex(myDbHelper.col_33333)));
      dataModel.setNamVedio(cursor.getString(cursor.getColumnIndex(myDbHelper.col_44444)));
      dataModel.setshow(cursor.getString(cursor.getColumnIndex(myDbHelper.col_66666)));
      data.add(dataModel);

              /*  s[0]=txt;
                s[1]= namimg;
                s[2]=  namVedio;*/

      //  break;
      //  }
    }
    System.out.println("kjkjljkl"+data.get(0).title);
    return data;
  }


  public String getBenFroVed(String title)
  {
    String txt,benfit=null,benfit2=null,benfit3= null,namVedio= null;
    ArrayList<String> temp=new ArrayList<>();
    SQLiteDatabase dbb = myhelper.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    String[] columns = {myDbHelper.col_11111, myDbHelper.col_22222, myDbHelper.col_33333, myDbHelper.col_44444,myDbHelper.col_55555,myDbHelper.col_66666};
    Cursor cursor =dbb.query(myDbHelper.TABLE_NAME5,columns,null,null,null,null,null);
    StringBuffer buffer= new StringBuffer();
    while (cursor.moveToNext())
    {
      listitem dataModel = new listitem();
      int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.col_11111));
      txt=cursor.getString(cursor.getColumnIndex(myDbHelper.col_22222));

      if(txt.equals(title)) {
        benfit=cursor.getString(cursor.getColumnIndex(myDbHelper.col_55555));
        break;
      }

    }
    //System.out.println("ben"+benfit);
    return benfit;
  }

  public int updateshowToVidio(String word1) {
    SQLiteDatabase dbb = myhelper.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(myDbHelper.col_66666, "true");
    int i=dbb.update(myDbHelper.TABLE_NAME5,contentValues,myDbHelper.col_22222 +"='"+word1+"'",null);
    return i;
  }


  public List<listitem> getinfoFropdf()
  {
    String txt,namimg ,namVedio= null;
    int count = 0;
    // String []s=new String[3];
    // Map<String, Integer> stringIntegerMap = null;
    List<listitem> data = new ArrayList<>();
    SQLiteDatabase dbb = myhelper.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    String[] columns = {myDbHelper.col_111111, myDbHelper.col_222222, myDbHelper.col_333333};
    Cursor cursor =dbb.query(myDbHelper.TABLE_NAME6,columns,null,null,null,null,null);
    StringBuffer buffer= new StringBuffer();
    while (cursor.moveToNext())
    {
      listitem dataModel = new listitem();
      int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.col_11111));

      // if(cid==nuberved)
      // {
      dataModel.setTitle(cursor.getString(cursor.getColumnIndex(myDbHelper.col_222222)));
      dataModel.setshow(cursor.getString(cursor.getColumnIndex(myDbHelper.col_333333)));
      data.add(dataModel);

    }
    return data;
  }



  public int updateshowTopdf(String word1) {
    SQLiteDatabase dbb = myhelper.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(myDbHelper.col_333333,"true");
    int i=dbb.update(myDbHelper.TABLE_NAME6,contentValues,myDbHelper.col_222222+"='"+word1+"'",null);
    return i;
  }


  public int updateAns(int nuberQus, int ans) {
    SQLiteDatabase dbb = myhelper.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(myDbHelper.col_3, ans);
    int i = dbb.update(myDbHelper.TABLE_NAME, contentValues, myDbHelper.col_1 + " = " + nuberQus, null);
    return i;
  }

  public int updateAnsToInfo(int nuberQus, String ans) {
    SQLiteDatabase dbb = myhelper.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(myDbHelper.col_1010, ans);
    int i = dbb.update(myDbHelper.TABLE_NAME2, contentValues, myDbHelper.col_11 + " = " + nuberQus, null);
    return i;
  }

  public boolean checFromReg(String name,String pass)
  {
    String name1,pass1;
    SQLiteDatabase dbb = myhelper.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    String[] columns = {myDbHelper.col_1111, myDbHelper.col_2222, myDbHelper.col_3333};
    Cursor cursor =dbb.query(myDbHelper.TABLE_NAME4,columns,null,null,null,null,null);
    while (cursor.moveToNext())
    {
      name1 =cursor.getString(cursor.getColumnIndex(myDbHelper.col_2222));
      pass1 =cursor.getString(cursor.getColumnIndex(myDbHelper.col_3333));
      if(name1.equals(name)&&pass1.equals(pass))
      {
        return true;

      }
    }


    // long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
    return false;
  }

  public int[] getthesum()
  {
    int stress = 0,anxiety = 0,depression = 0;
    int[]result=new int[3];
    SQLiteDatabase db = myhelper.getWritableDatabase();
    String[] columns = {myDbHelper.col_1, myDbHelper.col_2, myDbHelper.col_3, myDbHelper.col_4};
    Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
    StringBuffer buffer= new StringBuffer();
    while (cursor.moveToNext()) {
      int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.col_1));
      int ans =cursor.getInt(cursor.getColumnIndex(myDbHelper.col_3));
      String  type =cursor.getString(cursor.getColumnIndex(myDbHelper.col_4));
      if(type.equals("stress"))
      {
        stress=stress+ans;
      }
      else if(type.equals("anxiety"))
      {
        anxiety=anxiety+ans;
      }
      else if (type.equals("depression"))
      {
        depression=depression+ans;
      }

    }
    result[0]=stress;
    result[1]=anxiety;
    result[2]=depression;

    return result;

  }

  public String[] resultdiagnosis(int[]result)
  {
    String [] resultDi=new String[3];
    SQLiteDatabase db = myhelper.getWritableDatabase();
    String[] columns = {myDbHelper.col_111, myDbHelper.col_222, myDbHelper.col_333, myDbHelper.col_444};
    Cursor cursor =db.query(myDbHelper.TABLE_NAME3,columns,null,null,null,null,null);
    StringBuffer buffer= new StringBuffer();
    while (cursor.moveToNext()) {
      int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.col_111));
      String  type =cursor.getString(cursor.getColumnIndex(myDbHelper.col_222));
      int sum = cursor.getInt(cursor.getColumnIndex(myDbHelper.col_333));
      String result2 =cursor.getString(cursor.getColumnIndex(myDbHelper.col_444));
      if(type.equals("stress")&&sum==result[0])
      {
        resultDi[0]=result2;
      }
      else if(type.equals("anxiety")&&sum==result[1])
      {
        resultDi[1]=result2;
      }
      else if(type.equals("depression")&&sum==result[2])
      {
        resultDi[2]=result2;
      }

    }
    return resultDi;

  }




   /* public String getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.NAME,myDbHelper.MyPASSWORD};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.col_2));
            String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            buffer.append(cid+ "   " + name + "   " + password +" \n");
        }
        return buffer.toString();
    }

    public  int delete(String uname)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.NAME+" = ?",whereArgs);
        return  count;
    }

    public int updateName(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.NAME+" = ?",whereArgs );
        return count;
    }*/

  static class myDbHelper extends SQLiteOpenHelper
  {
    private static final String DATABASE_NAME = "myDatabase";    // Database Name
    private static final String TABLE_NAME = "diagnosisTable";   // Table Name
    private static final int DATABASE_Version = 1;   // Database Version
    private static final String col_1="_id";     // Column I (Primary Key)
    private static final String col_2 = "qustion";    //Column II
    private static final String col_3= "answer";    // Column III
    private static final String col_4= "type";    // Column III
    private static final String TABLE_NAME2 = "InfoProfile";   // Table Name
    private static final String col_11="id";     // Column I (Primary Key)
    private static final String col_22 = "ques";    //Column II
    private static final String col_33= "countAsw";    // Column III
    private static final String col_44= "ans1";    // Column IIII
    private static final String col_55= "ans2";    // Column IIII
    private static final String col_66= "ans3";    // Column IIII
    private static final String col_77= "ans4";    // Column IIII
    private static final String col_88= "ans5";    // Column IIII
    private static final String col_99= "ans6";    // Column IIII
    private static final String col_1010= "YourAns";    // Column IIII
    private static final String TABLE_NAME3 = "DassScore";   // Table Name
    private static final String col_111="id";     // Column I (Primary Key)
    private static final String col_222 = "type";    //Column II
    private static final String col_333= "sum";    // Column III
    private static final String col_444= "result";    // Column IIII
    private static final String TABLE_NAME4 = "RegCount";   // Table Name
    private static final String col_1111="id";     // Column I (Primary Key)
    private static final String col_2222 = "uesername";    //Column II
    private static final String col_3333= "pass";    // Column III
    private static final String TABLE_NAME5 = "allvedio";   // Table Name
    private static final String col_11111="id";     // Column I (Primary Key)
    private static final String col_22222= "txt";    //Column II
    private static final String col_33333= "namimg";    // Column III
    private static final String col_44444= "namVedio";    // Column IIII
    private static final String col_55555= "benfit";    // Column IIII
    private static final String col_66666= "show";    // Column IIII

    private static final String TABLE_NAME6 = "allpdf";   // Table Name
    private static final String col_111111="id";     // Column I (Primary Key)
    private static final String col_222222= "name";    //Column II
    private static final String col_333333= "showpdf";    // Column III


    private static final String TABLE_NAMEAir = "airship";   // Table Name
    private static final String col_1Air="id";     // Column I (Primary Key)
    private static final String col_2Air= "word";    //Column II
    private static final String col_3Air= "type";    // Column III
    private static final String col_4Air= "isclickable";    // Column IIII
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            " ("+col_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+col_2+" VARCHAR(255) ,"+ col_3+" NUMBER,"+col_4+"TEXT);";
    private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
    private Context context;

    public myDbHelper(Context context) {
      super(context, DATABASE_NAME, null, DATABASE_Version);
      this.context=context;
    }

    public void onCreate(SQLiteDatabase db) {

      try {
        db.execSQL(CREATE_TABLE);
      } catch (Exception e) {
        Message.message(context,""+e);
      }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      try {
        Message.message(context,"OnUpgrade");
        db.execSQL(DROP_TABLE);
        onCreate(db);
      }catch (Exception e) {
        Message.message(context,""+e);
      }
    }
  }

  public static class Message {
    public static void message(Context context, String message) {
      Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
  }





}

