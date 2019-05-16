package vermeer.luc.restaurant;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuItemsRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    private Callback activity;

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotMenuItemsError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        ArrayList<MenuItem> arrayList = new ArrayList<>();
        try {
            JSONArray menuItems = response.getJSONArray("items");
            for (int i = 0; i < menuItems.length(); i++) {
                MenuItem menuItem = new MenuItem();
                JSONObject menuItemJSON = menuItems.getJSONObject(i);
                menuItem.setName(menuItemJSON.getString("name"));
                menuItem.setDescription(menuItemJSON.getString("description"));
                menuItem.setCategory(menuItemJSON.getString("category"));
                menuItem.setPrice(menuItemJSON.getInt("price"));
                menuItem.setImageUrl(menuItemJSON.getString("image_url"));
                arrayList.add(menuItem);
            }
            activity.gotMenuItems(arrayList);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> menuItems);
        void gotMenuItemsError(String message);
    }

    public MenuItemsRequest(Context menuItemContext) {
        this.context = menuItemContext;
    }

    public void getMenu(Callback activity, String category) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu?category=" + category, null, this,this);
        queue.add(jsonObjectRequest);
    }
}