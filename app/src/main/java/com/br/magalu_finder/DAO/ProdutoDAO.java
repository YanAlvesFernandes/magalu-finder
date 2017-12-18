package com.br.magalu_finder.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.br.magalu_finder.Model.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yan Alves on 14/12/2017.
 */

public class ProdutoDAO extends SQLiteOpenHelper {

    //Nome do banco e versão que deve ser incrementada após alterações do BD
    public ProdutoDAO(Context context) {
        super(context, "magalu_finder", null, 2);
    }

    //Criação da tabela Produto
    @Override
    public void onCreate(SQLiteDatabase db) {

        //db = openOrCreateDatabase("magalu_finder", null);
        String sql = "CREATE TABLE IF NOT EXISTS Produto (id INTEGER PRIMARY KEY, desc_produto TEXT NOT NULL, valor_produto REAL NOT NULL, caminhoFoto TEXT NOT NULL);";
        db.execSQL(sql);
    }

    //Método chamado sempre que houver alguma alteração no banco
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Produto");
        onCreate(db);

    }

    //Método responsável pela inserção no banco de dados
    public void insere(Produto produto) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosProduto(produto);


        db.insert("Produto", null, dados);

    }

    //Criação do ContentValues que serão inseridos no banco
    @NonNull
    private ContentValues pegaDadosProduto(Produto produto) {

        ContentValues dados = new ContentValues();
        dados.put("desc_produto", produto.getDesc_produto().toUpperCase());
        dados.put("valor_produto", produto.getValor_produto());
        dados.put("caminhoFoto", produto.getCaminhoFoto());
        return dados;
    }

    //Busca no banco
    public List<Produto> buscaProduto() {
        String sql = "SELECT * FROM Produto";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Produto>produtos = new ArrayList<Produto>();
        while (c.moveToNext()){

            Produto produto = new Produto();
            produto.setId(c.getLong(c.getColumnIndex("id")));
            produto.setDesc_produto(c.getString(c.getColumnIndex("desc_produto")));
            produto.setValor_produto(c.getDouble(c.getColumnIndex("valor_produto")));
            produto.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));
            produtos.add(produto);
        }
        c.close();

        return produtos;
    }


    //Função responsável por deletar ítens do banco após busca po ID
    public void deleta(Produto produto) {

        SQLiteDatabase db  = getWritableDatabase();
        String[] params = {produto.getId().toString()};
        db.delete("Produto", "id = ?", params);

    }
}
