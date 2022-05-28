package dz.phamtuanvan.custom_rv_sqlite;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.window.SplashScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>{
    ArrayList<Student> studentArrayList;
    Context context;
    public StudentAdapter(ArrayList<Student> studentArrayList, Context context) {
        this.studentArrayList = studentArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout .item_view,parent,false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvId.setText(studentArrayList.get(position).getId()+"");
        holder.tvName.setText(studentArrayList.get(position).getName());
        holder.tvPhone.setText(studentArrayList.get(position).getNumber());
        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
            alertDialog.setTitle("Your title");
            alertDialog.setMessage("your message ");
            alertDialog.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialog.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // DO SOMETHING HERE
                    DataBase dataBase = new DataBase(view.getContext());
                Boolean checkDeleteData = dataBase.deleteData(studentArrayList.get(position).getId());
                if (checkDeleteData == true){
                    Toast.makeText(view.getContext(),"Delete Completed",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else {
                    Toast.makeText(view.getContext(), "Delete Failed",Toast.LENGTH_SHORT).show();
                }
            }


            });

            AlertDialog dialog = alertDialog.create();

            dialog.show();
            }
        });
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
            alertDialog.setTitle("Your title");
            alertDialog.setMessage("your message ");
            alertDialog.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialog.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // DO SOMETHING HERE
                    DataBase dataBase = new DataBase(view.getContext());
                Boolean checkDeleteData = dataBase.deleteData(holder.getAdapterPosition());
                if (checkDeleteData == true){
                    Toast.makeText(view.getContext(),"Delete Completed",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(view.getContext(), "Delete Failed",Toast.LENGTH_SHORT).show();
                }
            }


            });

            AlertDialog dialog = alertDialog.create();

            dialog.show();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnCreateContextMenuListener*/ {
        TextView tvName,tvPhone,tvId;
        private RelativeLayout layout_item;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            layout_item = itemView.findViewById(R.id.layout_item);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhone = itemView.findViewById(R.id.tv_phone);
        }

        /*@Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Confirm");
            contextMenu.add(getAdapterPosition(),101,0,"Edit");
            contextMenu.add(getAdapterPosition(),102,1,"Delete");
        }*/
    }
}
