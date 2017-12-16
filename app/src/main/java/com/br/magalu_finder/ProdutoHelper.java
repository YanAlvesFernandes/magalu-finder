package com.br.magalu_finder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;

import com.br.magalu_finder.Model.Produto;

/**
 * Created by Yan Alves on 16/12/2017.
 */

public class ProdutoHelper {

    private final EditText desc_produto;
    private final EditText valor_produto;
    private final ImageView campoFoto;

    private Produto produto;

    public ProdutoHelper(FormularioProdutos activity){

        desc_produto = (EditText)activity.findViewById(R.id.txtDescProduto);
        valor_produto = (EditText) activity.findViewById(R.id.txtValorProduto);
        campoFoto = (ImageView) activity.findViewById(R.id.formulario_foto);

        produto = new Produto();

    }

    public Produto pegaProduto() {

        produto.setDesc_produto(desc_produto.getText().toString());
        produto.setValor_produto(Double.valueOf(String.valueOf(valor_produto.getText())));
        produto.setCaminhoFoto((String) campoFoto.getTag());
        return produto;
    }

    public void carregaImagem(String caminhoFoto) {

        if (caminhoFoto != null) {

            //Abrir a foto
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = bitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(caminhoFoto);
        }
    }
}
