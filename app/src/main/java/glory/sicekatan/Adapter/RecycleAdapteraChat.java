package glory.sicekatan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import glory.sicekatan.Kelas.SharedVariable;
import glory.sicekatan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleAdapteraChat extends RecyclerView.Adapter<RecycleViewHolderChat> {


    LayoutInflater inflater;
    Context context;
    Intent i;
    public static List<String> list_toId = new ArrayList();
    public static List<String> list_fromID= new ArrayList();
    public static List<String> list_pesan = new ArrayList();
    public static List<String> list_time = new ArrayList();
    public static List<String> list_nama = new ArrayList();
    String key = "";

    DatabaseReference ref;
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener fStateListener;

    String[] nama ={"Pesan 1","Pesan 2","Pesan 3"};

    public RecycleAdapteraChat(final Context context) {

        this.context = context;
        inflater = LayoutInflater.from(context);
        Firebase.setAndroidContext(this.context);
        FirebaseApp.initializeApp(context.getApplicationContext());
        ref = FirebaseDatabase.getInstance().getReference();
     //   Gref = new Firebase("https://appubblik-9c8ec.firebaseio.com/").child("chat").child(ChatKeCS.to_id);


    }


    @Override
    public RecycleViewHolderChat onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_chat_message, parent, false);
        //View v = inflater.inflate(R.layout.item_list,parent,false);
        RecycleViewHolderChat viewHolderChat = new RecycleViewHolderChat(view);
        return viewHolderChat;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(RecycleViewHolderChat holder, int position) {

        Resources res = context.getResources();

        holder.txtMessage.setText(nama[position].toString());
        //holder.contentWithBackground.setGravity(Gravity.LEFT);

        if (SharedVariable.check.equals("1")){
        holder.contentWithBackground.setBackground(res.getDrawable(R.drawable.out_message_bg));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.contentWithBackground.getLayoutParams();
        layoutParams.gravity = Gravity.LEFT;
        holder.contentWithBackground.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) holder.content.getLayoutParams();
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        holder.content.setLayoutParams(lp);
        layoutParams = (LinearLayout.LayoutParams) holder.txtMessage.getLayoutParams();
        layoutParams.gravity = Gravity.LEFT;
        holder.txtMessage.setLayoutParams(layoutParams);
        layoutParams = (LinearLayout.LayoutParams) holder.txtNamaSender.getLayoutParams();
        layoutParams.gravity = Gravity.LEFT;
        holder.txtNamaSender.setLayoutParams(layoutParams);

            holder.txtNamaSender.setText("Saya");
        }else {
            holder.contentWithBackground.setBackgroundResource(R.drawable.in_message_bg);

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.contentWithBackground.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            holder.contentWithBackground.setLayoutParams(layoutParams);

            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) holder.content.getLayoutParams();
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.content.setLayoutParams(lp);
            layoutParams = (LinearLayout.LayoutParams) holder.txtMessage.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            holder.txtMessage.setLayoutParams(layoutParams);

            layoutParams = (LinearLayout.LayoutParams) holder.txtNamaSender.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            holder.txtNamaSender.setLayoutParams(layoutParams);
            holder.txtNamaSender.setText("Dokter");
        }



        holder.txtNamaSender.setOnClickListener(clicklistener);
        holder.txtMessage.setOnClickListener(clicklistener);
        holder.contentWithBackground.setOnClickListener(clicklistener);
        holder.content.setOnClickListener(clicklistener);


        holder.txtNamaSender.setTag(holder);
        holder.txtMessage.setTag(holder);
        holder.contentWithBackground.setTag(holder);
        holder.content.setTag(holder);

    }

    View.OnClickListener clicklistener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            RecycleViewHolderChat vHolder = (RecycleViewHolderChat) v.getTag();
            int position = vHolder.getPosition();
           // Toast.makeText(context.getApplicationContext(), "l_Nama : "+list_nama.get(position).toString(), Toast.LENGTH_SHORT).show();


        }
    };


    public int getItemCount() {

      //  return list_pesan == null ? 0 : list_pesan.size();
        return nama.length;

    }




}
