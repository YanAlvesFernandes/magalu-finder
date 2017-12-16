package com.br.magalu_finder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.br.magalu_finder.DAO.FilialDAO;
import com.br.magalu_finder.Model.Filial;
import com.google.android.gms.common.api.GoogleApiClient;

public class FormularioActivity extends AppCompatActivity {

    private FilialHelper helper;
    private GoogleApiClient googleApiClient;
    private FormularioActivity formularioActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FilialHelper(this);

        //Botão responsável por efetuar o cadastro
        Button btnCadastrarFilial = (Button) findViewById(R.id.btnCadastrarFilial);
        btnCadastrarFilial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Se obtendo os dados cadastrados nos TextViews
                Filial filial = helper.pegaFilial();
                //Criação do objeto para acesso ao bando de dados
                FilialDAO dao = new FilialDAO(FormularioActivity.this);
                //Inserindo os dasos e confirmação da inserção
                dao.insere(filial);
                Toast.makeText(FormularioActivity.this, "Filial cadastrado com sucesso! " + filial.getDesc_filial(), Toast.LENGTH_SHORT).show();

                dao.close();
                finish();

                Intent voltarFiliais = new Intent(FormularioActivity.this, MainActivity.class);
                startActivity(voltarFiliais);


            }
        });

        //Botão reponsável para ir a listagem de filiais cadastradas
        Button btnIrFiliais = (Button) findViewById(R.id.btnIrFiliais);
        btnIrFiliais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent voltarFiliais = new Intent(FormularioActivity.this, MainActivity.class);
                startActivity(voltarFiliais);
            }
        });

    }
}
