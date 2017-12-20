package centurion.dev.browniepoints;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rich on 25/11/2017.
 */

public class pointsAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<PointsAccount> allPointsAccounts = new ArrayList<PointsAccount>();

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

//        AppCompatTextView itemView;
          RelativeLayout itemView;
        if (convertView == null) {

            itemView = (RelativeLayout) mLayoutInflater.inflate(
                    R.layout.content_points_list_view, parent, false);
        } else {
            itemView = (RelativeLayout) convertView;
        }

        TextView nameText = (TextView)
                itemView.findViewById(R.id.name);

        nameText.setText(allPointsAccounts.get(position).name + " " + allPointsAccounts.get(position).points);

        return itemView;

    }

    public void upDateEntries(ArrayList<PointsAccount> pointsAccounts){

        allPointsAccounts = pointsAccounts;
        notifyDataSetChanged();

    }

}
