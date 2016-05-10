/*
 * Kingsgate Media Player
 * Copyright (C) 2016 Jon Burney (jon@version7.co.uk)
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
 *
 */
package jonburney.version7.kingsgatemediaplayer.Components;

import android.app.Activity;

import dagger.Component;
import jonburney.version7.kingsgatemediaplayer.Attributes.PerActivity;
import jonburney.version7.kingsgatemediaplayer.Fragments.VideoListTvFragment;
import jonburney.version7.kingsgatemediaplayer.Fragments.VideoListPhoneFragment;
import jonburney.version7.kingsgatemediaplayer.Modules.ActivityModule;
import jonburney.version7.kingsgatemediaplayer.Modules.DataProviderModule;
import jonburney.version7.kingsgatemediaplayer.Modules.FragmentModule;
import jonburney.version7.kingsgatemediaplayer.Modules.HttpModule;

/**
 * Created by jburney on 16/02/2016.
 */
@PerActivity
@Component (dependencies = IApplicationComponent.class, modules = {ActivityModule.class, FragmentModule.class, DataProviderModule.class, HttpModule.class})
public interface IActivityComponent {
    Activity activity();
    void inject(VideoListTvFragment videoListTvFragment);
    void inject(VideoListPhoneFragment videoListPhoneFragment);
}
