package io.github.changjiashuai.kotlindemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.github.changjiashuai.kotlindemo.models.Todo;
import io.realm.Realm;
import io.realm.RealmResults;


public class TodosFragment extends Fragment implements TodoAdapter.TodoItemClickListener {

    @Bind(R.id.todos_recycler_view) protected RealmRecyclerView rv;
    private Realm realm;

    public static TodosFragment newInstance() {
        return new TodosFragment();
    }

    public TodosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_todos, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        realm = Realm.getDefaultInstance();

    }

    @Override
    public void onResume() {
        super.onResume();
        RealmResults<Todo> todos = realm.where(Todo.class).findAll();
        TodoAdapter adapter = new TodoAdapter(getActivity(), todos, true, true, this);
        rv.setAdapter(adapter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onTodoClick(View caller, Todo task) {
        EditFragment editFragment = EditFragment.Companion.newInstance(task.getId());
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, editFragment, editFragment.getClass().getSimpleName())
                .addToBackStack(editFragment.getClass().getSimpleName())
                .commit();
    }
}
