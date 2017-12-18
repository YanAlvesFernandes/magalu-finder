package com.br.magalu_finder.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.magalu_finder.Model.Produto;
import com.br.magalu_finder.R;

import java.util.List;

/**
 * Created by Yan Alves on 14/12/2017.
 */

public class ProdutoAdapter extends BaseAdapter {

    private final List<Produto> produtos;
    private final Context context;

    public ProdutoAdapter(Context context, List<Produto> produtos) {
        this.context = context;
        this.produtos = produtos;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return produtos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Produto produto = produtos.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if (view == null){

            view = inflater.inflate(R.layout.list_item_produto, parent, false);

        }

        TextView campoId = (TextView) view.findViewById(R.id.item_id_produto);
        campoId.setText(produto.getId().toString());

        TextView campoDescProduto = (TextView)view.findViewById(R.id.item_desc_produto);
        campoDescProduto.setText(produto.getDesc_produto());

        TextView campoValorProduto = (TextView)view.findViewById(R.id.item_valor_produto);
        campoValorProduto.setText(produto.getValor_produto().toString());

        ImageView campoFoto = (ImageView) view.findViewById(R.id.item_foto);
        String caminhoFoto = produto.getCaminhoFoto();
        if (caminhoFoto != null) {

            //Abrir a foto
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            //Reduzir o tamanho
            Bitmap bitmapReduzido = bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        return view;
    }
}
