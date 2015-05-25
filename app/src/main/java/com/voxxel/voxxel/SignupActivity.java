package com.voxxel.voxxel;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.voxxel.api.AuthService;
import com.voxxel.api.ServiceGenerator;
import com.voxxel.Constants;

public class SignupActivity extends AppCompatActivity {

    private UserSignupTask mSignupTask = null;
    private AutoCompleteTextView mEmailView;
    private EditText mUsernameView;
    private EditText mPasswordView;
    private EditText mPasswordConfirmView;

    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        authService = ServiceGenerator.createService(AuthService.class, Constants.BASE_URL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickSignupButton(View view) {
        attemptSignup();
    }

    public void attemptSignup() {
        if (mSignupTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mUsernameView.setError(null);
        mPasswordView.setError(null);
        mPasswordConfirmView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();
        String passwordConfirm = mPasswordConfirmView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        } else if (!doesPasswordMatch(password, passwordConfirm)) {
            mPasswordView.setError(getString(R.string.error_password_confirm));
            focusView = mPasswordConfirmView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            //TODO: showProgress(true);
        }

    }

    private boolean validateInput() {
        return false;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private boolean doesPasswordMatch(String password, String confirm) {
        return password.equals(confirm);
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    public class UserSignupTask extends AsyncTask<Void, Void, Boolean> {
        //TODO: instantiate UserModel class instead
        private final String mEmail;
        private final String mUsername;
        private final String mPassword;
        private final String mPasswordConfirm;

        UserSignupTask(String email, String username, String password, String passwordConfirm) {
            mEmail = email;
            mUsername = username;
            mPassword = password;
            mPasswordConfirm = passwordConfirm;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

        }

        @Override
        protected void onCancelled() {

        }
    }
}
