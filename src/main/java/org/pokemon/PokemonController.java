package org.pokemon;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class PokemonController {
    private Gson gson = new Gson();
    public ArrayList createPokedexData() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/jsonFiles/pokemonList.json");
        String json = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        Type type = new TypeToken<ArrayList<Pokemon>>() {}.getType();
        ArrayList<Pokemon> list = gson.fromJson(json, type);
        return list;
    }

    public ArrayList createMegaPokemonData() throws IOException {
        InputStream inputStreamMega = getClass().getResourceAsStream("/jsonFiles/megaPokemonList.json");
        String jsonMega = new BufferedReader(new InputStreamReader(inputStreamMega)).lines().collect(Collectors.joining("\n"));
        Type typeMega = new TypeToken<ArrayList<Pokemon>>() {}.getType();
        ArrayList<Pokemon> listMega = gson.fromJson(jsonMega, typeMega);
        return listMega;
    }
}
