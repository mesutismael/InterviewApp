package Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.esmael.interviewapp.R;
import com.squareup.picasso.Picasso;

import Models.Data;
import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

/**
 * Created by esmael256 on 5/19/2016.
 */
//Annotate the class with the layout ID of the item.
@LayoutId(R.layout.single_item_display_layout)
public class DataAdapter extends ItemViewHolder<Data> {

    //Annotate every field with the ID of the view in the layout.
    //The views will automatically be assigned to the fields.
    @ViewId(R.id.associated_image)
    ImageView imageViewAssociatedImage;

    @ViewId(R.id.title_trending)
    TextView textViewSong;


    //Extend ItemViewHolder and call super(view)
    public DataAdapter(View view) {
        super(view);
    }

    //Override onSetValues() to set the values of the items in the views.
    @Override
    public void onSetValues(Data data, PositionInfo positionInfo) {


//Load the image using an external library ie picasso in this case
        Picasso.with(getContext())
                .load(data.getThumb_nail())
                .placeholder(R.drawable.telegram) // optional
                .error(R.drawable.telegram)         // optional
                .into(imageViewAssociatedImage);
//Load the title
        textViewSong.setText(data.getTitle());


    }
}
