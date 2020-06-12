package com.example.sqliteapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.app.Activity;

import com.example.sqliteapplication.Note;

public class AddEditNoteActivity extends AppCompatActivity {

    private static final int MODE_CREATE = 1;
    private static final int MODE_EDIT = 2;

    private EditText textTitle;
    private EditText textContent;
    private Button buttonSave;
    private Button buttonCancel;
    private RadioButton perso, pro, rigolo;
    private RadioGroup radioGroup;

    private Note note;
    private boolean needRefresh;
    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);

        this.textTitle = (EditText)this.findViewById(R.id.editText_note_title);
        this.textContent = (EditText)this.findViewById(R.id.editText_note_content);

        this.buttonSave = (Button)findViewById(R.id.button_save);
        this.buttonCancel = (Button)findViewById(R.id.button_cancel);

        this.radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        this.perso = (RadioButton) findViewById(R.id.radioButtonPerso);
        this.pro = (RadioButton)findViewById(R.id.radioButtonPro);
        this.rigolo = (RadioButton)findViewById(R.id.radioButtonRigolo);

        this.buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                buttonSaveClicked();
            }
        });

        this.buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                buttonCancelClicked();
            }
        });

        Intent intent = this.getIntent();
        this.note = (Note) intent.getSerializableExtra("note");
        if(note== null)  {
            this.mode = MODE_CREATE;
        } else  {
            this.mode = MODE_EDIT;
            this.textTitle.setText(note.getNoteTitle());
            this.textContent.setText(note.getNoteContent());
            perso.setText(Categorie.PERSO.toString());
            pro.setText(Categorie.TRAVAIL.toString());
            rigolo.setText(Categorie.RIGOLO.toString());

            if(note.getCategorie().equals(Categorie.PERSO.toString())){
                perso.setChecked(true);
                pro.setChecked(false);
                rigolo.setChecked(false);
            }else if(note.getCategorie().equals(Categorie.TRAVAIL.toString())){
                perso.setChecked(false);
                pro.setChecked(true);
                rigolo.setChecked(false);
            }else if (note.getCategorie().equals(Categorie.RIGOLO.toString())) {
                perso.setChecked(false);
                pro.setChecked(false);
                rigolo.setChecked(true);
            }
        }
    }

    // User Click on the Save button.
    public void buttonSaveClicked()  {
        MyDatabaseHelper db = new MyDatabaseHelper(this);

        String title = this.textTitle.getText().toString();
        String content = this.textContent.getText().toString();
        Categorie cat;
        if(perso.isChecked()){
            cat = Categorie.PERSO;
        }else if(pro.isChecked()){
            cat = Categorie.TRAVAIL;
        }else{
            cat = Categorie.RIGOLO;
        }
        if(title.equals("") || content.equals("") ||(!perso.isChecked()&&!pro.isChecked()&&!rigolo.isChecked())) {
            Toast.makeText(getApplicationContext(),
                    "Entrez un titre, une catégorie et un contenu svp.", Toast.LENGTH_LONG).show();
            return;
        }

        if(mode == MODE_CREATE ) {
            this.note= new Note(title,content,cat.toString());
            db.addNote(note);
        } else  {
            this.note.setNoteTitle(title);
            this.note.setNoteContent(content);
            this.note.setCategorie(cat.toString());
            db.updateNote(note);
        }

        this.needRefresh = true;

        // Back to MainActivity.
        this.onBackPressed();
    }

    // User Click on the Cancel button.
    public void buttonCancelClicked()  {
        // Do nothing, back MainActivity.
        this.onBackPressed();
    }

    // When completed this Activity,
    // Send feedback to the Activity called it.
    @Override
    public void finish() {

        // Create Intent
        Intent data = new Intent();

        // Request MainActivity refresh its ListView (or not).
        data.putExtra("needRefresh", needRefresh);

        // Set Result
        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }

}