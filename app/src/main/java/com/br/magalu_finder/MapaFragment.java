package com.br.magalu_finder;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.br.magalu_finder.DAO.FilialDAO;
import com.br.magalu_finder.Model.Filial;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

/**
 * Created by Yan Alves on 20/05/2017.
 * Classe responsável por criar o fragment para utilização do maps
 */

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        //Instancia do Google maps para mostrar conteudo no mapa
        getMapAsync(this);
    }

    //Método para construção do mapa
    @Override
    public void onMapReady(GoogleMap googleMap) {

        //Posição inicial
        LatLng posicaoUser = getCoordenada("Franca, Brasil");
        if (posicaoUser != null){

            //Método para especificar latitude e longitude, passando as coordenadas e nível de zoom inicial
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoUser, 11);

            //Método para definir o ponto do mapa
            googleMap.moveCamera(update);
        }
        //Buscar todos os locais cadastrados
        FilialDAO filialDAO = new FilialDAO(getContext());
        for (Filial filial : filialDAO.buscaFilial()){

            LatLng coordenada = getCoordenada(filial.getDesc_filial() + filial.getCep());
            if (coordenada != null){
                //Variavel para criação dos marcadores
                MarkerOptions marcador = new MarkerOptions();
                marcador.position(coordenada);

                //Informação que conterá o marcador
                marcador.title(filial.getDesc_filial() + "Coordenadas: " + coordenada.toString());
                marcador.snippet((filial.getDesc_filial()));

                //Adicionando o marcador ao mapa
                googleMap.addMarker(marcador);
            }
        }
        filialDAO.close();
    }

    private LatLng getCoordenada(String endereco){

        //Try para verificar se os resultados foram obtidos
        try {

            //Converte a cidade para latitude e longitude
            Geocoder geoCoder = new Geocoder(getContext());
            List<Address> resultados = geoCoder.getFromLocationName(endereco, 1);
            //Verificar se foram retonados resultados
            if (!resultados.isEmpty()){
                LatLng posicao = new LatLng(resultados.get(0).getLatitude(), resultados.get(0).getLongitude());
                return posicao;

            }
        } catch (IOException e){

            e.printStackTrace();
        }
        return null;
    }
}
