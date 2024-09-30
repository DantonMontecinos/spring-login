
package com.example.userloginapp.service;

import com.example.userloginapp.model.Producto;
import com.example.userloginapp.repository.ProductoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
Esta clase implementa la lógica de negocio y se anota con @Service, que indica que Spring debe 
tratar esta clase como un componente de servicio dentro del sistema.
Implementa la interfaz ProductoService: Esta clase proporciona la implementación 
de los métodos definidos en la interfaz ProductoService. Es responsable de realizar 
operaciones como guardar, actualizar, eliminar o recuperar productos.
Inyección de dependencias: A través de la anotación @Autowired, se inyecta el ProductoRepository 
(el repositorio que interactúa con la base de datos) en esta clase. De este modo,
ProductoServiceImpl puede delegar operaciones CRUD al repositorio.

*/

@Service
public class ProductoServiceImpl implements ProductoService{
    
    
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto save(Producto producto) {
         
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> get(Integer id) {
       
        return productoRepository.findById(id);
    }

    @Override
    public void update(Producto producto) {
      productoRepository.save(producto);
       
    }

    @Override
    public void delete(Integer id) {
       productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> findAll() {
       
        return productoRepository.findAll();
    }
    
}
