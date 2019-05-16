package vermeer.luc.restaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {

    private MenuItem menuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);
        Intent intent = getIntent();
        menuItem = (MenuItem) intent.getSerializableExtra("menu_item");

        ImageView image = findViewById(R.id.image);
        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);
        TextView price = findViewById(R.id.price);

// Ik kreeg de picasso hier niet werkend. Snap niet welke context ik moet meegeven.
//        Picasso.with(this).load(menuItem.getImageUrl()).into(image);
        name.setText(menuItem.getName());
        description.setText(menuItem.getDescription());
        String priceString = "€" + menuItem.getPrice();
        price.setText(priceString);
    }
}
