package com.br.magalu_finder.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.br.magalu_finder.Model.Filial;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yan Alves on 13/12/2017.
 */

public class FilialDAO extends SQLiteOpenHelper {

    //Nome do banco e versão que deve ser incrementada após alterações do BD
    public FilialDAO(Context context) {
        super(context, "magalu_finder", null, 3);
    }

    //Criação da tabela Filial
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Filial (id INTEGER PRIMARY KEY, desc_filial TEXT NOT NULL, cep TEXT NOT NULL, cidade TEXT NOT NULL, uf TEXT NOT NULL, bairro TEXT NOT NULL);";
        db.execSQL(sql);
    }

    //Método chamado sempre que houver alguma alteração no banco
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Filial");
        onCreate(db);

    }

    //Método responsável pela inserção no banco de dados
    public void insere(Filial filial) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosFilial(filial);


        db.insert("Filial", null, dados);

    }

    //Criação do ContentValues que serão inseridos no banco
    @NonNull
    private ContentValues pegaDadosFilial(Filial filial) {

        ContentValues dados = new ContentValues();
        dados.put("desc_filial", filial.getDesc_filial().toUpperCase());
        dados.put("cep", filial.getCep().toUpperCase());
        dados.put("cidade", filial.getCidade().toUpperCase());
        dados.put("uf", filial.getUf().toUpperCase());
        dados.put("bairro", filial.getBairro().toUpperCase());
        return dados;
    }


    //Busca no banco
    public List<Filial> buscaFilial() {
        String sql = "SELECT * FROM Filial";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Filial>filiais = new ArrayList<Filial>();
        while (c.moveToNext()){

            Filial filial = new Filial();
            filial.setId(c.getLong(c.getColumnIndex("id")));
            filial.setDesc_filial(c.getString(c.getColumnIndex("desc_filial")));
            filial.setCep(c.getString(c.getColumnIndex("cep")));
            filial.setCidade(c.getString(c.getColumnIndex("cidade")));
            filial.setUf(c.getString(c.getColumnIndex("uf")));
            filial.setBairro(c.getString(c.getColumnIndex("bairro")));
            filiais.add(filial);
        }
        c.close();

        return filiais;
    }

    //Função responsável por deletar ítens do banco após busca po ID
    public void deleta(Filial filial) {

        SQLiteDatabase db  = getWritableDatabase();
        String[] params = {filial.getId().toString()};
        db.delete("Filial", "id = ?", params);

    }
}
