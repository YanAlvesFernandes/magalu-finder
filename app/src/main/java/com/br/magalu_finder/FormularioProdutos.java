package com.br.magalu_finder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.br.magalu_finder.DAO.ProdutoDAO;
import com.br.magalu_finder.Model.Produto;

import java.io.File;

public class FormularioProdutos extends AppCompatActivity {

    public static final int CODIGO_CAMERA = 567;
    private ProdutoHelper helper;
    private String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_produtos);

        //Inicializção do helper
        helper = new ProdutoHelper(this);

        Button botaoFoto = (Button)findViewById(R.id.btnFotoProduto);
        botaoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File arquivoFoto = new File(caminhoFoto);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));
                startActivityForResult(intentCamera, CODIGO_CAMERA);
            }
        });

        //Botão reponsável para ir a listagem de filiais cadastradas
        Button btnIrFiliais = (Button) findViewById(R.id.btnIrFiliais);
        btnIrFiliais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent voltarFiliais = new Intent(FormularioProdutos.this, MainActivity.class);
                startActivity(voltarFiliais);
            }
        });

        //Botão responsável por efetuar o cadastro
        Button btnCadastrarFilial = (Button) findViewById(R.id.btnCadastrarFilial);
        btnCadastrarFilial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Se obtendo os dados cadastrados nos TextViews
                Produto produto = helper.pegaProduto();
                //Criação do objeto para acesso ao bando de dados
                ProdutoDAO dao = new ProdutoDAO(FormularioProdutos.this);
                //Inserindo os dasos e confirmação da inserção
                dao.insere(produto);
                Toast.makeText(FormularioProdutos.this, "Produto cadastrado com sucesso! " + produto.getDesc_produto(), Toast.LENGTH_SHORT).show();

                dao.close();
                finish();

                Intent voltarFiliais = new Intent(FormularioProdutos.this, MainActivity.class);
                startActivity(voltarFiliais);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == CODIGO_CAMERA) {
                //Abrir a foto
                helper.carregaImagem(caminhoFoto);
            }
        }
    }

}
