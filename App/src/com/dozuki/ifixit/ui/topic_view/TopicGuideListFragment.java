package com.dozuki.ifixit.ui.topic_view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import com.actionbarsherlock.app.SherlockFragment;
import com.dozuki.ifixit.R;
import com.dozuki.ifixit.model.guide.GuideInfo;
import com.dozuki.ifixit.model.topic.TopicLeaf;
import com.dozuki.ifixit.ui.GuideListAdapter;
import com.dozuki.ifixit.ui.guide.view.GuideViewActivity;

public class TopicGuideListFragment extends SherlockFragment {

   public static final String GUIDEID = "guideid";
   protected static final String SAVED_TOPIC = "SAVED_TOPIC";

   private TopicLeaf mTopicLeaf;

   /**
    * Required for restoring fragments
    */
   public TopicGuideListFragment() {}

   public TopicGuideListFragment(TopicLeaf topicLeaf) {
      mTopicLeaf = topicLeaf;
   }

   @Override
   public void onCreate(Bundle savedState) {
      super.onCreate(savedState);

      if (savedState != null && mTopicLeaf == null) {
         mTopicLeaf = (TopicLeaf)savedState.getSerializable(SAVED_TOPIC);
      }
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.topic_guide_list, container, false);

      GridView gridView = (GridView)view.findViewById(R.id.guide_grid);

      gridView.setAdapter(new GuideListAdapter(getSherlockActivity(), mTopicLeaf.getGuides()));
      gridView.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> arg0, View view, int position,
          long id) {
            GuideInfo guide = mTopicLeaf.getGuides().get(position);
            Intent intent = new Intent(getSherlockActivity(), GuideViewActivity.class);

            intent.putExtra(GUIDEID, guide.mGuideid);
            startActivity(intent);
         }
      });

      return view;
   }

   @Override
   public void onSaveInstanceState(Bundle state) {
      state.putSerializable(SAVED_TOPIC, mTopicLeaf);
   }
}
