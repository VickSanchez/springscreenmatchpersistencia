package com.aluracursos.screenmatch.controllers;

import com.aluracursos.screenmatch.dto.EpisodioDTO;
import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.model.Episodio;
import com.aluracursos.screenmatch.model.Serie;
import com.aluracursos.screenmatch.repository.SerieRepository;
import com.aluracursos.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/series") // endpoint base a la que se le va a encadenar las url de abajo
public class SerieController {

    @Autowired
    private SerieService servicio;


    @GetMapping() // Metodo Http mas la ruta
    public List<SerieDTO> obtenerTodasSeries() {
        return servicio.obtenerTodasSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obtenerTop5Series() {
        return servicio.obtenerTop5Series();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDTO> obtenerLanzamientosMasRecientes() {
        return servicio.obtenerLanzamientosMasRecientes();
    }

    @GetMapping("/{id}")
    public SerieDTO obtenerPorId(@PathVariable Long id) {
        return servicio.obtenerPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obtenerTemporadasPorId(@PathVariable Long id) {
        return servicio.obtenerTemporadasPorId(id);
    }

    @GetMapping("/{id}/temporadas/{numeroTemporada}")
    public List<EpisodioDTO> obtenerEpisodiosTemporada(@PathVariable Long id, @PathVariable int numeroTemporada) {
        return servicio.obtenerEpisodiosTemporada(id, numeroTemporada);
    }

    @GetMapping("/categoria/{nombreCategoria}")
    public List<SerieDTO> obtenerSerieCategoria(@PathVariable String nombreCategoria) {
        return servicio.obtenerSerieCategoria(nombreCategoria);
    }

}
