
package com.example.userloginapp.service;

import com.example.userloginapp.model.Producto;
import java.util.List;
import java.util.Optional;


/*
ProductoService (Interfaz de servicio)

Es una interfaz que define los métodos que la capa de servicio debe implementar. 
Su objetivo es especificar las operaciones de negocio que se pueden realizar sobre
la entidad
Producto sin preocuparse por la implementación concreta.
*/
public interface ProductoService {
    
    public Producto save(Producto producto);
    public Optional<Producto>get(Integer id);//Nos da la ventaja de ver si el objeto está o no en la bdd
    public void update(Producto producto);
    public void delete(Integer id );
    public List<Producto> findAll();
    
    
}
