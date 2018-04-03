package com.example.android.philosophyquizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText nameField;
    private EditText responseField;

    private CheckBox firstCheckBox;
    private CheckBox secondCheckBox;
    private CheckBox thirdCheckBox;
    private CheckBox fourthCheckBox;

    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;
    private RadioGroup radioGroup3;
    private RadioGroup radioGroup4;

    private TextView finalScoreTextView;

    private Button submitButton;
    private Button resetButton;

    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.name_field);
        responseField = findViewById(R.id.response_field);

        firstCheckBox = findViewById(R.id.first_checkbox);
        secondCheckBox = findViewById(R.id.second_checkbox);
        thirdCheckBox = findViewById(R.id.wrong_answer_q3);
        fourthCheckBox = findViewById(R.id.fourth_checkbox);

        radioGroup1 = findViewById(R.id.radio1);
        radioGroup2 = findViewById(R.id.radio2);
        radioGroup3 = findViewById(R.id.radio3);
        radioGroup4 = findViewById(R.id.radio4);

        finalScoreTextView = findViewById(R.id.final_score);

        submitButton = findViewById(R.id.submit_button);
        resetButton = findViewById(R.id.reset_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayScoreSummary();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetQuiz();
            }
        });
    }

    public void displayScoreSummary() {
        // Checks if the RadioButtons, CheckBoxes, and EditTexts are empty
        // If yes, show toast or else, show summary
        if(((radioGroup1.getCheckedRadioButtonId() == -1) || (radioGroup2.getCheckedRadioButtonId() == -1)
                || (radioGroup3.getCheckedRadioButtonId() == -1) || (radioGroup4.getCheckedRadioButtonId() == -1))
                && ((!firstCheckBox.isChecked()) && (!secondCheckBox.isChecked()) && (!thirdCheckBox.isChecked()
                && (!thirdCheckBox.isChecked()))) && (responseField.getText().toString().isEmpty())){
            showToast("Please provide answers to the questions above");
        } else {
            String name = nameField.getText().toString();
            showToast("Hi " + name + "!\nYour final score is " + score);

            finalScoreTextView.setText(" " + score);
        }
    }

    // Clears all the RadioButtons, CheckBoxes, EditTexts, and Score
    public void resetQuiz() {
        nameField.setText("");
        responseField.setText("");
        finalScoreTextView.setText("");

        radioGroup1.clearCheck();
        radioGroup2.clearCheck();
        radioGroup3.clearCheck();
        radioGroup4.clearCheck();

        firstCheckBox.setChecked(false);
        secondCheckBox.setChecked(false);
        thirdCheckBox.setChecked(false);
        fourthCheckBox.setChecked(false);

        score = 0;
    }

    // Checks answer from the RadioButtons
    // Questions 1, 2, 4, 6
    public boolean isAnswerFromRadioGroupCorrect(int newAnswer) {
        int firstQuestionAnswer = R.id.answer_q1;
        int secondQuestionAnswer = R.id.answer_q2;
        int fourthQuestionAnswer = R.id.answer_q4;
        int sixthQuestionAnswe = R.id.answer_q6;

        if((firstQuestionAnswer == newAnswer) || (secondQuestionAnswer == newAnswer)
                || (fourthQuestionAnswer == newAnswer) || (sixthQuestionAnswe == newAnswer)) {
            return true;
        }

        return false;
    }

    // Checks the answer from the CheckBoxes
    // Question 3
    public boolean isAnswerFromCheckBoxCorrect() {
        boolean isThirdQuestionFirstCheckBoxChecked = firstCheckBox.isChecked();
        boolean isThirdQuestionSecondCheckBoxChecked = secondCheckBox.isChecked();
        boolean isThirdQuestionThirdCheckBoxChecked = thirdCheckBox.isChecked();
        boolean isThirdQuestionFourthCheckBoxChecked = fourthCheckBox.isChecked();

        // Please modify this for the correct answers. Just remove the variable if it's not the correct answer
        if(isThirdQuestionFirstCheckBoxChecked && isThirdQuestionSecondCheckBoxChecked
                && isThirdQuestionThirdCheckBoxChecked && isThirdQuestionFourthCheckBoxChecked) {
            return true;
        }

        return false;
    }

    // Called from onClick
    // Shows toast if correct or wrong
    // Increments score if correct
    public void radioButtonResponseOnClick(View view) {
        if(isAnswerFromRadioGroupCorrect(view.getId())) {
            showToast(getString(R.string.toast_correct));
            score += 1;
        } else {
            showToast(getString(R.string.toast_wrong));
        }
    }

    // Called from onClick
    // Increments score if all the correct CheckBoxes are checked
    public void checkBoxResponseOnClick(View view) {
        if(isAnswerFromCheckBoxCorrect()) {
            showToast(getString(R.string.toast_correct));
            score += 1;
        }
    }

    // Shows a toast
    public void showToast(String newMessage) {
        Toast toast= Toast.makeText(getApplicationContext(),
                newMessage, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}