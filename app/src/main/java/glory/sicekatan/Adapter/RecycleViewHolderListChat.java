package glory.sicekatan.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import glory.sicekatan.R;

/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleViewHolderListChat extends RecyclerView.ViewHolder {

    public TextView txtNamaUser,txtLastChat;
    public ImageView img_iconlistchat;

    public RecycleViewHolderListChat(View itemView) {
        super(itemView);

        txtNamaUser = (TextView) itemView.findViewById(R.id.txt_namauser);
        img_iconlistchat = (ImageView) itemView.findViewById(R.id.img_iconlistchat);

    }
}
