package com.aluracursos.screenmatch.service;

import com.aluracursos.screenmatch.dto.EpisodioDTO;
import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.model.Categoria;
import com.aluracursos.screenmatch.model.Serie;
import com.aluracursos.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> obtenerTodasSeries() {
        return convierteDatos(repository.findAll());
    }

    public List<SerieDTO> obtenerTop5Series() {
        return convierteDatos(repository.findTop5ByOrderByEvaluacionDesc());
    }

    public List<SerieDTO> obtenerLanzamientosMasRecientes(){
        return convierteDatos(repository.lanzamientosMasRecientes());
    }

    public List<SerieDTO> convierteDatos(List<Serie> series){
        return series.stream()
                .map(serie -> new SerieDTO(serie.getId(), serie.getTitulo(),serie.getTotalTemporadas(), serie.getEvaluacion(), serie.getPoster(), serie.getGenero(), serie.getActores(), serie.getSinopsis()))
                .collect(Collectors.toList());

    }

    public SerieDTO obtenerPorId(Long id) {
        Optional<Serie> serieEncontrada = repository.findById(id);
        if (serieEncontrada.isPresent()){
            Serie serie = serieEncontrada.get();
            return  new SerieDTO(serie.getId(),serie.getTitulo(),serie.getTotalTemporadas(), serie.getEvaluacion(), serie.getPoster(), serie.getGenero(), serie.getActores(), serie.getSinopsis());
        }else {
            return null;
        }

    }

    public List<EpisodioDTO> obtenerTemporadasPorId(Long id) {
        Optional<Serie> serieEncontrada = repository.findById(id);
        if (serieEncontrada.isPresent()){
            Serie serie = serieEncontrada.get();
            return  serie.getEpisodios().stream().map(episodio -> new EpisodioDTO(episodio.getTemporada(), episodio.getTitulo(), episodio.getNumeroEpisodio())).collect(Collectors.toList());
        }else {
            return null;
        }
    }

    public List<EpisodioDTO> obtenerEpisodiosTemporada(Long id, int numeroTemporada) {
        return repository.obtenerTemporadaporNumero(id,numeroTemporada).stream().map(episodio -> new EpisodioDTO(episodio.getTemporada(), episodio.getTitulo(), episodio.getNumeroEpisodio())).collect(Collectors.toList());
    }

    public List<SerieDTO> obtenerSerieCategoria(String nombreCategoria) {
        Categoria categoria = Categoria.fromEspanol(nombreCategoria);
        return convierteDatos(repository.findByGenero(categoria));
    }
}
