package app.samniashvili.irakli.httppractice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by irakli on 12/18/2017.
 */

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
  private Context context;
  private List<MyData> my_data;

    public CustomAdapter(Context context, List<MyData> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
   View itemView = LayoutInflater.from(parent.getContext()).inflate( R.layout.card,parent,false );
   return  new ViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
holder.name.setText( my_data.get( position ).getName() );
    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public ViewHolder(View itemView) {
            super( itemView );
            name = itemView.findViewById( R.id.name );
        }
    }
}
