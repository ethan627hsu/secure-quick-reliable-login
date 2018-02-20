package org.ea.sqrl.activites;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.ea.sqrl.BuildConfig;
import org.ea.sqrl.R;
import org.ea.sqrl.database.IdentityDBHelper;

/**
 * This base activity is inherited by all other activities. We place logic used for menus,
 * background processes and other things that are untied to the current context of the application.
 *
 * @author Daniel Persson
 */
public class BaseActivity extends AppCompatActivity {
    protected final IdentityDBHelper mDbHelper;

    public BaseActivity() {
        mDbHelper = new IdentityDBHelper(this);
    }

    @Override
    protected void onDestroy() {
        mDbHelper.close();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_default, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                Intent intent = new Intent(this, IntroductionActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_about:
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(BaseActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(BaseActivity.this);
                }
                builder.setTitle(R.string.about_message_title)
                        .setMessage(getString(R.string.about_message_text, BuildConfig.VERSION_NAME))
                        .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
