/*
 * Copyright (c) 2016 [zhiye.wei@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.smile.moment.presenter;

import com.android.volley.VolleyError;
import com.smile.moment.app.SmileApplication;
import com.smile.moment.model.LoadModel;
import com.smile.moment.model.entity.Jokes;
import com.smile.moment.model.impl.JokesModelImpl;
import com.smile.moment.ui.contract.JokesContract;
import com.smile.moment.utils.ToastUtil;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Smile Wei
 * @since 2016/6/8.
 */
public class JokesPresenter implements JokesContract.Presenter {
    private JokesContract.View view;
    private LoadModel loadModel;

    public void init(JokesContract.View view) {
        loadModel = new JokesModelImpl();
        this.view = checkNotNull(view, "JokesContract view cannot be null");
        this.view.initView();
    }

    @Override
    public void result() {
        ToastUtil.showSortToast(SmileApplication.getContext(), "result!!!!!!!!!");
    }

    @Override
    public void start(final boolean isLoad) {
        view.loading(isLoad);
        loadModel.load(new OnLoadListener<List<Jokes>>() {
            @Override
            public void onSuccess(List<Jokes> success) {
                view.showJokes(success, isLoad);
            }

            @Override
            public void onError(VolleyError error) {
                view.error(error);
            }

            @Override
            public void networkError() {
                view.networkError();
            }
        });

    }

    @Override
    public void start() {

    }
}
