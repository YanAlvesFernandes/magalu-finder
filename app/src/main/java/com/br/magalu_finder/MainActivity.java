package com.br.magalu_finder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.br.magalu_finder.Adapter.FilialAdapter;
import com.br.magalu_finder.DAO.FilialDAO;
import com.br.magalu_finder.Model.Filial;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listaFiliais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        //Verificação do login com Facebook
        if (AccessToken.getCurrentAccessToken() == null){
            goLoginScreen();

        }


        listaFiliais = (ListView) findViewById(R.id.listaFiliais);

        //Ir para cadastro da filial
        Button irParaCadastro = (Button) findViewById(R.id.btnIrFormulario);
        irParaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiProFormulario = new Intent(MainActivity.this, FormularioActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        //Para que o menu de contexto possa ser criado
        registerForContextMenu(listaFiliais);

    }

    //Carregar a lista com os locais cadastrados pelo ususario
    public void carregaLista() {
        FilialDAO dao = new FilialDAO(this);
        List<Filial> filiais = dao.buscaFilial();
        dao.close();

        FilialAdapter adapter = new FilialAdapter(this, filiais);
        listaFiliais.setAdapter(adapter);
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

    //Criação dos ítens do menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Selecionar o ID do item do menu
        switch (item.getItemId()){

            case R.id.item_menu_mapa:
                //Intent para mostar a mapsactivity
                Intent goToMaps = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(goToMaps);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //Menu de contexto da listview
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Filial filial = (Filial) listaFiliais.getItemAtPosition(info.position);

        MenuItem deletar =  menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                //AlertDialog para verificar se o usuario deseja realmente deletear a filial
                AlertDialog.Builder alertDeletarLocal = new AlertDialog.Builder(MainActivity.this);
                alertDeletarLocal.setMessage("Deseja realmente delear a filial?");
                alertDeletarLocal.setTitle("Local: " + filial.getCidade().toString() + " " + filial.getBairro());
                alertDeletarLocal.setPositiveButton("Sim, quero removê-la", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FilialDAO dao = new FilialDAO(MainActivity.this);
                        dao.deleta(filial);
                        dao.close();
                        Toast.makeText(getApplicationContext(), "Filial removida com sucesso!  ID: " + filial.getId().toString(), Toast.LENGTH_SHORT).show();
                        carregaLista();

                    }
                });

                alertDeletarLocal.setNegativeButton("Ainda não", null);
                alertDeletarLocal.show();

                return false;
            }
        });

        MenuItem produtos =  menu.add("Produtos");
        produtos.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


            Intent goToProducts = new Intent(MainActivity.this, FormularioProdutos.class);
            startActivity(goToProducts);


                return false;
                }
        });
    }

    private void goLoginScreen() {

        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout(View view) {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }
}
