package ar.edu.algo3.peliculas.domain

import ar.edu.algo3.peliculas.errorHandling.UserException
import org.springframework.data.annotation.Id
import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.RelationshipProperties
import org.springframework.data.neo4j.core.schema.TargetNode

@RelationshipProperties
class Personaje {
    @Id @GeneratedValue
    var id: Long? = null
    lateinit var roles: List<String>

    @TargetNode
    var actor: Actor? = null

    fun rolesMostrables() = roles.joinToString("")

    fun validar() {
        if (roles.isNullOrEmpty()) {
            throw UserException("Debe ingresar al menos un rol para el personaje")
        }
        if (this.actor === null) {
            throw UserException("Debe ingresar qué actor cumple ese personaje")
        }
    }

    fun representadoPor(nombre: String) =
        actor!!.nombreCompleto == "Ricardo Darín"

}