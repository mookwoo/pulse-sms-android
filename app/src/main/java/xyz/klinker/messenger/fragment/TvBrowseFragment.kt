/*
 * Copyright (C) 2017 Luke Klinker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.klinker.messenger.fragment

import android.database.Cursor
import android.support.v17.leanback.widget.ArrayObjectAdapter
import android.support.v17.leanback.widget.HeaderItem
import android.support.v17.leanback.widget.ListRow

import com.sgottard.sofa.support.BrowseSupportFragment

import xyz.klinker.messenger.shared.data.DataSource
import xyz.klinker.messenger.shared.data.model.Conversation
import xyz.klinker.messenger.shared.util.CursorUtil

/**
 * A fragment that displays messages on the right side of the screen and conversations on the left
 * side.
 */
class TvBrowseFragment : BrowseSupportFragment() {

    override fun onStart() {
        super.onStart()

        val adapter = ArrayObjectAdapter()
        DataSource.getUnarchivedConversationsAsList(activity)
                .forEach {
                    val customFragmentAdapter = ArrayObjectAdapter()
                    customFragmentAdapter.add(MessageListFragment.newInstance(it))

                    val customFragmentRow = ListRow(HeaderItem(it.title), customFragmentAdapter)
                    adapter.add(customFragmentRow)
                }

        setAdapter(adapter)
    }

    companion object {

        fun newInstance(): TvBrowseFragment {
            return TvBrowseFragment()
        }
    }

}
