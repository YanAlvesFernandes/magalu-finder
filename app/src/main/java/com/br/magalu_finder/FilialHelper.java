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


    private Filial filial;

    public FilialHelper(FormularioActivity activity) {

        desc_filial = (EditText) activity.findViewById(R.id.txtDesc_filial);
        cep = (EditText) activity.findViewById(R.id.txtCep);


        filial = new Filial();

    }

    public Filial pegaFilial() {

        filial.setDesc_filial(filial.getDesc_filial().toString());
        filial.setCep(cep.getText().toString());


        return filial;
    }
}
