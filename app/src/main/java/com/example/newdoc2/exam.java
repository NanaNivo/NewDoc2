package com.example.newdoc2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fivehundredpx.android.blur.BlurringView;

public class exam extends AppCompatActivity {

    BlurringView  mBlurringView2;
    TextView TextExam;
    Button contnu;
    String the_result_of_stress,the_result_of_anxiety,the_result_of_depression;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);


        getSupportActionBar().setTitle("نتيجة التشخيصً");

        mBlurringView2 = (BlurringView) findViewById(R.id.blurring_view2);
        View blurredView2 = findViewById(R.id.blurred_view2);

        // Give the blurring view a reference to the blurred view.
        mBlurringView2.setBlurredView(blurredView2);

        TextExam= findViewById(R.id.TextExam);
        contnu= findViewById(R.id.continu);

        the_result_of_stress= getSharedPreferences("PREFERENCEExam1", MODE_PRIVATE).getString("Exam1", null);
        the_result_of_anxiety=getSharedPreferences("PREFERENCEExam2", MODE_PRIVATE).getString("Exam2", null);
        the_result_of_depression=getSharedPreferences("PREFERENCEExam3", MODE_PRIVATE).getString("Exam3", null);

        Toast.makeText(this,"the_result_of_stress"+the_result_of_stress,Toast.LENGTH_LONG).show();



        // Invalidates the blurring view when the content of the blurred view changes.
        mBlurringView2.invalidate();


      if(!the_result_of_stress.equals("normal")&&!the_result_of_anxiety.equals("normal")&&!the_result_of_depression.equals("normal"))
      {
          TextExam.setText("يبدو أنك تمر بفترة صعبة مؤخراًننصحك بشدة بزيارة الطبيب." +
                 "\n"+
                  "كما يمكنك االضغط على زر المتابعة للقيام ببعض النشاطات التي تساعد في تحسين حالتك");
      }

      else  if(!the_result_of_stress.equals("normal")&&the_result_of_anxiety.equals("normal")&&!the_result_of_depression.equals("normal"))
      {
          String ss="يبدو أنك لا تمر بفترة جيدة  ننصح بزيارة الطبيب إن أمكن "
                  +"\n" +
                  "كما يمكنك االضغط على زر المتابعة للقيام ببعض النشاطات التي تساعد في تحسين حالتك";
          TextExam.setText(ss);
      }
      else  if(the_result_of_stress.equals("normal")&&!the_result_of_anxiety.equals("normal")&&!the_result_of_depression.equals("normal"))
        {
            String ss="يبدو أنك لا تمر بفترة جيدة  ننصح بزيارة الطبيب إن أمكن "
                    +"\n" +
                    "كما يمكنك االضغط على زر المتابعة للقيام ببعض النشاطات التي تساعد في تحسين حالتك";
            TextExam.setText(ss);
        }
      else  if(!the_result_of_stress.equals("normal")&&!the_result_of_anxiety.equals("normal")&&the_result_of_depression.equals("normal"))
      {
          String ss="يبدو أنك لا تمر بفترة جيدة  ننصح بزيارة الطبيب إن أمكن "
                  +"\n" +
                  "كما يمكنك االضغط على زر المتابعة للقيام ببعض النشاطات التي تساعد في تحسين حالتك";
          TextExam.setText(ss);
      }

      else  if(the_result_of_stress.equals("normal")&&the_result_of_anxiety.equals("normal")&&the_result_of_depression.equals("normal"))
      {
          String ss="أنت في حالة جيدة  "
                  +"\n" +
                  "ننصحك بالاستمتاع ببعض النشاطات بالضغط على زر متابعة";
          TextExam.setText(ss);
      }

      else  if(the_result_of_stress.equals("normal")&&the_result_of_anxiety.equals("normal")&&!the_result_of_depression.equals("normal"))
      {
          String ss="يبدو أنك تتعرض لبعض الضغوطات مؤخراً  "
                  +"\n" +
                  "ننصحك بالضغط على زر المتابعة والقيام بالنشاطات بشكل دوري لتخفيف ذلك";
          TextExam.setText(ss);
      }
      else  if(!the_result_of_stress.equals("normal")&&the_result_of_anxiety.equals("normal")&&the_result_of_depression.equals("normal"))
      {
          String ss="يبدو أنك تتعرض لبعض الضغوطات مؤخراً  "
                  +"\n" +
                  "ننصحك بالضغط على زر المتابعة والقيام بالنشاطات بشكل دوري لتخفيف ذلك";
          TextExam.setText(ss);
      }

      else  if(the_result_of_stress.equals("normal")&&!the_result_of_anxiety.equals("normal")&&the_result_of_depression.equals("normal"))
      {
          String ss="يبدو أنك تتعرض لبعض الضغوطات مؤخراً  "
                  +"\n" +
                  "ننصحك بالضغط على زر المتابعة والقيام بالنشاطات بشكل دوري لتخفيف ذلك";
          TextExam.setText(ss);
      }





    }

    public void contnu(View view) {
        Intent intent = new Intent(getApplicationContext(), ElementMain.class);
        startActivity(intent);
    }
}
