/**
 * Wire
 * Copyright (C) 2016 Wire Swiss GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.waz.zclient.calling

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.{TextView, LinearLayout}
import com.waz.threading.Threading
import com.waz.zclient.views.chathead.ChatheadView
import com.waz.zclient.{R, ViewHelper}

class HeaderLayoutAudio(val context: Context, val attrs: AttributeSet, val defStyleAttr: Int) extends LinearLayout(context, attrs, defStyleAttr) with ViewHelper {

  def this(context: Context, attrs: AttributeSet) = this(context, attrs, 0)

  def this(context: Context) =  this(context, null)

  lazy val nameView: TextView = findById(R.id.ttv__calling__header__name)
  lazy val subtitleView: TextView = findById(R.id.ttv__calling__header__subtitle)

  LayoutInflater.from(context).inflate(R.layout.calling_header_audio, this, true)
  setOrientation(LinearLayout.VERTICAL)

  val controller = inject[CurrentCallController]

  controller.subtitleText.on(Threading.Ui)(subtitleView.setText)

  controller.conversationName.on(Threading.Ui)(nameView.setText)
}

class HeaderLayoutVideo (val context: Context, val attrs: AttributeSet, val defStyleAttr: Int) extends LinearLayout(context, attrs, defStyleAttr) with ViewHelper {

  def this(context: Context, attrs: AttributeSet) = this(context, attrs, 0)

  def this(context: Context) = this(context, null)

  lazy val chathead: ChatheadView = findById(R.id.chv__other_user_chathead)
  lazy val nameView: TextView = findById(R.id.ttv__calling__header__name)
  lazy val subtitleView: TextView = findById(R.id.ttv__calling__header__subtitle)

  LayoutInflater.from(context).inflate(R.layout.calling_header_video, this, true)
  setOrientation(LinearLayout.HORIZONTAL)

  val controller = inject[CurrentCallController]

  controller.otherUser.on(Threading.Ui)(_.foreach(user => chathead.setUserId(user.id)))

  controller.subtitleText.on(Threading.Ui)(subtitleView.setText)

  controller.conversationName.on(Threading.Ui)(nameView.setText)
}
