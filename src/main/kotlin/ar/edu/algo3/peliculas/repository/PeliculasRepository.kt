package ar.edu.algo3.peliculas.repository

import ar.edu.algo3.peliculas.domain.Pelicula
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.neo4j.repository.query.Query
import org.springframework.stereotype.Repository

@Repository
interface PeliculasRepository : Neo4jRepository<Pelicula, Long>  {

    @Query("MATCH (pelicula:Movie) WHERE pelicula.title =~ \$titulo RETURN pelicula LIMIT 10")
    fun peliculasPorTitulo(titulo: String): List<Pelicula>

    @Query("MATCH (pelicula:Movie)<-[actuo_en:ACTED_IN]-(persona:Person) WHERE ID(pelicula) = \$id RETURN pelicula, collect(actuo_en), collect(persona) LIMIT 1")
    fun pelicula(id: Long): Pelicula

}