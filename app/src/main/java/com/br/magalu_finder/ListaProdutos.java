    package com.br.magalu_finder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.br.magalu_finder.Adapter.ProdutoAdapter;
import com.br.magalu_finder.DAO.ProdutoDAO;
import com.br.magalu_finder.Model.Produto;

import java.util.List;

    public class ListaProdutos extends AppCompatActivity {

        private ListView listaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        listaProdutos = (ListView) findViewById(R.id.listaProdutos);

        //Ir para cadastro de produtos
        Button irParaCadastro = (Button) findViewById(R.id.btnIrFormularioProdutos);
        irParaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiProFormulario = new Intent(ListaProdutos.this, FormularioProdutos.class);
                startActivity(intentVaiProFormulario);
            }
        });

        //Para que o menu de contexto possa ser criado
        registerForContextMenu(listaProdutos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    //Criação do menu superior
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Inflater para chamar o mapa
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menumainactivity, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //Carregar a lista com os produtos cadastrados pelo ususario
    public void carregaLista() {
        ProdutoDAO dao = new ProdutoDAO(this);
        List<Produto> produtos = dao.buscaProduto();
        dao.close();

        ProdutoAdapter adapter = new ProdutoAdapter(this, produtos);
        listaProdutos.setAdapter(adapter);
    }

    //Criação dos ítens do menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Selecionar o ID do item do menu
        switch (item.getItemId()){

            case R.id.item_menu_mapa:
                //Intent para mostar a mapsactivity
                Intent goToMaps = new Intent(ListaProdutos.this, MapsActivity.class);
                startActivity(goToMaps);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //Menu de contexto da listview
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Produto produto = (Produto) listaProdutos.getItemAtPosition(info.position);

        MenuItem deletar =  menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                //AlertDialog para verificar se o usuario deseja realmente deletear a filial
                AlertDialog.Builder alertDeletarLocal = new AlertDialog.Builder(ListaProdutos.this);
                alertDeletarLocal.setMessage("Deseja realmente delear o Produto?");
                alertDeletarLocal.setTitle("Local: " + produto.getId().toString());
                alertDeletarLocal.setPositiveButton("Sim, quero removê-lo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ProdutoDAO dao = new ProdutoDAO(ListaProdutos.this);
                        dao.deleta(produto);
                        dao.close();
                        Toast.makeText(getApplicationContext(), "Produto removida com sucesso!  ID: " + produto.getId().toString(), Toast.LENGTH_SHORT).show();
                        carregaLista();

                    }
                });

                alertDeletarLocal.setNegativeButton("Ainda não", null);
                alertDeletarLocal.show();

                return false;
            }
        });

        MenuItem produtos =  menu.add("Lista filiais com o produto");
        produtos.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


                Intent goToFiliais = new Intent(ListaProdutos.this, MainActivity.class);
                startActivity(goToFiliais);


                return false;
            }
        });
    }


}
