package org.pokemon;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PokemonController {
    private Gson gson = new Gson();
    public ArrayList createPokedexData() throws IOException {
        String filePath = "src/main/resources/jsonFiles/pokemonList.json";
        String json = new String(Files.readAllBytes(Paths.get(filePath)));
        Type type = new TypeToken<ArrayList<Pokemon>>(){}.getType();
        ArrayList<Pokemon> list = gson.fromJson(json, type);
        return list;
    }

    public ArrayList createMegaPokemonData() throws IOException {
        String filePath = "src/main/resources/jsonFiles/megaPokemonList.json";
        String jsonMega = new String(Files.readAllBytes(Paths.get(filePath)));
        Type typeMega = new TypeToken<ArrayList<Pokemon>>(){}.getType();
        ArrayList<Pokemon> listMega = gson.fromJson(jsonMega, typeMega);
        return listMega;
    }
}
