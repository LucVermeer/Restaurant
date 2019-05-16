package vermeer.luc.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter<MenuItem> {

    public MenuAdapter(@NonNull Context context, ArrayList<MenuItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        // Inflates a menu entry so that it can be displayed in a listView
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }
        TextView name = view.findViewById(R.id.name);
        ImageView image = view.findViewById(R.id.imageView);
        TextView price = view.findViewById(R.id.price);
        MenuItem item = getItem(position);
        String priceString = String.valueOf("$ " + item.getPrice());
        name.setText(item.getName());
        price.setText(priceString);
        Picasso.with(getContext()).load(item.getImageUrl()).into(image);

        return view;
    }
}
