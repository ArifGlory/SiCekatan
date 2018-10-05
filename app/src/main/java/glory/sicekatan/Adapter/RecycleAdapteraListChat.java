package glory.sicekatan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import glory.sicekatan.ChatActivity;
import glory.sicekatan.R;


/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleAdapteraListChat extends RecyclerView.Adapter<RecycleViewHolderListChat> {


    LayoutInflater inflater;
    Context context;
    Intent i;
    public static List<String> list_idLS= new ArrayList();
    public static List<String> list_namaLs = new ArrayList();
    public static List<String> list_pesanLS = new ArrayList();
    public static List<String> list_pesan2LS = new ArrayList();
    public static List<String> list_fromIdLS = new ArrayList();
    public static List<String> list_toIdLS = new ArrayList();
    public static String namaCustomer;
    public static String tglCustomer;
    public static Stack stack_friendLS = new Stack();
    String key = "";
    Bitmap bitmap;

    String[] nama ={"SiCekatan","hasanudin","Seijuro"};
    public RecycleViewHolderListChat viewHolderListChat;

    public RecycleAdapteraListChat(final Context context) {

        this.context = context;
        inflater = LayoutInflater.from(context);
        Firebase.setAndroidContext(this.context);



    }


    @Override
    public RecycleViewHolderListChat onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_listchat, parent, false);
        //View v = inflater.inflate(R.layout.item_list,parent,false);
        /*RecycleViewHolderListChat*/ viewHolderListChat = new RecycleViewHolderListChat(view);
        return viewHolderListChat;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolderListChat holder, int position) {


        holder.txtNamaUser.setText(nama[position].toString());
        //holder.img_iconlistchat.setImageResource(R.drawable.akun);

        holder.txtNamaUser.setOnClickListener(clicklistener);
        holder.img_iconlistchat.setOnClickListener(clicklistener);

        holder.txtNamaUser.setTag(holder);
        holder.img_iconlistchat.setTag(holder);

    }

    View.OnClickListener clicklistener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            RecycleViewHolderListChat vHolder = (RecycleViewHolderListChat) v.getTag();
            int position = vHolder.getPosition();
            //Toast.makeText(context.getApplicationContext(), "Kunci Cusnya : "+Glist_dari_keyCus.get(position).toString(), Toast.LENGTH_SHORT).show();
            i = new Intent(context.getApplicationContext(), ChatActivity.class);
            context.startActivity(i);

        }
    };


    public int getItemCount() {

       // return list_fromIdLS == null ? 0 : list_fromIdLS.size();
        return nama.length;

    }




}
