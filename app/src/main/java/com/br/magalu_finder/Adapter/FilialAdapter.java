package com.br.magalu_finder.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.br.magalu_finder.Model.Filial;
import com.br.magalu_finder.R;

import java.util.List;

/**
 * Created by Yan Alves on 13/12/2017.
 * Classe responsável pelo layout da listview com o arquivo list_item.xml
 */

public class FilialAdapter extends BaseAdapter {

    private final List<Filial> filiais;
    private final Context context;

    public FilialAdapter(Context context, List<Filial> filiais) {
        this.context = context;
        this.filiais = filiais;
    }

    @Override
    public int getCount() {
        return filiais.size();
    }

    @Override
    public Object getItem(int position) {
        return filiais.get(position);
    }

    @Override
    public long getItemId(int position) {
        return filiais.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Filial filial = filiais.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if (view == null){

            view = inflater.inflate(R.layout.list_item, parent, false);

        }

        TextView campoId = (TextView) view.findViewById(R.id.item_id);
        campoId.setText(filial.getId().toString());

        TextView campoDesc = (TextView)view.findViewById(R.id.item_desc_filial);
        campoDesc.setText(filial.getDesc_filial());

/*        TextView campoCEP = (TextView)view.findViewById(R.id.item_cep);
        campoCEP.setText(filial.getCep());*/


        TextView campoCidade = (TextView)view.findViewById(R.id.item_cidade);
        campoCidade.setText(filial.getCidade());

        TextView campoUf = (TextView)view.findViewById(R.id.item_uf);
        campoUf.setText(filial.getUf());

        TextView campoBairro = (TextView)view.findViewById(R.id.item_bairro);
        campoBairro.setText(filial.getBairro());



        return view;
    }
}
