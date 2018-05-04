package com.codegene.femicodes.offlinerestaurantapp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codegene.femicodes.offlinerestaurantapp.R;
import com.codegene.femicodes.offlinerestaurantapp.db.AppDatabase;
import com.codegene.femicodes.offlinerestaurantapp.model.Menu;
import com.codegene.femicodes.offlinerestaurantapp.ui.activity.MealsActivity;
import com.codegene.femicodes.offlinerestaurantapp.util.CommonUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuCategoryFragment extends Fragment {

    @BindView(R.id.rv_menu_list)
    RecyclerView mMenuRecyclerView;
    MenuAdapter mMenuAdapter;

    List<Menu> mMenuList = new ArrayList<>();


    public MenuCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_menu_category, container, false);
        ButterKnife.bind(this, v);

        Executors.newSingleThreadExecutor().execute(() -> {

            mMenuList = AppDatabase.getDatabase(getContext()).mMenuDao().getAllMenu();
            getActivity().runOnUiThread(new Thread(this::setupRecyclerView));

        });
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Menu");
    }

    public void setupRecyclerView() {
        mMenuRecyclerView.setLayoutManager(new GridLayoutManager(mMenuRecyclerView.getContext(), 2));
        mMenuRecyclerView.setHasFixedSize(true);
        mMenuAdapter = new MenuAdapter(mMenuRecyclerView.getContext(), mMenuList);
        mMenuRecyclerView.setAdapter(mMenuAdapter);
        mMenuAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_populate_database)
    public void populateDatabase() {

        CommonUtil.populateMenuDatabase(getContext());
        setupRecyclerView();
    }

    public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

        Context mContext;
        List<Menu> mMenuList;

        public MenuAdapter(Context context, List<Menu> menuList) {
            mContext = context;
            mMenuList = menuList;
        }

        @Override
        public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_row, parent, false);
            return new MenuViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MenuViewHolder holder, int position) {

            Picasso.get().load(mMenuList.get(position).getImage()).into(holder.mMenuImage);
            holder.mMenuName.setText(mMenuList.get(position).getName());


        }

        @Override
        public int getItemCount() {
            return mMenuList.size();
        }


        public class MenuViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.iv_menu_image)
            ImageView mMenuImage;

            @BindView(R.id.tv_menu_name)
            TextView mMenuName;

            public MenuViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);

                itemView.setOnClickListener(view -> {

                    Menu menu = mMenuList.get(getAdapterPosition());
                    Log.d("status: ", "id " + menu.getId());

                    Intent intent = new Intent(mContext, MealsActivity.class);
                    intent.putExtra("menuName", menu.getName());
                    intent.putExtra("menuId", menu.getId());
                    startActivity(intent);


                });


            }
        }

    }

}
