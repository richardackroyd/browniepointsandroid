package centurion.dev.browniepoints;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rich on 25/11/2017.
 */

public class pointsAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<String> allPointsAccounts = new ArrayList<String>();

    public pointsAdapter(Context context) {

        this.mContext = context;
        this.mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {

        return allPointsAccounts.size();

    }

    @Override
    public Object getItem(int position) {

        return allPointsAccounts.get(position);

    }

    @Override
    public long getItemId(int position) {

        return position;

    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {

        AppCompatTextView itemView;
        if (convertView == null) {

            itemView = (AppCompatTextView) mLayoutInflater.inflate(
                    R.layout.content_points_list_view, parent, false);
        } else {
            itemView = (AppCompatTextView) convertView;
        }

        TextView titleText = (TextView)
                itemView.findViewById(R.id.label);

        titleText.setText(allPointsAccounts.get(position));

        return itemView;

    }

    public void upDateEntries(ArrayList<String> pointsAccounts){

        allPointsAccounts = pointsAccounts;
        notifyDataSetChanged();

    }

}
