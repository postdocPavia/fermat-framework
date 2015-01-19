package com.bitdubai.smartwallet.ui.os.android.app.subapp.wallet_factory.version_1.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitdubai.smartwallet.R;
import com.bitdubai.smartwallet.ui.os.android.app.common.version_1.classes.MyApplication;

/**
 * Created by Natalia on 19/12/2014.
 */
public class DrawersFragment extends Fragment {
    private static final String ARG_POSITION = "position";

    View rootView;
    ExpandableListView lv;
    private String[] item;
    private String[][] drawer_item;



    public static DrawersFragment newInstance(int position) {
        DrawersFragment f = new DrawersFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        item = new String[]{
                getString(R.string.title_section2),
                getString(R.string.title_section3),
                getString(R.string.title_section4),
                getString(R.string.title_section5),
                getString(R.string.title_section6),
                getString(R.string.title_section7),
                getString(R.string.title_section8),
                getString(R.string.title_section9),
                getString(R.string.title_section10),
                getString(R.string.title_section11),
        };

        drawer_item = new String[][]{

                { getString(R.string.title_section2)},
                { getString(R.string.title_section3)},
                { getString(R.string.title_section4)},
                { getString(R.string.title_section5)},
                { getString(R.string.title_section6)},
                { getString(R.string.title_section7)},
                { getString(R.string.title_section8)},
                { getString(R.string.title_section9)},
                { getString(R.string.title_section10)},
                { getString(R.string.title_section11)},
        };

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.wallet_factory_fragment_inflater, container, false);



        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv = (ExpandableListView) view.findViewById(R.id.expListView);
        lv.setAdapter(new ExpandableListAdapter(item, drawer_item));
        lv.setGroupIndicator(null);

        lv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
/*
                Intent intent;
                intent = new Intent(getActivity(), SentDetailActivity.class);
                startActivity(intent);*/
                return false;
            }
        });

        lv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
/*
                if (groupPosition == 0) {
                    Intent intent;
                    intent = new Intent(getActivity(), SendToNewContactActivity.class);
                    startActivity(intent);
                    return false;
                }
                else*/
                {
                    return false;
                }
            }
        });


    }

    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private final LayoutInflater inf;
        private String[] contacts;
        private String[][] transactions;

        public ExpandableListAdapter(String[] contacts, String[][] transactions) {
            this.contacts = contacts;
            this.transactions = transactions;
            inf = LayoutInflater.from(getActivity());
        }


        @Override
        public int getGroupCount() {
            return contacts.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return transactions[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return contacts[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return transactions[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            ViewHolder item;
            ImageView profile_picture;


            //*** Seguramente por una cuestion de performance lo hacia asi, yo lo saque para que ande el prototippo
            // if (convertView == null) {
                convertView = inf.inflate(R.layout.wallet_factory_drawer_detail_fragment, parent, false);

                profile_picture = (ImageView) convertView.findViewById(R.id.icon);
                switch (groupPosition)
                {
                    case 0:
                        profile_picture.setImageResource(R.drawable.ic_action_user_grey);
                        break;
                    case 1:
                        profile_picture.setImageResource(R.drawable.ic_action_accounts_grey);
                        break;
                    case 2:
                        profile_picture.setImageResource(R.drawable.ic_action_bank_grey);
                        break;
                    case 3:
                        profile_picture.setImageResource(R.drawable.ic_action_coupon_grey);
                        break;
                    case 4:
                        profile_picture.setImageResource(R.drawable.ic_action_discount_grey);
                        break;
                    case 5:
                        profile_picture.setImageResource(R.drawable.ic_action_voucher_grey);
                        break;
                    case 6:
                        profile_picture.setImageResource(R.drawable.ic_action_gift_card_grey);
                        break;
                    case 7:
                        profile_picture.setImageResource(R.drawable.ic_action_clone_grey);
                        break;
                    case 8:
                        profile_picture.setImageResource(R.drawable.ic_action_child_grey);
                        break;
                    case 9:
                        profile_picture.setImageResource(R.drawable.ic_action_exit_grey);
                        break;
                }


                item = new ViewHolder();
                item.text = (TextView) convertView.findViewById(R.id.description);
                item.text.setTypeface(MyApplication.getDefaultTypeface());
                item.text.setText(drawer_item[groupPosition][childPosition].toString());

            return convertView;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            ViewHolder Item;
            //*** Seguramente por una cuestion de performance lo hacia asi, yo lo saque para que ande el prototippo
                // if (convertView == null) {
                    convertView = inf.inflate(R.layout.wallet_factory_drawer_header_fragment, parent, false);

                    Item = new ViewHolder();
                    Item.text = (TextView) convertView.findViewById(R.id.items);
                    Item.text.setTypeface(MyApplication.getDefaultTypeface());
                    Item.text.setText(item[groupPosition].toString());

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }


        private class ViewHolder {
            TextView text;
        }
    }



}


