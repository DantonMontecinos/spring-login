
package com.example.userloginapp.controller;

import com.example.userloginapp.model.DetalleOrden;
import com.example.userloginapp.model.Orden;
import com.example.userloginapp.model.Producto;
import com.example.userloginapp.service.ProductoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {
    
    private final Logger log=LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    private ProductoService productoService;
    
    //Almacenar detalles de la orden
    List<DetalleOrden>detalles = new ArrayList<DetalleOrden>();
    
    //Almaceba datos de la orden
    Orden orden = new Orden();
    
    
    @GetMapping("")
    public String home(Model model){
        model.addAttribute("productos",productoService.findAll());
        return "usuario/home";
    }
    
    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model){ //model nos permite llevar info a la vista
        log.info("Id enviado como parametro",id);
        Producto producto = new Producto();
        Optional<Producto>productoOptional=productoService.get(id);
        producto = productoOptional.get();
        
        model.addAttribute("producto",producto);
        return "usuario/productohome";
          
    }
    
    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model){
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal=0;
        
        Optional <Producto> optionalProducto = productoService.get(id);
        log.info("Producto añadido:{}",optionalProducto.get());
        log.info("Cantidad: {}",cantidad);
        producto=optionalProducto.get();
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio()*cantidad);
        detalleOrden.setProducto(producto);
        
        //Validar que no se repita 2 veces el producto
        Integer idProduct = producto.getId();
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId()==idProduct);
        
        if(!ingresado){
           detalles.add(detalleOrden);
        }
        
        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum() ;
        
        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden",orden);
        
        return "usuario/carrito";
    }
    
    
        
    //quitar del carro
    @GetMapping("/delete/cart/{id}")
    public String deleteProductCart(@PathVariable Integer id, Model model){
        //lista nueva
        List<DetalleOrden>ordenesNueva=new ArrayList<DetalleOrden>();
        
        for(DetalleOrden d:detalles){
            if(d.getProducto().getId()!=id){
                ordenesNueva.add(d);
            }
        }
        //poner nueva lista con los produc restantes
        detalles=ordenesNueva;
        
        double  sumaTotal=0;
        
        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum() ;
        
        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden",orden);
        
        return "usuario/carrito";
    }
    
    @GetMapping("/getCart")
    public String getCart(Model model){
        
        model.addAttribute("cart", detalles);
        model.addAttribute("orden",orden);
        
        return "/usuario/carrito";
    }
    
    @GetMapping("/order")
    public String order(){
        
        return "usuario/resumenorden";
    }
       
    
    
    
}
