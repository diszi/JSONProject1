package d2.hu.jsonproject1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter  extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<String> list = new ArrayList<>();



    public MainAdapter(List<String> array){
        this.list.clear();
        this.list.addAll(array);
        this.notifyDataSetChanged();
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_row,parent,false);
        return new MainAdapter.MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        String item = list.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.company_name)
        TextView company_name_text;


        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

        public void bind(String item){
            company_name_text.setText(item);
        }
    }
}
