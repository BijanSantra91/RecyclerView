package com.example.bijan.recyclerviewex1;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    EditText editText1, editText2;
    Button button;
    RecyclerView recyclerView;
    ArrayList<Movie> arrayList;
    MyAdapter myAdapter;
    int count = 0;

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //load row.xml & pass row.xml to below viewholder
            View v = getActivity().getLayoutInflater().inflate(R.layout.row, null);
            ViewHolder viewHolder = new ViewHolder(v);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Movie movie = arrayList.get(position);

            holder.textView1.setText(movie.getSno());
            holder.textView2.setText(movie.getActor());
            holder.textView3.setText(movie.getMovie());
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView textView1, textView2, textView3;

            public ViewHolder(View itemView) {
                super(itemView);

                textView1 = (TextView) itemView.findViewById(R.id.text1);
                textView2 = (TextView) itemView.findViewById(R.id.text2);
                textView3 = (TextView) itemView.findViewById(R.id.text3);
            }
        }
    }

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        editText1 = (EditText) v.findViewById(R.id.edittext1);
        editText2 = (EditText) v.findViewById(R.id.edittext2);
        button = (Button) v.findViewById(R.id.button);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        arrayList = new ArrayList<Movie>();
        myAdapter = new MyAdapter();

        //creating layoutmanager true for arabic country
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//
//        recyclerView.setLayoutManager(linearLayoutManager);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);

        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(myAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;

                String actor = editText1.getText().toString();
                String movie = editText2.getText().toString();

                Movie mymovie = new Movie();

                mymovie.setSno(""+count);
                mymovie.setActor(actor);
                mymovie.setMovie(movie);

                arrayList.add(mymovie);
                myAdapter.notifyItemInserted(count-1);

                editText1.setText("");
                editText2.setText("");
                editText1.requestFocus();

            }
        });

        return v;
    }
}
