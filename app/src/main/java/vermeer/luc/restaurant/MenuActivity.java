package vermeer.luc.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuItemsRequest.Callback {

    private String category;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();
        category = intent.getStringExtra("category");

        MenuItemsRequest menuItemsRequest = new MenuItemsRequest(this);
        menuItemsRequest.getMenu(this, category);

        listView = findViewById(R.id.listViewMenu);

        onListItemClick listener = new onListItemClick();
        listView.setOnItemClickListener(listener);
    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> menuItems) {
        listView = findViewById(R.id.listViewMenu);
        MenuAdapter adapter = new MenuAdapter(this, menuItems);
        listView.setAdapter(adapter);
    }

    @Override
    public void gotMenuItemsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    private class onListItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            MenuItem menuItem = (MenuItem) parent.getItemAtPosition(position);
            intent.putExtra("menu_item", menuItem);
            startActivity(intent);
        }
    }
}
