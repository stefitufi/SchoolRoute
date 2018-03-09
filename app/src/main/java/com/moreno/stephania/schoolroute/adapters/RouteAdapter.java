package com.moreno.stephania.schoolroute.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moreno.stephania.schoolroute.R;
import com.moreno.stephania.schoolroute.models.SchoolBus;
import com.squareup.picasso.Picasso;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Adapter que infla la vista con cada ruta
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno.</a>
 */
public class RouteAdapter extends ArrayAdapter<SchoolBus> {

    /** **/
    private  List<SchoolBus> contactList;

    /** **/
    private Context context;

    /** **/
    private LayoutInflater mInflater;

    // Constructors
    public RouteAdapter(Context context, List<SchoolBus> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        contactList = objects;
    }

    /***
     *
     * @param position
     * @return
     */
    @Override
    public SchoolBus getItem(int position) {
        return contactList.get(position);
    }

    /***
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.item_route, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        SchoolBus item = getItem(position);

        vh.textViewName.setText(item.getName());
        vh.textViewDescription.setText(item.getDescription());
        Picasso.with(context).load(item.getImgUrl()).placeholder(R.mipmap.ic_launcher).
                error(R.mipmap.ic_launcher).into(vh.imageView);

        return vh.rootView;
    }

    /**
     *
     */
    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final CircleImageView imageView;
        public final TextView textViewName;
        public final TextView textViewDescription;

        private ViewHolder(RelativeLayout rootView, CircleImageView imageView, TextView textViewName,
                           TextView textViewDescription) {
            this.rootView = rootView;
            this.imageView = imageView;
            this.textViewName = textViewName;
            this.textViewDescription = textViewDescription;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            CircleImageView imageView = (CircleImageView) rootView.findViewById(R.id.buses_civ);
            TextView textViewName = (TextView) rootView.findViewById(R.id.name_ruta_tv);
            TextView textViewDescription = (TextView) rootView.findViewById(R.id.description_ruta_tv);
            return new ViewHolder(rootView, imageView, textViewName, textViewDescription);
        }
    }

    /**
     *
     * @param items
     * @return
     */
    public List<SchoolBus> updateAnswers(List<SchoolBus> items) {
        contactList = items;
        //notifyDataSetChanged();
        return items;
    }

}

