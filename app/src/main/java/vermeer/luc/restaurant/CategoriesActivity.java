package vermeer.luc.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        CategoriesRequest categoriesRequest = new CategoriesRequest(this);
        categoriesRequest.getCategories(this);

        listView = findViewById(R.id.listView);

        onListItemClick listener = new onListItemClick();
        listView.setOnItemClickListener(listener);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        listView = findViewById(R.id.listView);
        Toast.makeText(this, categories.get(0), Toast.LENGTH_SHORT).show();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.category_item, categories);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class onListItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            String category = (String) parent.getItemAtPosition(position);
            intent.putExtra("category", category);
            startActivity(intent);
        }
    }
}
