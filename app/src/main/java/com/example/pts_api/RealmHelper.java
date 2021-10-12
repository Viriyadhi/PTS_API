package com.example.pts_api;

import android.util.Log;
import java.util.List;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public  RealmHelper(Realm realm){
        this.realm = realm;
    }

    // untuk menyimpan data
    public void save (final MovieModel movieModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(MovieModel.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    movieModel.setId(nextId);
                    MovieModel model = realm.copyToRealm(movieModel);
                }else{
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }

    // untuk memanggil semua data
    public List<MovieModel> getAllMovie(){
        RealmResults<MovieModel> results = realm.where(MovieModel.class).findAll();
        return results;
    }

//    // untuk meng-update data
//    public void update(final Integer id, final Integer nim, final String nama){
//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                MovieModel model = realm.where(MovieModel.class)
//                        .equalTo("id", id)
//                        .findFirst();
//                model.setNim(nim);
//                model.setNama(nama);
//            }
//        }, new Realm.Transaction.OnSuccess() {
//            @Override
//            public void onSuccess() {
//                Log.e("pppp", "onSuccess: Update Successfully");
//            }
//        }, new Realm.Transaction.OnError() {
//            @Override
//            public void onError(Throwable error) {
//                error.printStackTrace();
//            }
//        });
//    }

    // untuk menghapus data
    public void delete(Integer id){
        final RealmResults<MovieModel> model = realm.where(MovieModel.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }

}
