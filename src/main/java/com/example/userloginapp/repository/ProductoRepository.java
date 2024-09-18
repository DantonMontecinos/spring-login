
package com.example.userloginapp.repository;

import com.example.userloginapp.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*

Esta interfaz extiende de JpaRepository, una interfaz proporcionada por Spring Data JPA que 
simplifica el acceso a la base de datos. Spring Data JPA genera automáticamente las implementaciones básicas (CRUD)
para las entidades al extender esta interfaz.
Extiende JpaRepository: Al extender JpaRepository<Producto, Integer>, se está indicando que este
repositorio manejará la entidad Producto y que las claves primarias son de tipo Integer.
Proporciona operaciones CRUD: Métodos como save, findById, deleteById, etc., son proporcionados por JpaRepository,
lo que elimina la necesidad de escribir consultas SQL manualmente.

*/
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    
}
