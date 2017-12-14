package com.br.magalu_finder;

import android.widget.EditText;

import com.br.magalu_finder.Model.Filial;

/**
 * Created by Yan Alves on 13/12/2017.
 * Classe auxiliar ao formulário
 */
public class FilialHelper {

    private final EditText desc_filial;
    private final EditText cep;
    private final EditText cidade;
    private final EditText bairro;

    private Filial filial;

    public FilialHelper(FormularioActivity activity) {

        desc_filial = (EditText) activity.findViewById(R.id.txtDesc_filial);
        cep = (EditText) activity.findViewById(R.id.txtCep);
        cidade = (EditText) activity.findViewById(R.id.txtCidade);
        bairro = (EditText) activity.findViewById(R.id.txtBairro);


        filial = new Filial();

    }

    public Filial pegaFilial() {

        filial.setDesc_filial(desc_filial.getText().toString());
        filial.setCep(cep.getText().toString());
        filial.setCidade(cidade.getText().toString());
        filial.setBairro(bairro.getText().toString());


        return filial;
    }
}
