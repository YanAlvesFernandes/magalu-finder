package com.br.magalu_finder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
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

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        //Inicializção do helper
        helper = new ProdutoHelper(this);

        Button btnFotoProduto = (Button)findViewById(R.id.btnFotoProduto);
        btnFotoProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File arquivoFoto = new File(caminhoFoto);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));
                startActivityForResult(intentCamera, CODIGO_CAMERA);
            }
        });

        //Botão reponsável para ir a listagem de produtos cadastradas
        Button btnIrProdutos = (Button) findViewById(R.id.btnIrProdutos);
        btnIrProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent listarProdutos = new Intent(FormularioProdutos.this, ListaProdutos.class);
                startActivity(listarProdutos);
            }
        });


        //Botão responsável por efetuar o cadastro
        Button btnCadastrarProdutos = (Button) findViewById(R.id.btnCadastrarProdutos);
        btnCadastrarProdutos.setOnClickListener(new View.OnClickListener() {
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

                Intent voltarProdutos = new Intent(FormularioProdutos.this, ListaProdutos.class);
                startActivity(voltarProdutos);


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
